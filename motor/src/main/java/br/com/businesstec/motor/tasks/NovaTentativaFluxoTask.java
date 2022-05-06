package br.com.businesstec.motor.tasks;

import br.com.businesstec.model.entities.ControleExecucaoFluxo;
import br.com.businesstec.motor.enums.ControleAmbienteEnum;
import br.com.businesstec.motor.service.ControleExecucaoFluxoService;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.logging.Logger;

public class NovaTentativaFluxoTask implements Runnable {

    private static final Logger logger = Logger.getLogger(NovaTentativaFluxoTask.class.getName());

    private final Long idFluxo;
    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange directExchange;
    private final ControleExecucaoFluxoService controleExecucaoFluxoService;
    private final Long idControleAmbiente;

    public NovaTentativaFluxoTask(Long idFluxo, RabbitTemplate rabbitTemplate, DirectExchange directExchange, ControleExecucaoFluxoService controleExecucaoFluxoService, Long idControleAmbiente) {
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
        this.idFluxo = idFluxo;
        this.controleExecucaoFluxoService = controleExecucaoFluxoService;
        this.idControleAmbiente = idControleAmbiente;
    }


    @Override
    public void run() {
            var tentativa = controleExecucaoFluxoService.registrarNovaExecucao(idFluxo);

            logger.info("============ NOVA TENTATIVA FLUXO =============");
            logger.info("Tentativa ID: " + tentativa.getId() + " hora execução: " + tentativa.getDataHora());
            logger.info("==============================================");

            rabbitTemplate.convertAndSend(directExchange.getName(), ControleAmbienteEnum.getBindingByValue(idControleAmbiente), tentativa);
        }
}

