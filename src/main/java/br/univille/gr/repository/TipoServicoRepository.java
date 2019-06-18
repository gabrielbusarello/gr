package br.univille.gr.repository;

import br.univille.gr.model.TipoServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoServicoRepository extends JpaRepository<TipoServico, Long> {
    TipoServico findByNome(String nome);
}
