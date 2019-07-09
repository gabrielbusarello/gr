package br.univille.gr.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private final String jwttoken;

    private final Usuario usuario;

    public JwtResponse(String jwttoken, Usuario usuario) {
        this.jwttoken = jwttoken;
        this.usuario = usuario;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }
}
