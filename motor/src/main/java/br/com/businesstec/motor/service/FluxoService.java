package br.com.businesstec.motor.service;

import br.com.businesstec.motor.model.ControleFluxo;
import br.com.businesstec.motor.model.ControleFluxoTentativa;

import java.util.List;

public interface FluxoService {

    List<ControleFluxo> recuperarFluxosPeloIdCliente(Long idCliente);



}
