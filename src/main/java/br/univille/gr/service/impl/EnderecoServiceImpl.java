package br.univille.gr.service.impl;

import br.univille.gr.model.Endereco;
import br.univille.gr.model.Usuario;
import br.univille.gr.repository.EnderecoRepository;
import br.univille.gr.repository.UsuarioRepository;
import br.univille.gr.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Endereco> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Endereco> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Endereco save(Endereco endereco) {
        if (endereco.getId() == 0) {
            endereco.setCriacao(new Date());
        } else {
            endereco.setAlteracao(new Date());
        }

        return repository.save(endereco);
    }

    @Override
    public void delete(Endereco endereco) {
        repository.delete(endereco);
    }
}
