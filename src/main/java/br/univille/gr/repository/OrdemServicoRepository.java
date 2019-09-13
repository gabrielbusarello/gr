package br.univille.gr.repository;

import br.univille.gr.model.OrdemServico;
import br.univille.gr.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
    List<OrdemServico> findAllByUsuario(Usuario usuario);
    Optional<OrdemServico> findByIdAndUsuario(long id, Usuario usuario);
}
