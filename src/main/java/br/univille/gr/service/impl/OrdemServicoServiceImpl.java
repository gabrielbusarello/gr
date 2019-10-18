package br.univille.gr.service.impl;

import br.univille.gr.model.OrdemServico;
import br.univille.gr.model.Usuario;
import br.univille.gr.repository.AgendaRepository;
import br.univille.gr.repository.OrdemServicoRepository;
import br.univille.gr.repository.UsuarioRepository;
import br.univille.gr.service.OrdemServicoService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrdemServicoServiceImpl implements OrdemServicoService {
    @Autowired
    OrdemServicoRepository repository;

    @Autowired
    UsuarioRepository usuarioRepository;

    private Usuario getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return usuarioRepository.findByCpf(userDetails.getUsername());
    }

    @Override
    public List<OrdemServico> getAll() {
        return repository.findAllByPrestador(this.getUser());
    }

    @Override
    public Optional<OrdemServico> findById(long id) {
        return repository.findByIdAndPrestador(id, this.getUser());
    }

    @Override
    public OrdemServico save(OrdemServico ordemServico) {
        ordemServico.setPrestador(this.getUser());

        if (ordemServico.getId() == 0) {
            ordemServico.setCriacao(new Date());
        } else {
            ordemServico.setAlteracao(new Date());
        }

        return repository.save(ordemServico);
    }

    @Override
    public void delete(OrdemServico ordemServico) {
        repository.delete(ordemServico);
    }
}
