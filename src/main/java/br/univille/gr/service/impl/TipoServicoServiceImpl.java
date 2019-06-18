package br.univille.gr.service.impl;

import br.univille.gr.model.TipoServico;
import br.univille.gr.repository.TipoServicoRepository;
import br.univille.gr.service.TipoServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TipoServicoServiceImpl implements TipoServicoService {
    @Autowired
    private TipoServicoRepository repository;

    @Override
    public List<TipoServico> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<TipoServico> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public void save(TipoServico tipoServico) {
        if (tipoServico.getId() == 0) {
            tipoServico.setCriacao(new Date());
        } else {
            tipoServico.setAlteracao(new Date());
        }

        repository.save(tipoServico);
    }

    @Override
    public void delete(TipoServico tipoServico) {
        repository.delete(tipoServico);
    }
}
