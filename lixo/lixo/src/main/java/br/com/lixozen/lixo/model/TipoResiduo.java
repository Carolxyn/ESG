package br.com.lixozen.lixo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tipos_residuo")
public class TipoResiduo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    private String descricao;

    private String codigoIdentificacao;

    private String corIdentificacao;

    private boolean reciclavel;

    private String instrucaoDescarte;

    private String tempoDecomposicao;

    // Constructors
    public TipoResiduo() {}

    public TipoResiduo(String nome, String descricao, boolean reciclavel) {
        this.nome = nome;
        this.descricao = descricao;
        this.reciclavel = reciclavel;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigoIdentificacao() {
        return codigoIdentificacao;
    }

    public void setCodigoIdentificacao(String codigoIdentificacao) {
        this.codigoIdentificacao = codigoIdentificacao;
    }

    public String getCorIdentificacao() {
        return corIdentificacao;
    }

    public void setCorIdentificacao(String corIdentificacao) {
        this.corIdentificacao = corIdentificacao;
    }

    public boolean isReciclavel() {
        return reciclavel;
    }

    public void setReciclavel(boolean reciclavel) {
        this.reciclavel = reciclavel;
    }

    public String getInstrucaoDescarte() {
        return instrucaoDescarte;
    }

    public void setInstrucaoDescarte(String instrucaoDescarte) {
        this.instrucaoDescarte = instrucaoDescarte;
    }

    public String getTempoDecomposicao() {
        return tempoDecomposicao;
    }

    public void setTempoDecomposicao(String tempoDecomposicao) {
        this.tempoDecomposicao = tempoDecomposicao;
    }
}