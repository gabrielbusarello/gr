package br.univille.gr.service;

import br.univille.gr.model.Ferramenta;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FerramentaService {
    List<Ferramenta> getAll();
    Optional<Ferramenta> findById(long id);
    Ferramenta save(Ferramenta ferramenta);
    void delete(Ferramenta ferramenta);
}
