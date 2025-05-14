package br.com.lixozen.lixo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "relatorios")
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    @Column(name = "data_geracao")
    private LocalDateTime dataGeracao;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "gerado_por")
    private String geradoPor;

    @Column(length = 4000)
    private String conteudo;

    @Column(name = "tipo_relatorio")
    private String tipoRelatorio;

    @Column(name = "formato_arquivo")
    private String formatoArquivo;

    @ElementCollection
    @CollectionTable(name = "relatorio_metricas",
            joinColumns = @JoinColumn(name = "relatorio_id"))
    @MapKeyColumn(name = "nome_metrica")
    @Column(name = "valor_metrica")
    private Map<String, String> metricas;

    // Constructors
    public Relatorio() {
        this.dataGeracao = LocalDateTime.now();
    }

    public Relatorio(String titulo, String descricao, LocalDate dataInicio, LocalDate dataFim, String geradoPor) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.geradoPor = geradoPor;
        this.dataGeracao = LocalDateTime.now();
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

    public LocalDateTime getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDateTime dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getGeradoPor() {
        return geradoPor;
    }

    public void setGeradoPor(String geradoPor) {
        this.geradoPor = geradoPor;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getTipoRelatorio() {
        return tipoRelatorio;
    }

    public void setTipoRelatorio(String tipoRelatorio) {
        this.tipoRelatorio = tipoRelatorio;
    }

    public String getFormatoArquivo() {
        return formatoArquivo;
    }

    public void setFormatoArquivo(String formatoArquivo) {
        this.formatoArquivo = formatoArquivo;
    }

    public Map<String, String> getMetricas() {
        return metricas;
    }

    public void setMetricas(Map<String, String> metricas) {
        this.metricas = metricas;
    }
}