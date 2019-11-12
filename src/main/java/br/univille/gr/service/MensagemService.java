package br.univille.gr.service;

import br.univille.gr.model.Agenda;
import br.univille.gr.model.Mensagem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MensagemService {
    List<Mensagem> getAll();
    Optional<Mensagem> findById(long id);
    List<Mensagem> findByAgenda(Agenda agenda);
    Mensagem save(Mensagem mensagem);
    void delete(Mensagem mensagem);
}
