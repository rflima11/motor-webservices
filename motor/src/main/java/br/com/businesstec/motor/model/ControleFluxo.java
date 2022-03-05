package br.com.businesstec.motor.model;

import javax.persistence.*;

@Entity
@Table(name = "ctrl_fluxo")
public class ControleFluxo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "id_ctrl_cliente")
    private Long idControleCliente;
    @Column(name = "id_ctrl_ambiente_origem")
    private Long idControleAmbienteOrigem;
    @Column(name = "id_ctrl_ambiente_destino")
    private Long idControleAmbienteDestino;
    @Column(name = "id_tipo_entidade")
    private Long idTipoEntidade;
    @Column(name = "periodicidade_execucao")
    private Long periodicidadeExecucao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getIdControleCliente() {
        return idControleCliente;
    }

    public void setIdControleCliente(Long idControleCliente) {
        this.idControleCliente = idControleCliente;
    }

    public Long getIdControleAmbienteOrigem() {
        return idControleAmbienteOrigem;
    }

    public void setIdControleAmbienteOrigem(Long idControleAmbienteOrigem) {
        this.idControleAmbienteOrigem = idControleAmbienteOrigem;
    }

    public Long getIdControleAmbienteDestino() {
        return idControleAmbienteDestino;
    }

    public void setIdControleAmbienteDestino(Long idControleAmbienteDestino) {
        this.idControleAmbienteDestino = idControleAmbienteDestino;
    }

    public Long getIdTipoEntidade() {
        return idTipoEntidade;
    }

    public void setIdTipoEntidade(Long idTipoEntidade) {
        this.idTipoEntidade = idTipoEntidade;
    }

    public Long getPeriodicidadeExecucao() {
        return periodicidadeExecucao;
    }

    public void setPeriodicidadeExecucao(Long periodicidadeExecucao) {
        this.periodicidadeExecucao = periodicidadeExecucao;
    }
}
