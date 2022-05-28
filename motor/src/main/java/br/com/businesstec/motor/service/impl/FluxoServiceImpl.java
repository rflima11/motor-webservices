package br.com.businesstec.motor.service.impl;

import br.com.businesstec.model.entities.ControleFluxo;
import br.com.businesstec.model.repository.ControleFluxoRepository;
import br.com.businesstec.model.repository.ControleFluxoTentativaRepository;
import br.com.businesstec.motor.service.FluxoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FluxoServiceImpl implements FluxoService {

    private final ControleFluxoRepository controleFluxoRepository;
    private final ControleFluxoTentativaRepository controleFluxoTentativaRepository;

    public FluxoServiceImpl(ControleFluxoRepository controleFluxoRepository, ControleFluxoTentativaRepository controleFluxoTentativaRepository) {
        this.controleFluxoRepository = controleFluxoRepository;
        this.controleFluxoTentativaRepository = controleFluxoTentativaRepository;
    }


    @Override
    public List<ControleFluxo> recuperarFluxosPeloIdCliente(Long idCliente) {
        return controleFluxoRepository.findByIdControleClienteOrderByPeriodicidadeExecucaoDesc(idCliente);
    }

}
