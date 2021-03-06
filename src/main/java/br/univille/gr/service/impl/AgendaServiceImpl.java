package br.univille.gr.service.impl;

import br.univille.gr.model.Agenda;
import br.univille.gr.model.Despesa;
import br.univille.gr.model.Usuario;
import br.univille.gr.repository.AgendaRepository;
import br.univille.gr.repository.DespesaRepository;
import br.univille.gr.repository.UsuarioRepository;
import br.univille.gr.service.AgendaService;
import br.univille.gr.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AgendaServiceImpl implements AgendaService {

    @Autowired
    AgendaRepository repository;

    @Autowired
    UsuarioRepository usuarioRepository;

    private Usuario getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return usuarioRepository.findByCpf(userDetails.getUsername());
    }

    @Override
    public List<Agenda> getAll() {
        if (getUser().getPermissao() == 'I') {
            return repository.findAll();
        } else if (getUser().getPermissao() == 'P') {
            return repository.findAllByStatusIsNot('A');
        } else {
            return repository.findAllByUsuario(this.getUser());
        }
    }

    @Override
    public List<Agenda> getAllByStatusAndPrestador(char status, Usuario prestador) {
        return repository.findAllByStatusAndPrestador(status, prestador);
    }

    @Override
    public List<Agenda> getAllByStatusAndPrestadorData(char status, Usuario prestador, Date data) {
        return repository.findAllByStatusAndPrestadorAndDataIsLessThanEqual(status, prestador, data);
    }

    @Override
    public Optional<Agenda> findById(long id) {
        if (getUser().getPermissao() == 'I' || getUser().getPermissao() == 'P') {
            return repository.findById(id);
        } else {
            return repository.findByIdAndUsuario(id, this.getUser());
        }
    }

    @Override
    public Agenda save(Agenda agenda) {
        agenda.setUsuario(this.getUser());

        if (agenda.getId() == 0) {
            agenda.setCriacao(new Date());
        } else {
            agenda.setAlteracao(new Date());
        }

        return repository.save(agenda);

    }

    @Override
    public void delete(Agenda agenda) {
        repository.delete(agenda);
    }
}
