package br.com.businesstec.motor.config;

import br.com.businesstec.motor.service.ControleExecucaoFluxoService;
import br.com.businesstec.motor.service.FluxoService;
import br.com.businesstec.motor.tasks.NovaTentativaFluxoTask;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.PeriodicTrigger;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableScheduling
public class SchedulerConfig implements SchedulingConfigurer {

    private final FluxoService fluxoService;
    private final ObjectMapper objectMapper;
    private final JmsTemplate jmsTemplate;
    private final ControleExecucaoFluxoService controleExecucaoFluxoService;

    public SchedulerConfig(FluxoService fluxoService, ObjectMapper objectMapper, JmsTemplate jmsTemplate, ControleExecucaoFluxoService controleExecucaoFluxoService) {
        this.fluxoService = fluxoService;
        this.objectMapper = objectMapper;
        this.jmsTemplate = jmsTemplate;
        this.controleExecucaoFluxoService = controleExecucaoFluxoService;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        var fluxo = fluxoService.recuperarFluxosPeloIdCliente(1L);
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(3));
        fluxo.stream().forEach(f -> {
            var task = new NovaTentativaFluxoTask(objectMapper, jmsTemplate, controleExecucaoFluxoService, f.getId(),"queue.fluxo");
            Trigger trigger = (triggerContext) -> {
                PeriodicTrigger periodicTrigger = new PeriodicTrigger(f.getPeriodicidadeExecucao(), TimeUnit.SECONDS);
                return periodicTrigger.nextExecutionTime(triggerContext);
            };

            taskRegistrar.addTriggerTask(task, trigger);
        });
    }
}
