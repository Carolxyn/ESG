package br.com.lixozen.lixo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alertas")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    private String tipo;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_resolucao")
    private LocalDateTime dataResolucao;

    private String localizacao;

    private String status;

    @Column(name = "criado_por")
    private String criadoPor;

    @Column(name = "resolvido_por")
    private String resolvidoPor;

    private Integer prioridade;

    private Boolean ativo;

    // Constructors
    public Alerta() {
        this.dataCriacao = LocalDateTime.now();
        this.ativo = true;
        this.status = "PENDENTE";
    }

    public Alerta(String titulo, String descricao, String tipo, String localizacao, Integer prioridade, String criadoPor) {
        this();
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipo = tipo;
        this.localizacao = localizacao;
        this.prioridade = prioridade;
        this.criadoPor = criadoPor;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataResolucao() {
        return dataResolucao;
    }

    public void setDataResolucao(LocalDateTime dataResolucao) {
        this.dataResolucao = dataResolucao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(String criadoPor) {
        this.criadoPor = criadoPor;
    }

    public String getResolvidoPor() {
        return resolvidoPor;
    }

    public void setResolvidoPor(String resolvidoPor) {
        this.resolvidoPor = resolvidoPor;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}