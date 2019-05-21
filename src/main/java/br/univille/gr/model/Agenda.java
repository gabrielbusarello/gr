package br.univille.gr.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String descricao;
    private Date data;
    private String hora;
    private char status;
    private Date criacao;
    private Date alteracao;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
    private Endereco endereco;

    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name="agenda_id")
    private List<Mensagem> mensagem = new ArrayList<Mensagem>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Mensagem> getMensagem() {
        return mensagem;
    }

    public void setMensagem(List<Mensagem> mensagem) {
        this.mensagem = mensagem;
    }
}
