package br.univille.gr.api;

import br.univille.gr.model.*;
import br.univille.gr.service.AgendaService;
import br.univille.gr.service.AvaliacaoService;
import br.univille.gr.service.FerramentaService;
import br.univille.gr.service.OrdemServicoService;
import br.univille.gr.util.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Autowired
    private OrdemServicoService ordemServicoService;

    @GetMapping
    public ResponseEntity<Resposta<List<Avaliacao>>> listarAvaliacaos(@RequestParam long idOrdemServico) {
        Optional<OrdemServico> ordemServico = ordemServicoService.findById(idOrdemServico);

        Resposta<List<Avaliacao>> resposta = new Resposta<List<Avaliacao>>();

        if (!ordemServico.isPresent()){
            resposta.setStatus(3);
            resposta.setMensagem("Avaliação não existente!");
        }

        List<Avaliacao> lista = avaliacaoService.findByOrdemServico(ordemServico.get());

        for (int i = 0; i < lista.size(); i++){
            lista.get(i).setOrdemServico(null);
        }

        if (lista.isEmpty()) {
            resposta.setStatus(2);
            resposta.setMensagem("Não há registros!");
        } else {
            resposta.setStatus(1);
            resposta.setData(lista);
        }

        return new ResponseEntity<Resposta<List<Avaliacao>>>(resposta, HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Resposta<Avaliacao>> buscaAvaliacaoPeloId(@PathVariable("id")long id) {
        Optional<Avaliacao> talvezAvaliacao = avaliacaoService.findById(id);
        Resposta<Avaliacao> resposta = new Resposta<Avaliacao>();

        if (!talvezAvaliacao.isPresent()) {
            return naoEncontrado(resposta);
        }

        resposta.setStatus(1);
        resposta.setData(talvezAvaliacao.get());

        return new ResponseEntity<Resposta<Avaliacao>>(resposta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Resposta<Avaliacao>> save(@RequestBody Avaliacao Avaliacao) {
        Avaliacao avaliacao = avaliacaoService.save(Avaliacao);

        Resposta<Avaliacao> resposta = new Resposta<Avaliacao>();

        if(Avaliacao == null) {
            resposta.setStatus(3);
            resposta.setMensagem("Avaliacao não foi registrada!");
            return new ResponseEntity<Resposta<Avaliacao>>(resposta, HttpStatus.OK);
        }

        resposta.setStatus(1);
        resposta.setData(avaliacao);
        resposta.setMensagem("Avaliacao cadastrada com sucesso!");

        return new ResponseEntity<Resposta<Avaliacao>>(resposta, HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        Optional<Avaliacao> talvezAvaliacao = avaliacaoService.findById(id);
        Resposta<Avaliacao> resposta = new Resposta<Avaliacao>();
        if (!talvezAvaliacao.isPresent()) {
            return naoEncontrado(resposta);
        }

        avaliacaoService.delete(talvezAvaliacao.get());

        resposta.setStatus(1);
        resposta.setMensagem("Avaliacao excluída com sucesso!");

        return new ResponseEntity<Resposta<Avaliacao>>(resposta, HttpStatus.OK);
    }

    private ResponseEntity<Resposta<Avaliacao>> naoEncontrado(Resposta<Avaliacao> resposta) {
        resposta.setStatus(2);
        resposta.setMensagem("Avaliacao não encontrada!");
        return new ResponseEntity<Resposta<Avaliacao>>(resposta, HttpStatus.NOT_FOUND);
    }
}
