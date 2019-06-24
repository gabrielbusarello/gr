package br.univille.gr.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Ferramenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 80)
    private String nome;
    @Column(nullable = false)
    private Date dtUltimoReparo;
    @Column(nullable = false, length = 5)
    private int proximoReparo;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date criacao;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date alteracao;

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

    public Date getDtUltimoReparo() {
        return dtUltimoReparo;
    }

    public void setDtUltimoReparo(Date dtUltimoReparo) {
        this.dtUltimoReparo = dtUltimoReparo;
    }

    public int getProximoReparo() {
        return proximoReparo;
    }

    public void setProximoReparo(int proximoReparo) {
        this.proximoReparo = proximoReparo;
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
