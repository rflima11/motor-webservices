package br.com.businesstec.motor.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "ctrl_execucao_fluxo")
public class ControleExecucaoFluxo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "id_ctrl_fluxo")
    private Long idControleFluxo;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Column(name = "id_entidade")
    private Long idEntidade;

    public ControleExecucaoFluxo() {}

    public ControleExecucaoFluxo(Long idControleFluxo) {
        this.idControleFluxo = idControleFluxo;
        dataHora = LocalDateTime.of(2022, 01, 01, 0, 0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdControleFluxo() {
        return idControleFluxo;
    }

    public void setIdControleFluxo(Long idControleFluxo) {
        this.idControleFluxo = idControleFluxo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Long getIdEntidade() {
        return idEntidade;
    }

    public void setIdEntidade(Long idEntidade) {
        this.idEntidade = idEntidade;
    }
}
