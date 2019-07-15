package br.univille.gr.service.impl;

import br.univille.gr.model.Despesa;
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

    @Override
    public List<Despesa> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Despesa> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Despesa save(Despesa despesa) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        despesa.setUsuario(usuarioRepository.findByCpf(userDetails.getUsername()));

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
