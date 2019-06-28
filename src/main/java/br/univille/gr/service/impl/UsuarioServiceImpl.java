package br.univille.gr.service.impl;

import br.univille.gr.model.Usuario;
import br.univille.gr.repository.UsuarioRepository;
import br.univille.gr.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public List<Usuario> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Usuario> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Usuario findByUserAndPassword(String matricula, String senha) {
        return repository.findByMatriculaAndSenha(matricula, senha);
    }

    @Override
    public void save(Usuario usuario) {
        if (usuario.getId() == 0) {
            usuario.setCriacao(new Date());
        } else {
            usuario.setAlteracao(new Date());
        }

        repository.save(usuario);
    }

    @Override
    public void delete(Usuario usuario) {
        repository.delete(usuario);
    }
}
