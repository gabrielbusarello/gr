package br.univille.gr.repository;

import br.univille.gr.model.TipoServico;
import br.univille.gr.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TipoServicoRepository extends JpaRepository<TipoServico, Long> {
    List<TipoServico> findAllByUsuario(Usuario usuario);
    Optional<TipoServico> findByIdAndUsuario(long id, Usuario usuario);
}
