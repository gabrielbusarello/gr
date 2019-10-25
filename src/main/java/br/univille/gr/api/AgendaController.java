package br.univille.gr.api;

import br.univille.gr.model.Agenda;
import br.univille.gr.service.AgendaService;
import br.univille.gr.util.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @GetMapping
    public ResponseEntity<Resposta<List<Agenda>>> listarAgendas(@RequestParam char status, @RequestParam long idPrestador) {
        if (status != ' ' || idPrestador != 0) {

        }
        List<Agenda> lista = agendaService.getAll();
        Resposta<List<Agenda>> resposta = new Resposta<List<Agenda>>();
        if (lista.isEmpty()) {
            resposta.setStatus(2);
            resposta.setMensagem("Não há registros!");
        } else {
            resposta.setStatus(1);
            resposta.setData(lista);
        }
        return new ResponseEntity<Resposta<List<Agenda>>>(resposta, HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Resposta<Agenda>> buscaAgendaPeloId(@PathVariable("id")long id) {
        Optional<Agenda> talvezAgenda = agendaService.findById(id);
        Resposta<Agenda> resposta = new Resposta<Agenda>();
        if (!talvezAgenda.isPresent()) {
            return this.naoEncontrado(resposta);
        }
        resposta.setStatus(1);
        resposta.setData(talvezAgenda.get());

        return new ResponseEntity<Resposta<Agenda>>(resposta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Resposta<Agenda>> save(@RequestBody Agenda Agenda) {
        Agenda agendaI = agendaService.save(Agenda);
        Resposta<Agenda> resposta = new Resposta<Agenda>();
        if(Agenda == null) {
            resposta.setStatus(3);
            resposta.setMensagem("Agenda não foi registrada!");
            return new ResponseEntity<Resposta<Agenda>>(resposta, HttpStatus.OK);
        }
        resposta.setStatus(1);
        resposta.setData(agendaI);
        resposta.setMensagem("Agenda cadastrada com sucesso!");
        return new ResponseEntity<Resposta<Agenda>>(resposta, HttpStatus.OK);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<Resposta<Agenda>> update(@PathVariable("id")long id, @RequestBody Agenda newAgenda) {
        Optional<Agenda> talvezAgenda = agendaService.findById(id);
        Resposta<Agenda> resposta = new Resposta<Agenda>();
        if (!talvezAgenda.isPresent()) {
            return naoEncontrado(resposta);
        }

        Agenda oldAgenda = talvezAgenda.get();

        oldAgenda.setDescricao(newAgenda.getDescricao());
        oldAgenda.setData(newAgenda.getData());
        oldAgenda.setHora(newAgenda.getHora());

        Agenda agendaA = agendaService.save(oldAgenda);

        if(agendaA == null) {
            resposta.setStatus(3);
            resposta.setMensagem("Agenda não foi alterada!");
            return new ResponseEntity<Resposta<Agenda>>(resposta, HttpStatus.OK);
        }
        resposta.setStatus(1);
        resposta.setData(agendaA);
        resposta.setMensagem("Agenda alterada com sucesso!");
        return new ResponseEntity<Resposta<Agenda>>(resposta, HttpStatus.OK);
    }

    @PatchMapping(path="/{id}")
    public ResponseEntity<Resposta<Agenda>> updatePartial(@PathVariable("id")long id, @RequestBody Agenda updateAgenda) {
        Optional<Agenda> talvezAgenda = agendaService.findById(id);
        Resposta<Agenda> resposta = new Resposta<Agenda>();
        if (!talvezAgenda.isPresent()) {
            return naoEncontrado(resposta);
        }

        Agenda oldAgenda = talvezAgenda.get();

        if (updateAgenda.getStatus() != ' ') {
            oldAgenda.setStatus(updateAgenda.getStatus());
        }

        if (updateAgenda.getPrestador() != null) {
            oldAgenda.setPrestador(updateAgenda.getPrestador());
        }

        Agenda agendaA = agendaService.save(oldAgenda);

        if(agendaA == null) {
            resposta.setStatus(3);
            resposta.setMensagem("Agenda não foi alterada!");
            return new ResponseEntity<Resposta<Agenda>>(resposta, HttpStatus.OK);
        }
        resposta.setStatus(1);
        resposta.setData(agendaA);
        resposta.setMensagem("Agenda alterada com sucesso!");
        return new ResponseEntity<Resposta<Agenda>>(resposta, HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Resposta<Agenda>> delete(@PathVariable("id") long id){
        Optional<Agenda> talvezAgenda = agendaService.findById(id);
        Resposta<Agenda> resposta = new Resposta<Agenda>();
        if (!talvezAgenda.isPresent()) {
            return naoEncontrado(resposta);
        }

        agendaService.delete(talvezAgenda.get());

        resposta.setStatus(1);
        resposta.setMensagem("Agenda excluída com sucesso!");

        return new ResponseEntity<Resposta<Agenda>>(resposta, HttpStatus.OK);
    }

    private ResponseEntity<Resposta<Agenda>> naoEncontrado(Resposta<Agenda> resposta) {
        resposta.setStatus(2);
        resposta.setMensagem("Agenda não encontrada!");
        return new ResponseEntity<Resposta<Agenda>>(resposta, HttpStatus.NOT_FOUND);
    }
}
