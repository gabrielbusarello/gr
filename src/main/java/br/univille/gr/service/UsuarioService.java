package br.univille.gr.service;

import br.univille.gr.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioService {
    List<Usuario> getAll();
    void save(Usuario usuario);
    void delete(Usuario usuario);
}
