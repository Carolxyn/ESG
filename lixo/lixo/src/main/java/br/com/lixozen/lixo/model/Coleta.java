package br.com.lixozen.lixo.model;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "coletas")
public class Coleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoResiduo;

    private Double quantidadeKg;

    private LocalDate dataColeta;

    private String localizacao;

    private String responsavel;

    // Constructors
    public Coleta() {}

    public Coleta(String tipoResiduo, Double quantidadeKg, LocalDate dataColeta, String localizacao, String responsavel) {
        this.tipoResiduo = tipoResiduo;
        this.quantidadeKg = quantidadeKg;
        this.dataColeta = dataColeta;
        this.localizacao = localizacao;
        this.responsavel = responsavel;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getTipoResiduo() {
        return tipoResiduo;
    }

    public void setTipoResiduo(String tipoResiduo) {
        this.tipoResiduo = tipoResiduo;
    }

    public Double getQuantidadeKg() {
        return quantidadeKg;
    }

    public void setQuantidadeKg(Double quantidadeKg) {
        this.quantidadeKg = quantidadeKg;
    }

    public LocalDate getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(LocalDate dataColeta) {
        this.dataColeta = dataColeta;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
}
