package br.com.businesstec.motor.service.impl;

import br.com.businesstec.motor.repository.ControleFluxoRepository;
import br.com.businesstec.motor.service.ControleFluxoService;
import org.springframework.stereotype.Service;

@Service
public class ControleFluxoServiceImpl implements ControleFluxoService {

    private final ControleFluxoRepository controleFluxoRepository;

    public ControleFluxoServiceImpl(ControleFluxoRepository controleFluxoRepository) {
        this.controleFluxoRepository = controleFluxoRepository;
    }

    @Override
    public Long retornaTipoEntidadePeloIdFluxo(Long idFluxo) {
        return controleFluxoRepository.findById(idFluxo).orElseThrow(() -> new RuntimeException("Não foi possível localizar fluxo")).getIdTipoEntidade();
    }
}
