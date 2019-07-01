package br.univille.gr.service.impl;

import br.univille.gr.model.Ferramenta;
import br.univille.gr.model.TipoServico;
import br.univille.gr.repository.FerramentaRepository;
import br.univille.gr.repository.TipoServicoRepository;
import br.univille.gr.service.FerramentaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class FerramentaImpl  implements FerramentaService {
    @Autowired
    private FerramentaRepository repository;

    @Override
    public List<Ferramenta> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Ferramenta> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Ferramenta save(Ferramenta ferramenta) {
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
