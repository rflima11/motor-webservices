package br.com.businesstec.motor.repository;

import br.com.businesstec.motor.model.ControleFluxo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ControleFluxoRepository extends JpaRepository<ControleFluxo, Long> {

    List<ControleFluxo> findByIdControleCliente(Long idControleCliente);



}
