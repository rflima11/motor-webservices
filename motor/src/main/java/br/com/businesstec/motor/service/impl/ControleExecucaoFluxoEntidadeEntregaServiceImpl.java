package br.com.businesstec.motor.service.impl;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidadeEntrega;
import br.com.businesstec.model.repository.ControleExecucaoFluxoEntidadeEntregaRepository;
import br.com.businesstec.motor.service.ControleExecucaoFluxoEntidadeEntregaService;
import br.com.businesstec.motor.service.ControleExecucaoFluxoEntidadeService;
import org.springframework.stereotype.Service;

@Service
public class ControleExecucaoFluxoEntidadeEntregaServiceImpl implements ControleExecucaoFluxoEntidadeEntregaService {

    private final ControleExecucaoFluxoEntidadeEntregaRepository repository;
    private final ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService;

    public ControleExecucaoFluxoEntidadeEntregaServiceImpl(ControleExecucaoFluxoEntidadeEntregaRepository repository, ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService) {
        this.repository = repository;
        this.controleExecucaoFluxoEntidadeService = controleExecucaoFluxoEntidadeService;
    }

    @Override
    public boolean verificarSeRegistroJaFoiIntegrado(Long idControleFluxoEntidade) {
        return repository.existsByIdControleExecucaoFluxoEntidade(idControleFluxoEntidade);
    }

    @Override
    public ControleExecucaoFluxoEntidadeEntrega registrarExecucao(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);
        return repository.save(new ControleExecucaoFluxoEntidadeEntrega(controleExecucaoFluxoEntidade.getId(), false));
    }

}
