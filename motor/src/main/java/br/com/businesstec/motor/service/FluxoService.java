package br.com.businesstec.motor.service;

import br.com.businesstec.model.entities.ControleFluxo;

import java.util.List;

public interface FluxoService {

    List<ControleFluxo> recuperarFluxosPeloIdCliente(Long idCliente);



}
