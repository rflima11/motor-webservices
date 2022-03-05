package br.com.businesstec.motor.config;

import br.com.businesstec.motor.service.ControleNovaTentativaExecucaoFluxo;
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

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableScheduling
public class SchedulerConfig implements SchedulingConfigurer {

    private final FluxoService fluxoService;
    private final ObjectMapper objectMapper;
    private final JmsTemplate jmsTemplate;
    private final ControleNovaTentativaExecucaoFluxo controleNovaTentativaExecucaoFluxo;

    public SchedulerConfig(FluxoService fluxoService, ObjectMapper objectMapper, JmsTemplate jmsTemplate, ControleNovaTentativaExecucaoFluxo controleNovaTentativaExecucaoFluxo) {
        this.fluxoService = fluxoService;
        this.objectMapper = objectMapper;
        this.jmsTemplate = jmsTemplate;
        this.controleNovaTentativaExecucaoFluxo = controleNovaTentativaExecucaoFluxo;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        var fluxo = fluxoService.recuperarFluxosPeloIdCliente(1L).get(0);
        var tentativa = controleNovaTentativaExecucaoFluxo.registrarNovaTentativa(fluxo.getId());

        var task = new NovaTentativaFluxoTask(objectMapper, jmsTemplate, tentativa, "queue.fluxo");
        Runnable runnable = () -> System.out.println("Trigger task executed at " + new Date());
        Trigger trigger = (triggerContext) -> {
            PeriodicTrigger periodicTrigger = new PeriodicTrigger(fluxo.getPeriodicidadeExecucao(), TimeUnit.SECONDS);
            return periodicTrigger.nextExecutionTime(triggerContext);
            };

        taskRegistrar.addTriggerTask(task, trigger);
    }
}