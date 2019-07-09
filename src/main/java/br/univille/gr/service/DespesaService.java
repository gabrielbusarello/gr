package br.univille.gr.service;

import java.util.List;
import java.util.Optional;

import br.univille.gr.model.Despesa;
import org.springframework.stereotype.Service;

@Service
public interface DespesaService {
    List<Despesa> getAll();
    Optional<Despesa> findById(long id);
    Despesa save(Despesa despesa);
    void delete(Despesa despesa);
}
