package br.com.businesstec.motor.service;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidadeEntrega;

public interface ControleExecucaoFluxoEntidadeEntregaService {

    boolean verificarSeRegistroJaFoiIntegrado(Long idControleFluxoEntidade);
}
