package br.com.businesstec.motor.service.impl;

import br.com.businesstec.motor.model.ControleFluxoTentativa;
import br.com.businesstec.motor.repository.ControleFluxoTentativaRepository;
import br.com.businesstec.motor.service.ControleExecucaoFluxoService;
import br.com.businesstec.motor.service.ControleNovaTentativaExecucaoFluxo;
import org.springframework.stereotype.Service;

@Service
public class ControleNovaTentativaExecucaoFluxoImpl implements ControleNovaTentativaExecucaoFluxo {

    private final ControleExecucaoFluxoService controleExecucaoFluxoService;
    private final ControleFluxoTentativaRepository controleFluxoTentativaRepository;

    public ControleNovaTentativaExecucaoFluxoImpl(ControleExecucaoFluxoService controleExecucaoFluxoService, ControleFluxoTentativaRepository controleFluxoTentativaRepository) {
        this.controleExecucaoFluxoService = controleExecucaoFluxoService;
        this.controleFluxoTentativaRepository = controleFluxoTentativaRepository;
    }

    @Override
    public ControleFluxoTentativa registrarNovaTentativa(Long idFluxo) {
        var controleExecucaoFluxo = controleExecucaoFluxoService.registrarNovaExecucao(idFluxo);
        return controleFluxoTentativaRepository.save(new ControleFluxoTentativa(controleExecucaoFluxo.getId()));
    }
}
