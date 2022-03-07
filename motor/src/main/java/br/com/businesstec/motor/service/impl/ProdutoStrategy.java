package br.com.businesstec.motor.service.impl;

import br.com.businesstec.motor.enums.NameStrategy;
import br.com.businesstec.motor.service.EntidadeStrategy;
import org.springframework.stereotype.Service;

@Service
public class ProdutoStrategy implements EntidadeStrategy {

    @Override
    public void salvarEntidade() {

    }

    @Override
    public NameStrategy getNomeStrategy() {
        return NameStrategy.PRODUTO_STRATEGY;
    }
}
