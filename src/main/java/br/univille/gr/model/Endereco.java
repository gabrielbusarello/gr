package br.univille.gr.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull()
    @NotEmpty(message = "O campo cep não pode ser vazio!")
    @Size(max = 8, message = "O campo não pode ultrapassar 8 caracteres")
    @Column(nullable = false, length = 8)
    private String cep;
    @NotNull()
    @NotEmpty(message = "O campo logradouro não pode ser vazio!")
    @Size(max = 100, message = "O campo não pode ultrapassar 100 caracteres")
    @Column(nullable = false, length = 100)
    private String logradouro;
    @NotNull()
    @NotEmpty(message = "O campo número não pode ser vazio!")
    @Size(max = 20, message = "O campo não pode ultrapassar 20 caracteres")
    @Column(nullable = false, length = 20)
    private String numero;
    @Size(max = 50, message = "O campo não pode ultrapassar 50 caracteres")
    @Column(length = 50)
    private String complemento;
    @NotNull()
    @NotEmpty(message = "O campo bairro não pode ser vazio!")
    @Size(max = 100, message = "O campo não pode ultrapassar 100 caracteres")
    @Column(nullable = false, length = 100)
    private String bairro;
    @NotNull()
    @NotEmpty(message = "O campo cidade não pode ser vazio!")
    @Size(max = 100, message = "O campo não pode ultrapassar 100 caracteres")
    @Column(nullable = false, length = 100)
    private String cidade;
    @NotNull()
    @NotEmpty(message = "O campo estado (UF) não pode ser vazio!")
    @Size(max = 2, message = "O campo não pode ultrapassar 2 caracteres")
    @Column(nullable = false, length = 2)
    private String estado;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date criacao;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date alteracao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
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

    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }

    public Date getAlteracao() {
        return alteracao;
    }

    public void setAlteracao(Date alteracao) {
        this.alteracao = alteracao;
    }

}
