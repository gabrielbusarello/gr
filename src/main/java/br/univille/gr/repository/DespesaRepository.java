package br.univille.gr.repository;

import br.univille.gr.model.Despesa;
import br.univille.gr.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    List<Despesa> findAllByUsuario(Usuario usuario);
    Optional<Despesa> findByIdAndUsuario(long id, Usuario usuario);
}
