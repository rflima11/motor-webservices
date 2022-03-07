package br.com.businesstec.motor.service.impl;

import br.com.businesstec.motor.enums.NameStrategy;
import br.com.businesstec.motor.service.EntidadeStrategy;


public class MarcaStrategy implements EntidadeStrategy {

    @Override
    public void salvarEntidade() {

    }

    @Override
    public NameStrategy getNomeStrategy() {
        return NameStrategy.MARCA_STRATEGY;
    }
}
