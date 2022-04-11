package br.com.businesstec.motor.repository;

import br.com.businesstec.motor.model.ControleExecucaoFluxo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ControleExecucaoFluxoRepository extends JpaRepository<ControleExecucaoFluxo, Long> {

    Optional<ControleExecucaoFluxo> findTopByIdControleFluxoAndErroFalseOrderByDataHoraDesc(Long idControleFluxo);
}
