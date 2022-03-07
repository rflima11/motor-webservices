package br.com.businesstec.motor.service;

import br.com.businesstec.motor.enums.NameStrategy;

public interface EntidadeStrategy {

    void salvarEntidade();

    NameStrategy getNomeStrategy();
}
