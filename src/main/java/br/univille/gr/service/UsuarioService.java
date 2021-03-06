package br.univille.gr.service;

import br.univille.gr.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UsuarioService {
    List<Usuario> getAll();
    Optional<Usuario> findById(long id);
    Usuario findByUserAndPassword(String cpf, String senha);
    Usuario findByUser(String cpf);
    Usuario save(Usuario usuario);
    void delete(Usuario usuario);
}
