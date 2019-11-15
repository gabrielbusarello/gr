package br.univille.gr.service;

import br.univille.gr.model.Avaliacao;
import br.univille.gr.model.Despesa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AvaliacaoService {
    List<Avaliacao> getAll();
    Optional<Avaliacao> findById(long id);
    Avaliacao save(Avaliacao avaliacao);
    void delete(Avaliacao avaliacao);
}
