package br.univille.gr.service;

import br.univille.gr.model.Avaliacao;
import br.univille.gr.model.Despesa;
import br.univille.gr.model.OrdemServico;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public interface AvaliacaoService {
    List<Avaliacao> getAll();
    Optional<Avaliacao> findById(long id);
    List<Avaliacao> findByOrdemServico(OrdemServico ordemServico);
    Avaliacao save(Avaliacao avaliacao);
    void delete(Avaliacao avaliacao);
}
