package br.univille.gr.service.impl;

import br.univille.gr.model.Ferramenta;
import br.univille.gr.model.TipoServico;
import br.univille.gr.model.Usuario;
import br.univille.gr.repository.FerramentaRepository;
import br.univille.gr.repository.TipoServicoRepository;
import br.univille.gr.repository.UsuarioRepository;
import br.univille.gr.service.FerramentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FerramentaImpl  implements FerramentaService {

    @Autowired
    private FerramentaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return usuarioRepository.findByCpf(userDetails.getUsername());
    }

    @Override
    public List<Ferramenta> getAll() {
        return repository.findAllByUsuario(this.getUser());
    }

    @Override
    public Optional<Ferramenta> findById(long id) {
        return repository.findByIdAndUsuario(id, this.getUser());
    }

    @Override
    public Ferramenta save(Ferramenta ferramenta) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ferramenta.setUsuario(usuarioRepository.findByCpf(userDetails.getUsername()));

        if (ferramenta.getId() == 0) {
            ferramenta.setCriacao(new Date());
        } else {
            ferramenta.setAlteracao(new Date());
        }

        return repository.save(ferramenta);
    }

    @Override
    public void delete(Ferramenta ferramenta) {
        repository.delete(ferramenta);
    }
}
