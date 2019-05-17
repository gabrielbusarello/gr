package br.univille.gr.model;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String matricula;
    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private char permissao;
    private Date criacao;
    private Date alteracao;

    public Usuario() {
    }
}
