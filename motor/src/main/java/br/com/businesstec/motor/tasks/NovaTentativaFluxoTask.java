package br.com.businesstec.motor.tasks;

import br.com.businesstec.motor.model.ControleExecucaoFluxo;
import br.com.businesstec.motor.model.ControleFluxo;
import br.com.businesstec.motor.model.ControleFluxoTentativa;
import br.com.businesstec.motor.service.FluxoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.core.JmsTemplate;

import java.util.logging.Logger;

public class NovaTentativaFluxoTask implements Runnable {

    private static final Logger logger = Logger.getLogger(NovaTentativaFluxoTask.class.getName());

    private final ObjectMapper objectMapper;
    private final JmsTemplate jmsTemplate;
    private final ControleExecucaoFluxo controleExecucaoFluxo;

    private final String nomeQueue;

    public NovaTentativaFluxoTask(ObjectMapper objectMapper, JmsTemplate jmsTemplate, ControleExecucaoFluxo controleExecucaoFluxo, String nomeQueue) {
        this.objectMapper = objectMapper;
        this.jmsTemplate = jmsTemplate;
        this.controleExecucaoFluxo = controleExecucaoFluxo;
        this.nomeQueue = nomeQueue;
    }

    @Override
    public void run() {
        try {
            var fluxoAsJson = objectMapper.writeValueAsString(controleExecucaoFluxo);
            jmsTemplate.convertAndSend(nomeQueue, fluxoAsJson);
        } catch (JsonProcessingException e) {
            logger.fine(e.getMessage());
        }
    }
}
