package br.univille.gr.service.impl;

import br.univille.gr.model.Avaliacao;
import br.univille.gr.model.Despesa;
import br.univille.gr.model.Usuario;
import br.univille.gr.repository.AvaliacaoRepository;
import br.univille.gr.repository.DespesaRepository;
import br.univille.gr.repository.UsuarioRepository;
import br.univille.gr.service.AvaliacaoService;
import br.univille.gr.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class AvaliacaoImpl implements AvaliacaoService {

    @Autowired
    AvaliacaoRepository repository;

    @Autowired
    UsuarioRepository usuarioRepository;

    private Usuario getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return usuarioRepository.findByCpf(userDetails.getUsername());
    }

    @Override
    public List<Avaliacao> getAll() {
        return repository.findAllByUsuario(this.getUser());
    }

    @Override
    public Optional<Avaliacao> findById(long id) {
        return repository.findByIdAndUsuario(id, this.getUser());
    }

    @Override
    public Avaliacao save(Avaliacao avaliacao) {
        avaliacao.setUsuario(this.getUser());

        if (avaliacao.getId() == 0) {
            avaliacao.setCriacao(new Date());
        }

        return repository.save(avaliacao);
    }

    @Override
    public void delete(Avaliacao avaliacao) {
        repository.delete(avaliacao);
    }
}
