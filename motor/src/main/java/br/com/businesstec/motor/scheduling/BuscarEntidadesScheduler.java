package br.com.businesstec.motor.scheduling;

import br.com.businesstec.motor.enums.ControleAmbienteEnum;
import br.com.businesstec.motor.enums.IntegraoEnum;
import br.com.businesstec.motor.service.ControleExecucaoFluxoEntidadeService;
import br.com.businesstec.motor.service.EntidadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class BuscarEntidadesScheduler {

    private static final Logger logger = LoggerFactory.getLogger(BuscarEntidadesScheduler.class);

    private final ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService;
    private final EntidadeService entidadeService;
    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange directExchange;

    public BuscarEntidadesScheduler(ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService, EntidadeService entidadeService, RabbitTemplate rabbitTemplate, DirectExchange directExchange) {
        this.controleExecucaoFluxoEntidadeService = controleExecucaoFluxoEntidadeService;
        this.entidadeService = entidadeService;
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
    }

    @Scheduled(fixedRate = 10000)
    public void integrarEntidades() throws InterruptedException {
        var controles = controleExecucaoFluxoEntidadeService.recuperarControlesFluxos();

        if (controles.isEmpty()) {
            logger.info("NÃO FORAM ENCONTRADOS NOVOS REGISTROS A INTEGRAR");
        } else {
            logger.info("FORAM ENCONTRADOS " + controles.size() + " REGISTROS NOVOS A SEREM INTEGRADOS");
        }

        controles.stream().forEach(c -> {
           var enumIntegracao = IntegraoEnum.getStrategyByIdEntidade(
                            entidadeService.encontrarIdEntidade(c.getIdEntidade()).getIdEntidade());
            if (enumIntegracao == IntegraoEnum.CLIENTES_STRATEGY) {
                rabbitTemplate.convertAndSend(directExchange.getName(), ControleAmbienteEnum.TOTVS_ENTREGA);
            } else {
                logger.info("ENVIANDO MENSAGENS PARA O SERVICE JET");
                rabbitTemplate.convertAndSend(directExchange.getName(), ControleAmbienteEnum.JET_ENTREGA.getBinding(), c);
            }

        });
    }



}
