package br.univille.gr.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class TipoServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull()
    @NotEmpty(message = "O campo nome não pode ser vazio!")
    @Size(max = 80, message = "O campo não pode ultrapassar 80 caracteres")
    @Column(nullable = false, length = 80)
    private String nome;
    @Size(max = 8000, message = "O campo não pode ultrapassar 8000 caracteres")
    @Column(columnDefinition = "TEXT", length = 8000)
    private String descricao;
    @NotNull()
    @Column(nullable = false, length = 8)
    private double valorEstimado;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date criacao;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date alteracao;

    @NotNull()
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
    private Usuario usuario;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public double getValorEstimado() {
        return valorEstimado;
    }

    public void setValorEstimado(double valorEstimado) {
        this.valorEstimado = valorEstimado;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
