package br.univille.gr.repository;

import br.univille.gr.model.Agenda;
import br.univille.gr.model.Mensagem;
import br.univille.gr.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    List<Mensagem> findAllByUsuario(Usuario usuario);
    Optional<Mensagem> findByIdAndUsuario(long id, Usuario usuario);
    List<Mensagem> findAllByAgenda(Agenda agenda);
}
