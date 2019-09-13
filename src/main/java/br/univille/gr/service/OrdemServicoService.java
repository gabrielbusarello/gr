package br.univille.gr.service;

import br.univille.gr.model.OrdemServico;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrdemServicoService {
    List<OrdemServico> getAll();
    Optional<OrdemServico> findById(long id);
    OrdemServico save(OrdemServico ordemServico);
    void delete(OrdemServico ordemServico);
}
