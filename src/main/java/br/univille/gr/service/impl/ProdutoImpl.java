package br.univille.gr.service.impl;

import br.univille.gr.model.Produto;
import br.univille.gr.model.Usuario;
import br.univille.gr.repository.ProdutoRepository;
import br.univille.gr.repository.UsuarioRepository;
import br.univille.gr.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoImpl implements ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return usuarioRepository.findByCpf(userDetails.getUsername());
    }

    @Override
    public List<Produto> getAll() {
        return repository.findAllByUsuario(this.getUser());
    }

    @Override
    public Optional<Produto> findById(long id) {
        return repository.findByIdAndUsuario(id, this.getUser());
    }

    @Override
    public Produto save(Produto produto) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        produto.setUsuario(usuarioRepository.findByCpf(userDetails.getUsername()));

        if (produto.getId() == 0) {
            produto.setCriacao(new Date());
        } else {
            produto.setAlteracao(new Date());
        }

        return repository.save(produto);
    }

    @Override
    public void delete(Produto produto) {
        repository.delete(produto);
    }
}
