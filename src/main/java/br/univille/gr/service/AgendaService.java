package br.univille.gr.service;

import br.univille.gr.model.Agenda;
import br.univille.gr.model.Despesa;

import java.util.List;
import java.util.Optional;

public interface AgendaService {
    List<Agenda> getAll();
    Optional<Agenda> findById(long id);
    Agenda save(Agenda agenda);
    void delete(Agenda agenda);
}
