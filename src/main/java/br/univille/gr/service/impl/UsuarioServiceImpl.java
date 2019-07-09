package br.univille.gr.service.impl;

import br.univille.gr.model.Usuario;
import br.univille.gr.repository.UsuarioRepository;
import br.univille.gr.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public List<Usuario> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Usuario> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Usuario findByUserAndPassword(String cpf, String senha) {
        return repository.findByCpfAndSenha(cpf, senha);
    }

    @Override
    public Usuario findByUser(String cpf) {
        return repository.findByCpf(cpf);
    }

    @Override
    public Usuario save(Usuario usuario) {
        if (usuario.getId() == 0) {
            usuario.setCriacao(new Date());
        } else {
            usuario.setAlteracao(new Date());
        }
        usuario.setSenha(bcryptEncoder.encode(usuario.getSenha()));

        return repository.save(usuario);
    }

    @Override
    public void delete(Usuario usuario) {
        repository.delete(usuario);
    }
}
