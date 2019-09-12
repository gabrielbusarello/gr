package br.univille.gr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull()
    @Column(unique = true, nullable = false, length = 15)
    private String cpf;
    @NotNull()
    @NotEmpty(message = "O campo nome não pode ser vazio!")
    @Size(max = 100, message = "O campo não pode ultrapassar 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nome;
    @NotNull()
    @NotEmpty(message = "O campo email não pode ser vazio!")
    @Size(max = 70, message = "O campo não pode ultrapassar 70 caracteres")
    @Column(nullable = false, length = 70)
    private String email;
    @NotNull()
    @NotEmpty(message = "O campo senha não pode ser vazio!")
    @Size(max = 100, message = "O campo não pode ultrapassar 100 caracteres")
    @Column(nullable = false, length = 100)
    private String senha;
    @NotNull()
    @Column(length = 11)
    private String telefone;
    @NotNull()
    @Column(nullable = false, length = 1)
    private char permissao;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date criacao;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date alteracao;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    private Endereco endereco;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    @JsonProperty(value = "senha")
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public char getPermissao() {
        return permissao;
    }

    public void setPermissao(char permissao) {
        this.permissao = permissao;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
