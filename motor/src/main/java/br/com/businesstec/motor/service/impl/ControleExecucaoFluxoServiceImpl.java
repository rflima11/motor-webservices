package br.com.businesstec.motor.service.impl;

import br.com.businesstec.motor.model.ControleExecucaoFluxo;
import br.com.businesstec.motor.repository.ControleExecucaoFluxoRepository;
import br.com.businesstec.motor.service.ControleExecucaoFluxoService;
import br.com.businesstec.motor.service.ControleFluxoService;
import org.springframework.stereotype.Service;

@Service
public class ControleExecucaoFluxoServiceImpl implements ControleExecucaoFluxoService {

    private final ControleFluxoService controleFluxoService;
    private final ControleExecucaoFluxoRepository repository;

    public ControleExecucaoFluxoServiceImpl(ControleFluxoService controleFluxoService, ControleExecucaoFluxoRepository repository) {
        this.controleFluxoService = controleFluxoService;
        this.repository = repository;
    }

    @Override
    public ControleExecucaoFluxo registrarNovaExecucao(Long idFluxo) {
        var controleExecucaoFluxo = new ControleExecucaoFluxo(idFluxo);
        controleExecucaoFluxo.setIdEntidade(controleFluxoService.retornaTipoEntidadePeloIdFluxo(idFluxo));
        return repository.save(controleExecucaoFluxo);
    }

}
