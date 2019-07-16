package br.univille.gr.repository;

import br.univille.gr.model.Ferramenta;
import br.univille.gr.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FerramentaRepository extends JpaRepository<Ferramenta, Long> {
    List<Ferramenta> findAllByUsuario(Usuario usuario);
    Optional<Ferramenta> findByIdAndUsuario(long id, Usuario usuario);
}
