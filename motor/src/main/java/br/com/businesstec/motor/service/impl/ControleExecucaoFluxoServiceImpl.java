package br.com.businesstec.motor.service.impl;

import br.com.businesstec.motor.model.ControleExecucaoFluxo;
import br.com.businesstec.motor.repository.ControleExecucaoFluxoRepository;
import br.com.businesstec.motor.service.ControleExecucaoFluxoService;
import org.springframework.stereotype.Service;

@Service
public class ControleExecucaoFluxoServiceImpl implements ControleExecucaoFluxoService {

    private final ControleExecucaoFluxoRepository repository;

    public ControleExecucaoFluxoServiceImpl(ControleExecucaoFluxoRepository repository) {
        this.repository = repository;
    }

    @Override
    public ControleExecucaoFluxo registrarNovaExecucao(Long idFluxo) {
        var controleExecucaoFluxo = new ControleExecucaoFluxo(idFluxo);
        return repository.save(controleExecucaoFluxo);
    }

}
