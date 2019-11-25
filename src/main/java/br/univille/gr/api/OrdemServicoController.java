package br.univille.gr.api;

import br.univille.gr.model.Agenda;
import br.univille.gr.model.OrdemServico;
import br.univille.gr.model.Produto;
import br.univille.gr.service.AgendaService;
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
    @Autowired
    private AgendaService agendaService;

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
    public ResponseEntity<Resposta<OrdemServico>> save(@RequestBody OrdemServico OrdemServico) {
        Agenda agenda = agendaService.findById(OrdemServico.getAgenda().getId()).get();
        agenda.setStatus('F');
        this.agendaService.save(agenda);
        OrdemServico ordemServicoI = ordemServicoService.save(OrdemServico);
        Resposta<OrdemServico> resposta = new Resposta<OrdemServico>();
        if(OrdemServico == null) {
            resposta.setStatus(3);
            resposta.setMensagem("Ordem de serviço não foi registrada!");
            return new ResponseEntity<Resposta<OrdemServico>>(resposta, HttpStatus.OK);
        }

        resposta.setStatus(1);
        resposta.setData(ordemServicoI);
        resposta.setMensagem("Ordem de serviço cadastrada com sucesso!");
        return new ResponseEntity<Resposta<OrdemServico>>(resposta, HttpStatus.OK);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<Resposta<OrdemServico>> update(@PathVariable("id")long id, @RequestBody OrdemServico newOrdemServico) {
        Optional<OrdemServico> talvezOrdemServico = ordemServicoService.findById(id);
        Resposta<OrdemServico> resposta = new Resposta<OrdemServico>();
        if (!talvezOrdemServico.isPresent()) {
            return naoEncontrado(resposta);
        }

        OrdemServico oldOrdemServico = talvezOrdemServico.get();

        oldOrdemServico.setDescricao(newOrdemServico.getDescricao());
        oldOrdemServico.setData(newOrdemServico.getData());
        oldOrdemServico.setHora(newOrdemServico.getHora());
        oldOrdemServico.setProduto(newOrdemServico.getProduto());
        oldOrdemServico.setServico(newOrdemServico.getServico());

//        for (Produto umProduto:newOrdemServico.getProduto()) {
//            umProduto.setOrdemServico(oldOrdemServico);
//        }

        OrdemServico ordemServicoA = ordemServicoService.save(oldOrdemServico);

        if(ordemServicoA == null) {
            resposta.setStatus(3);
            resposta.setMensagem("Ordem de serviço não foi alterada!");
            return new ResponseEntity<Resposta<OrdemServico>>(resposta, HttpStatus.OK);
        }
        resposta.setStatus(1);
        resposta.setData(ordemServicoA);
        resposta.setMensagem("Ordem de serviço alterada com sucesso!");
        return new ResponseEntity<Resposta<OrdemServico>>(resposta, HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Resposta<OrdemServico>> delete(@PathVariable("id") long id){
        Optional<OrdemServico> talvezOrdemServico = ordemServicoService.findById(id);
        Resposta<OrdemServico> resposta = new Resposta<OrdemServico>();
        if (!talvezOrdemServico.isPresent()) {
            return naoEncontrado(resposta);
        }

        ordemServicoService.delete(talvezOrdemServico.get());

        resposta.setStatus(1);
        resposta.setMensagem("Ordem de serviço excluída com sucesso!");

        return new ResponseEntity<Resposta<OrdemServico>>(resposta, HttpStatus.OK);
    }

    private ResponseEntity<Resposta<OrdemServico>> naoEncontrado(Resposta<OrdemServico> resposta) {
        resposta.setStatus(2);
        resposta.setMensagem("Ordem de serviço não encontrada!");
        return new ResponseEntity<Resposta<OrdemServico>>(resposta, HttpStatus.NOT_FOUND);
    }
}
