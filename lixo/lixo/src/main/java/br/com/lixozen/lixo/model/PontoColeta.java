package br.com.lixozen.lixo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "pontos_coleta")
public class PontoColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String endereco;

    private String cidade;

    private String estado;

    private String cep;

    private String telefone;

    private String email;

    private String horarioFuncionamento;

    private boolean ativo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ponto_coleta_tipo_residuo",
            joinColumns = @JoinColumn(name = "ponto_coleta_id"),
            inverseJoinColumns = @JoinColumn(name = "tipo_residuo_id")
    )
    private List<TipoResiduo> tiposResiduoAceitos;

    private Double latidude;

    private Double longitude;

    // Constructors
    public PontoColeta() {}

    public PontoColeta(String nome, String endereco, String cidade, String estado,
                       String cep, List<TipoResiduo> tiposResiduoAceitos) {
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.tiposResiduoAceitos = tiposResiduoAceitos;
        this.ativo = true;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public void setHorarioFuncionamento(String horarioFuncionamento) {
        this.horarioFuncionamento = horarioFuncionamento;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<TipoResiduo> getTiposResiduoAceitos() {
        return tiposResiduoAceitos;
    }

    public void setTiposResiduoAceitos(List<TipoResiduo> tiposResiduoAceitos) {
        this.tiposResiduoAceitos = tiposResiduoAceitos;
    }

    public Double getLatidude() {
        return latidude;
    }

    public void setLatidude(Double latidude) {
        this.latidude = latidude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}