package br.com.businesstec.motor.service.impl;

import br.com.businesstec.model.entities.ControleExecucaoFluxo;
import br.com.businesstec.model.repository.ControleExecucaoFluxoRepository;
import br.com.businesstec.motor.service.ControleExecucaoFluxoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ControleExecucaoFluxoServiceImpl implements ControleExecucaoFluxoService {

    private final ControleExecucaoFluxoRepository repository;

    public ControleExecucaoFluxoServiceImpl(ControleExecucaoFluxoRepository repository) {
        this.repository = repository;
    }

    @Override
    public ControleExecucaoFluxo registrarNovaExecucao(Long idFluxo) {
        var controleExecucaoFluxo = new ControleExecucaoFluxo(idFluxo, recuperarDataUltimaTentativaBemSucedida(idFluxo));
        return repository.save(controleExecucaoFluxo);
    }

    private LocalDateTime recuperarDataUltimaTentativaBemSucedida(Long idControleFluxo) {
        var controleExecucaoFluxoTentativa = repository.findTopByIdControleFluxoAndErroFalseOrderByDataHoraDesc(idControleFluxo);
        if (controleExecucaoFluxoTentativa.isEmpty()) {
            return LocalDateTime.now();
        }
        return controleExecucaoFluxoTentativa.get().getDataHora();
    }

}
