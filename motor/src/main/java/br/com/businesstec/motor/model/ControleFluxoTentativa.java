package br.com.businesstec.motor.model;

import br.com.businesstec.motor.enums.ControleAmbienteEnum;
import br.com.businesstec.motor.enums.ControleTipoFluxoEnum;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "ctrl_execucao_fluxo_tentativa")
public class ControleFluxoTentativa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "id_ctrl_execucao_fluxo")
    private Long idExecucaoFluxo;

    @Column(name = "tipo")
    private Integer tipo;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Column(name = "erro")
    private Boolean erro;

    @Column(name = "descricao_erro")
    private String descricaoErro;

    public ControleFluxoTentativa(Long idExecucaoFluxo) {
        this.idExecucaoFluxo = idExecucaoFluxo;
        dataHora = LocalDateTime.of(2022, 01, 01, 0, 0);
        erro = false;
        tipo = ControleTipoFluxoEnum.BUSCA_ORIGEM.getValue();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdExecucaoFluxo() {
        return idExecucaoFluxo;
    }

    public void setIdExecucaoFluxo(Long idExecucaoFluxo) {
        this.idExecucaoFluxo = idExecucaoFluxo;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Boolean getErro() {
        return erro;
    }

    public void setErro(Boolean erro) {
        this.erro = erro;
    }

    public String getDescricaoErro() {
        return descricaoErro;
    }

    public void setDescricaoErro(String descricaoErro) {
        this.descricaoErro = descricaoErro;
    }
}
