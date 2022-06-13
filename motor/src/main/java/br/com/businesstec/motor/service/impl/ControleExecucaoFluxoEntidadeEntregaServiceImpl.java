package br.com.businesstec.motor.service.impl;

import br.com.businesstec.model.repository.ControleExecucaoFluxoEntidadeEntregaRepository;
import br.com.businesstec.motor.service.ControleExecucaoFluxoEntidadeEntregaService;
import org.springframework.stereotype.Service;

@Service
public class ControleExecucaoFluxoEntidadeEntregaServiceImpl implements ControleExecucaoFluxoEntidadeEntregaService {

    private final ControleExecucaoFluxoEntidadeEntregaRepository repository;

    public ControleExecucaoFluxoEntidadeEntregaServiceImpl(ControleExecucaoFluxoEntidadeEntregaRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean verificarSeRegistroJaFoiIntegrado(Long idControleFluxoEntidade) {
        return repository.existsByIdControleExecucaoFluxoEntidadeAndErroIsFalse(idControleFluxoEntidade);
    }
}
