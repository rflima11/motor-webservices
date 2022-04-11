package br.com.businesstec.motor.service;

import br.com.businesstec.motor.model.ControleFluxo;

import java.util.List;

public interface FluxoService {

    List<ControleFluxo> recuperarFluxosPeloIdCliente(Long idCliente);



}
