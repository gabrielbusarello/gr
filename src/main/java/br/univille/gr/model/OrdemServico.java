package br.univille.gr.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class OrdemServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 8)
    private Double maoDeObra;
    @Column(nullable = false, columnDefinition = "TEXT", length = 8000)
    private String descricao;
    @Column(nullable = false)
    private Date data;
    @Column(nullable = false, columnDefinition = "TIME")
    private String hora;
    //- P - Pendente / C - Cancelado / F - Finalizado -//
    @Column(nullable = false, length = 1)
    private char status;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date criacao;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date alteracao;

    @OneToOne(cascade= { CascadeType.PERSIST }, optional = false)
    private Agenda agenda;

    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name="ordem_servico_id")
    private List<Produto> produto = new ArrayList<Produto>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getMaoDeObra() {
        return maoDeObra;
    }

    public void setMaoDeObra(Double maoDeObra) {
        this.maoDeObra = maoDeObra;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
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
