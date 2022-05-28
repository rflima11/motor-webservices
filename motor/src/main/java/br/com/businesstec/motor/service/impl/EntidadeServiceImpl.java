package br.com.businesstec.motor.service.impl;

import br.com.businesstec.model.entities.Entidade;
import br.com.businesstec.model.repository.EntidadeRepository;
import br.com.businesstec.motor.service.EntidadeService;
import org.springframework.stereotype.Service;

@Service
public class EntidadeServiceImpl implements EntidadeService {

    private final EntidadeRepository entidadeRepository;

    public EntidadeServiceImpl(EntidadeRepository entidadeRepository) {
        this.entidadeRepository = entidadeRepository;
    }

    @Override
    public Entidade encontrarIdEntidade(Long idEntidade) {
        return entidadeRepository.findById(idEntidade).orElseThrow(() -> new RuntimeException("Não encontrada entidade"));
    }

}
