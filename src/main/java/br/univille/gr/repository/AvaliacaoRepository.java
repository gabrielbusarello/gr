package br.univille.gr.repository;

import br.univille.gr.model.Avaliacao;
import br.univille.gr.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
        List<Avaliacao> findAllByUsuario(Usuario usuario);
        Optional<Avaliacao> findByIdAndUsuario(long id, Usuario usuario);
}
