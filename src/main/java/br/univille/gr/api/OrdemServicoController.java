package br.univille.gr.api;

import br.univille.gr.model.OrdemServico;
import br.univille.gr.service.OrdemServicoService;
import br.univille.gr.util.Resposta;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ordem-servico")
public class OrdemServicoController {
    @Autowired
    private OrdemServicoService ordemServicoService;

    @GetMapping
    public ResponseEntity<Resposta<List<OrdemServico>>> listarOrdemServico() {
        List<OrdemServico> lista = ordemServicoService.getAll();
        Resposta<List<OrdemServico>> resposta = new Resposta<List<OrdemServico>>();
        if (lista.isEmpty()) {
            resposta.setStatus(2);
            resposta.setMensagem("Não há registros!");
        } else {
            resposta.setStatus(1);
            resposta.setData(lista);
        }
        return new ResponseEntity<Resposta<List<OrdemServico>>>(resposta, HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Resposta<OrdemServico>> buscaOrdemServicoPeloId(@PathVariable("id")long id) {
        Optional<OrdemServico> talvezAgenda = ordemServicoService.findById(id);
        Resposta<OrdemServico> resposta = new Resposta<OrdemServico>();
        if (!talvezAgenda.isPresent()) {
            return this.naoEncontrado(resposta);
        }
        resposta.setStatus(1);
        resposta.setData(talvezAgenda.get());

        return new ResponseEntity<Resposta<OrdemServico>>(resposta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Resposta<OrdemServico>> save(@RequestBody OrdemServico ordemServico) {
        OrdemServico ordemServicoI = ordemServicoService.save(OrdemServico);
        Resposta<OrdemServico> resposta = new Resposta<OrdemServico>();
        if(OrdemServico == null) {
            resposta.setStatus(3);
            resposta.setMensagem("Agenda não foi registrada!");
            return new ResponseEntity<Resposta<OrdemServico>>(resposta, HttpStatus.OK);
        }
        resposta.setStatus(1);
        resposta.setData(agendaI);
        resposta.setMensagem("Agenda cadastrada com sucesso!");
        return new ResponseEntity<Resposta<OrdemServico>>(resposta, HttpStatus.OK);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<Resposta<OrdemServico>> update(@PathVariable("id")long id, @RequestBody Agenda newAgenda) {
        Optional<OrdemServico> talvezAgenda = agendaService.findById(id);
        Resposta<OrdemServico> resposta = new Resposta<OrdemServico>();
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
            return new ResponseEntity<Resposta<OrdemServico>>(resposta, HttpStatus.OK);
        }
        resposta.setStatus(1);
        resposta.setData(agendaA);
        resposta.setMensagem("Agenda alterada com sucesso!");
        return new ResponseEntity<Resposta<OrdemServico>>(resposta, HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Resposta<OrdemServico>> delete(@PathVariable("id") long id){
        Optional<OrdemServico> talvezAgenda = agendaService.findById(id);
        Resposta<OrdemServico> resposta = new Resposta<OrdemServico>();
        if (!talvezAgenda.isPresent()) {
            return naoEncontrado(resposta);
        }

        agendaService.delete(talvezAgenda.get());

        resposta.setStatus(1);
        resposta.setMensagem("Agenda excluída com sucesso!");

        return new ResponseEntity<Resposta<OrdemServico>>(resposta, HttpStatus.OK);
    }

    private ResponseEntity<Resposta<OrdemServico>> naoEncontrado(Resposta<OrdemServico> resposta) {
        resposta.setStatus(2);
        resposta.setMensagem("Agenda não encontrada!");
        return new ResponseEntity<Resposta<OrdemServico>>(resposta, HttpStatus.NOT_FOUND);
    }
}
