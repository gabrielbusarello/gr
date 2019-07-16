package br.univille.gr.service.impl;

import br.univille.gr.model.Despesa;
import br.univille.gr.model.Usuario;
import br.univille.gr.repository.DespesaRepository;
import br.univille.gr.repository.UsuarioRepository;
import br.univille.gr.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DespesaServiceImpl implements DespesaService {

    @Autowired
    DespesaRepository repository;

    @Autowired
    UsuarioRepository usuarioRepository;

    private Usuario getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return usuarioRepository.findByCpf(userDetails.getUsername());
    }

    @Override
    public List<Despesa> getAll() {
        return repository.findAllByUsuario(this.getUser());
    }

    @Override
    public Optional<Despesa> findById(long id) {
        return repository.findByIdAndUsuario(id, this.getUser());
    }

    @Override
    public Despesa save(Despesa despesa) {
        despesa.setUsuario(this.getUser());

        if (despesa.getId() == 0) {
            despesa.setCriacao(new Date());
        } else {
            despesa.setAlteracao(new Date());
        }

        return repository.save(despesa);

    }

    @Override
    public void delete(Despesa despesa) {
        repository.delete(despesa);
    }
}
