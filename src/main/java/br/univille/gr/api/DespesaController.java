package br.univille.gr.api;

import br.univille.gr.model.Despesa;
import br.univille.gr.service.DespesaService;
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
@RequestMapping("/despesa")
public class DespesaController {

    @Autowired
    DespesaService despesaService;

    @GetMapping
    public ResponseEntity<Resposta<List<Despesa>>> listarDespesas() {
        List<Despesa> lista = despesaService.getAll();
        Resposta<List<Despesa>> resposta = new Resposta<List<Despesa>>();
        if (lista.isEmpty()) {
            resposta.setStatus(2);
            resposta.setMensagem("Não há registros!");
        } else {
            resposta.setStatus(1);
            resposta.setData(lista);
        }
        return new ResponseEntity<Resposta<List<Despesa>>>(resposta, HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Resposta<Despesa>> buscaDespesaPeloId(@PathVariable("id")long id) {
        Optional<Despesa> talvezDespesa = despesaService.findById(id);
        Resposta<Despesa> resposta = new Resposta<Despesa>();
        if (!talvezDespesa.isPresent()) {
            return this.naoEncontrado(resposta);
        }
        resposta.setStatus(1);
        resposta.setData(talvezDespesa.get());

        return new ResponseEntity<Resposta<Despesa>>(resposta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Resposta<Despesa>> save(@RequestBody Despesa Despesa) {
        Despesa despesaI = despesaService.save(Despesa);
        Resposta<Despesa> resposta = new Resposta<Despesa>();
        if(Despesa == null) {
            resposta.setStatus(3);
            resposta.setMensagem("Despesa não foi registrada!");
            return new ResponseEntity<Resposta<Despesa>>(resposta, HttpStatus.OK);
        }
        resposta.setStatus(1);
        resposta.setData(despesaI);
        resposta.setMensagem("Despesa cadastrada com sucesso!");
        return new ResponseEntity<Resposta<Despesa>>(resposta, HttpStatus.OK);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<Resposta<Despesa>> update(@PathVariable("id")long id, @RequestBody Despesa newDespesa) {
        Optional<Despesa> talvezDespesa = despesaService.findById(id);
        Resposta<Despesa> resposta = new Resposta<Despesa>();
        if (!talvezDespesa.isPresent()) {
            return naoEncontrado(resposta);
        }

        Despesa oldDespesa = talvezDespesa.get();

        oldDespesa.setNome(newDespesa.getNome());
        oldDespesa.setDescricao(newDespesa.getDescricao());
        oldDespesa.setValor(newDespesa.getValor());
        oldDespesa.setData(newDespesa.getData());
        oldDespesa.setHora(newDespesa.getHora());

        Despesa despesaA = despesaService.save(oldDespesa);

        if(despesaA == null) {
            resposta.setStatus(3);
            resposta.setMensagem("Despesa não foi alterada!");
            return new ResponseEntity<Resposta<Despesa>>(resposta, HttpStatus.OK);
        }
        resposta.setStatus(1);
        resposta.setData(despesaA);
        resposta.setMensagem("Despesa alterada com sucesso!");
        return new ResponseEntity<Resposta<Despesa>>(resposta, HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Resposta<Despesa>> delete(@PathVariable("id") long id){
        Optional<Despesa> talvezDespesa = despesaService.findById(id);
        Resposta<Despesa> resposta = new Resposta<Despesa>();
        if (!talvezDespesa.isPresent()) {
            return naoEncontrado(resposta);
        }

        despesaService.delete(talvezDespesa.get());

        resposta.setStatus(1);
        resposta.setMensagem("Despesa excluída com sucesso!");

        return new ResponseEntity<Resposta<Despesa>>(resposta, HttpStatus.OK);
    }

    private ResponseEntity<Resposta<Despesa>> naoEncontrado(Resposta<Despesa> resposta) {
        resposta.setStatus(2);
        resposta.setMensagem("Despesa não encontrada!");
        return new ResponseEntity<Resposta<Despesa>>(resposta, HttpStatus.NOT_FOUND);
    }
}
