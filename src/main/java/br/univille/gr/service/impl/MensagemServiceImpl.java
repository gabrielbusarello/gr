package br.univille.gr.service.impl;

import br.univille.gr.model.Mensagem;
import br.univille.gr.model.Usuario;
import br.univille.gr.repository.MensagemRepository;
import br.univille.gr.repository.UsuarioRepository;
import br.univille.gr.service.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MensagemServiceImpl implements MensagemService {

    @Autowired
    MensagemRepository repository;

    @Autowired
    UsuarioRepository usuarioRepository;

    private Usuario getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return usuarioRepository.findByCpf(userDetails.getUsername());
    }

    @Override
    public List<Mensagem> getAll() {
        return repository.findAllByUsuario(this.getUser());
    }

    @Override
    public Optional<Mensagem> findById(long id) {
        return repository.findByIdAndUsuario(id, this.getUser());
    }

    @Override
    public Mensagem save(Mensagem mensagem) {
        mensagem.setUsuario(this.getUser());

        if (mensagem.getId() == 0) {
            mensagem.setCriacao(new Date());
        }

        return repository.save(mensagem);
    }

    @Override
    public void delete(Mensagem mensagem) {
        repository.delete(mensagem);
    }
}
