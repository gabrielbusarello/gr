package br.univille.gr.api;

import br.univille.gr.model.Agenda;
import br.univille.gr.model.Despesa;
import br.univille.gr.model.Mensagem;
import br.univille.gr.service.AgendaService;
import br.univille.gr.service.DespesaService;
import br.univille.gr.service.MensagemService;
import br.univille.gr.util.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mensagem")
public class MensagemController {

    @Autowired
    private MensagemService mensagemService;

    @Autowired
    private AgendaService agendaService;

    @GetMapping
    public ResponseEntity<Resposta<List<Mensagem>>> listarMensagens(@RequestParam long idAgenda) {
        Optional<Agenda> agenda = agendaService.findById(idAgenda);

        Resposta<List<Mensagem>> resposta = new Resposta<List<Mensagem>>();

        if (!agenda.isPresent()){
            resposta.setStatus(3);
            resposta.setMensagem("Agenda não existente!");
        }

        List<Mensagem> lista = mensagemService.findByAgenda(agenda.get());

        for (int i = 0; i < lista.size(); i++){
            lista.get(i).setAgenda(null);
        }

        if (lista.isEmpty()) {
            resposta.setStatus(2);
            resposta.setMensagem("Não há registros!");
        } else {
            resposta.setStatus(1);
            resposta.setData(lista);
        }
        return new ResponseEntity<Resposta<List<Mensagem>>>(resposta, HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Resposta<Mensagem>> buscaMensagemPeloId(@PathVariable("id")long id) {
        Optional<Mensagem> talvezMensagem = mensagemService.findById(id);
        Resposta<Mensagem> resposta = new Resposta<Mensagem>();
        if (!talvezMensagem.isPresent()) {
            return this.naoEncontrado(resposta);
        }
        resposta.setStatus(1);
        resposta.setData(talvezMensagem.get());

        return new ResponseEntity<Resposta<Mensagem>>(resposta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Resposta<Mensagem>> save(@RequestBody Mensagem Mensagem) {
        Agenda agenda = Mensagem.getAgenda();

        Mensagem mensagemI = mensagemService.save(Mensagem);
        Resposta<Mensagem> resposta = new Resposta<Mensagem>();
        if(Mensagem == null) {
            resposta.setStatus(3);
            resposta.setMensagem("Mensagem não foi registrada!");
            return new ResponseEntity<Resposta<Mensagem>>(resposta, HttpStatus.OK);
        }
        resposta.setStatus(1);
        resposta.setData(mensagemI);
        resposta.setMensagem("Mensagem cadastrada com sucesso!");
        return new ResponseEntity<Resposta<Mensagem>>(resposta, HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Resposta<Mensagem>> delete(@PathVariable("id") long id){
        Optional<Mensagem> talvezMensagem = mensagemService.findById(id);
        Resposta<Mensagem> resposta = new Resposta<Mensagem>();
        if (!talvezMensagem.isPresent()) {
            return naoEncontrado(resposta);
        }

        mensagemService.delete(talvezMensagem.get());

        resposta.setStatus(1);
        resposta.setMensagem("Mensagem excluída com sucesso!");

        return new ResponseEntity<Resposta<Mensagem>>(resposta, HttpStatus.OK);
    }

    private ResponseEntity<Resposta<Mensagem>> naoEncontrado(Resposta<Mensagem> resposta) {
        resposta.setStatus(2);
        resposta.setMensagem("Mensagem não encontrada!");
        return new ResponseEntity<Resposta<Mensagem>>(resposta, HttpStatus.NOT_FOUND);
    }
}
