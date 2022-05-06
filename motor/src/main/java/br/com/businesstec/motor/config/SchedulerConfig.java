package br.com.businesstec.motor.config;

import br.com.businesstec.motor.service.ControleExecucaoFluxoService;
import br.com.businesstec.motor.service.FluxoService;
import br.com.businesstec.motor.tasks.NovaTentativaFluxoTask;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    private final ControleExecucaoFluxoService controleExecucaoFluxoService;
    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange directExchange;

    public SchedulerConfig(FluxoService fluxoService,
                           ControleExecucaoFluxoService controleExecucaoFluxoService,
                           RabbitTemplate rabbitTemplate,
                           DirectExchange directExchange) {
        this.fluxoService = fluxoService;
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
        this.controleExecucaoFluxoService = controleExecucaoFluxoService;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        var fluxo = fluxoService.recuperarFluxosPeloIdCliente(1L);
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(3));
        fluxo.stream().forEach(f -> {
            var task = new NovaTentativaFluxoTask(f.getId(), rabbitTemplate, directExchange, controleExecucaoFluxoService, f.getIdControleAmbienteOrigem());
            Trigger trigger = (triggerContext) -> {
                PeriodicTrigger periodicTrigger = new PeriodicTrigger(f.getPeriodicidadeExecucao(), TimeUnit.SECONDS);
                return periodicTrigger.nextExecutionTime(triggerContext);
            };

            taskRegistrar.addTriggerTask(task, trigger);
        });
    }
}
