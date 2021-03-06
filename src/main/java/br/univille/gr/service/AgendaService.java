package br.univille.gr.service;

import br.univille.gr.model.Agenda;
import br.univille.gr.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface AgendaService {
    List<Agenda> getAll();
    List<Agenda> getAllByStatusAndPrestador(char status, Usuario prestador);
    List<Agenda> getAllByStatusAndPrestadorData(char status, Usuario prestador, Date data);
    Optional<Agenda> findById(long id);
    Agenda save(Agenda agenda);
    void delete(Agenda agenda);
}
