package br.com.businesstec.motor.tasks;

import br.com.businesstec.motor.model.ControleExecucaoFluxo;
import br.com.businesstec.motor.service.ControleExecucaoFluxoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.core.JmsTemplate;

import java.util.logging.Logger;

public class NovaTentativaFluxoTask implements Runnable {

    private static final Logger logger = Logger.getLogger(NovaTentativaFluxoTask.class.getName());

    private final ObjectMapper objectMapper;
    private final JmsTemplate jmsTemplate;
    private final ControleExecucaoFluxoService controleExecucaoFluxoService;
    private final Long idFluxo;


    private final String nomeQueue;

    public NovaTentativaFluxoTask(ObjectMapper objectMapper,
                                  JmsTemplate jmsTemplate,
                                  ControleExecucaoFluxoService controleExecucaoFluxoService,
                                  Long idFluxo,
                                  String nomeQueue) {
        this.objectMapper = objectMapper;
        this.jmsTemplate = jmsTemplate;
        this.controleExecucaoFluxoService = controleExecucaoFluxoService;
        this.idFluxo = idFluxo;
        this.nomeQueue = nomeQueue;
    }

    @Override
    public void run() {
        try {
            var tentativa = controleExecucaoFluxoService.registrarNovaExecucao(idFluxo);
            var fluxoAsJson = objectMapper.writeValueAsString(tentativa);

            logger.info("============ NOVA TENTATIVA FLUXO =============");
            logger.info(fluxoAsJson);
            logger.info("==============================================");

            jmsTemplate.convertAndSend(nomeQueue, fluxoAsJson);

        } catch (JsonProcessingException e) {
            logger.fine(e.getMessage());
        }
    }
}
