package br.univille.gr.service;

import br.univille.gr.model.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {
    List<Produto> getAll();
    Optional<Produto> findById(long id);
    Produto save(Produto produto);
    void delete(Produto produto);
}
