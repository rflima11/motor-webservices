package br.com.businesstec.motor.model;

import javax.persistence.*;

@Entity
@Table(name = "ctrl_execucao_fluxo_entidade")
public class ControleFluxoTentativa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "id_ctrl_execucao_fluxo")
    private Long idExecucaoFluxo;

    @Column(name = "id_entidade")
    private Long id_entidade;


    public ControleFluxoTentativa(ControleExecucaoFluxo controleExecucaoFluxo) {
        this.idExecucaoFluxo = controleExecucaoFluxo.getIdControleFluxo();

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

    public Long getId_entidade() {
        return id_entidade;
    }

    public void setId_entidade(Long id_entidade) {
        this.id_entidade = id_entidade;
    }
}
