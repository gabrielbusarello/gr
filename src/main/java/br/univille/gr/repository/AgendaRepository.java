package br.univille.gr.repository;

import br.univille.gr.model.Agenda;
import br.univille.gr.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    List<Agenda> findAllByUsuario(Usuario usuario);
    Optional<Agenda> findByIdAndUsuario(long id, Usuario usuario);
}
