package br.univille.gr.service;

import br.univille.gr.model.TipoServico;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TipoServicoService {
    List<TipoServico> getAll();
    Optional<TipoServico> findById(long id);
    TipoServico save(TipoServico tipoServico);
    void delete(TipoServico tipoServico);
}
