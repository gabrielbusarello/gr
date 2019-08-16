package br.univille.gr.repository;

import br.univille.gr.model.Produto;
import br.univille.gr.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findAllByUsuario(Usuario usuario);
    Optional<Produto> findByIdAndUsuario(long id, Usuario usuario);
}
