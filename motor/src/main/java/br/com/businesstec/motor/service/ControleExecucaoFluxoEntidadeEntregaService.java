package br.com.businesstec.motor.service;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidadeEntrega;

public interface ControleExecucaoFluxoEntidadeEntregaService {

    boolean verificarSeRegistroJaFoiIntegrado(Long idControleFluxoEntidade);

    ControleExecucaoFluxoEntidadeEntrega registrarExecucao(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade);
}
