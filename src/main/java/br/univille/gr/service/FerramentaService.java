package br.univille.gr.service;

import br.univille.gr.model.Ferramenta;

import java.util.List;
import java.util.Optional;

public interface FerramentaService {
    List<Ferramenta> getAll();
    Optional<Ferramenta> findById(long id);
    Ferramenta save(Ferramenta ferramenta);
    void delete(Ferramenta ferramenta);
}
