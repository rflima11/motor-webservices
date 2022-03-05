package br.com.businesstec.motor.repository;

import br.com.businesstec.motor.model.ControleFluxoTentativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControleFluxoTentativaRepository extends JpaRepository<ControleFluxoTentativa, Long> {
}
