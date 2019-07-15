package br.univille.gr.api;


import br.univille.gr.model.TipoServico;
import br.univille.gr.service.TipoServicoService;
import br.univille.gr.util.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipo-servico")
public class TipoServicoController {

    @Autowired
    private TipoServicoService tipoServicoService;

    @GetMapping
    public ResponseEntity<Resposta<List<TipoServico>>> listarTipoServicos() {
        List<TipoServico> lista = tipoServicoService.getAll();
        Resposta<List<TipoServico>> resposta = new Resposta<List<TipoServico>>();

        if (lista.isEmpty()) {
            resposta.setStatus(2);
            resposta.setMensagem("Não há registros!");
        } else {
            resposta.setStatus(1);
            resposta.setData(lista);
        }

        return new ResponseEntity<Resposta<List<TipoServico>>>(resposta, HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Resposta<TipoServico>> buscaTipoServicoPeloId(@PathVariable("id")long id) {
        Optional<TipoServico> talvezTipoServico = tipoServicoService.findById(id);
        Resposta<TipoServico> resposta = new Resposta<TipoServico>();

        if (!talvezTipoServico.isPresent()) {
            return naoEncontrado(resposta);
        }

        resposta.setStatus(1);
        resposta.setData(talvezTipoServico.get());

        return new ResponseEntity<Resposta<TipoServico>>(resposta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Resposta<TipoServico>> save(@RequestBody TipoServico TipoServico) {
        TipoServico tipoServico = tipoServicoService.save(TipoServico);

        Resposta<TipoServico> resposta = new Resposta<TipoServico>();

        if(TipoServico == null) {
            resposta.setStatus(3);
            resposta.setMensagem("Tipo de serviço não foi registrado!");
            return new ResponseEntity<Resposta<TipoServico>>(resposta, HttpStatus.OK);
        }

        resposta.setStatus(1);
        resposta.setData(tipoServico);
        resposta.setMensagem("Tipo de serviço cadastrado com sucesso!");

        return new ResponseEntity<Resposta<TipoServico>>(resposta, HttpStatus.OK);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<?> update(@PathVariable("id")long id, @RequestBody TipoServico newTipoServico) {
        Optional<TipoServico> talvezTipoServico = tipoServicoService.findById(id);

        Resposta<TipoServico> resposta = new Resposta<TipoServico>();
        if (!talvezTipoServico.isPresent()) {
            return naoEncontrado(resposta);
        }

        TipoServico oldTipoServico = talvezTipoServico.get();

        oldTipoServico.setNome(newTipoServico.getNome());
        oldTipoServico.setDescricao(newTipoServico.getDescricao());
        oldTipoServico.setValorEstimado(newTipoServico.getValorEstimado());

        TipoServico TipoServicoA = tipoServicoService.save(oldTipoServico);

        if(TipoServicoA == null) {
            resposta.setStatus(3);
            resposta.setMensagem("Tipo de serviço não foi alterado!");

            return new ResponseEntity<Resposta<TipoServico>>(resposta, HttpStatus.OK);
        }

        resposta.setStatus(1);
        resposta.setData(TipoServicoA);
        resposta.setMensagem("Tipo de serviço alterado com sucesso!");

        return new ResponseEntity<Resposta<TipoServico>>(resposta, HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        Optional<TipoServico> talvezTipoServico = tipoServicoService.findById(id);
        Resposta<TipoServico> resposta = new Resposta<TipoServico>();
        if (!talvezTipoServico.isPresent()) {
            return naoEncontrado(resposta);
        }

        tipoServicoService.delete(talvezTipoServico.get());

        resposta.setStatus(1);
        resposta.setMensagem("Tipo de Serviço excluída com sucesso!");

        return new ResponseEntity<Resposta<TipoServico>>(resposta, HttpStatus.OK);
    }

    private ResponseEntity<Resposta<TipoServico>> naoEncontrado(Resposta<TipoServico> resposta) {
        resposta.setStatus(2);
        resposta.setMensagem("Tipo de Serviço não encontrado!");
        return new ResponseEntity<Resposta<TipoServico>>(resposta, HttpStatus.NOT_FOUND);
    }
}
