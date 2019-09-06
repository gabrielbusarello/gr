package br.univille.gr.service;

import br.univille.gr.model.Endereco;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EnderecoService {
    List<Endereco> getAll();
    Optional<Endereco> findById(long id);
    Endereco save(Endereco endereco);
    void delete(Endereco endereco);
}
