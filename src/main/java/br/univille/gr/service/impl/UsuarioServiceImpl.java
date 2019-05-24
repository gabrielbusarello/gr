package br.univille.gr.service.impl;

import br.univille.gr.model.Usuario;
import br.univille.gr.repository.UsuarioRepository;
import br.univille.gr.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public List<Usuario> getAll() {
        return repository.findAll();
    }
}
