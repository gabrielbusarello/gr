package br.univille.gr.api;

import br.univille.gr.model.Ferramenta;
import br.univille.gr.service.FerramentaService;
import br.univille.gr.util.Resposta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ferramenta")
@CrossOrigin(origins = "*")
public class FerramentaController {
    private FerramentaService ferramentaService;

    @GetMapping
    public ResponseEntity<Resposta<List<Ferramenta>>> listarFerramentas() {
        List<Ferramenta> lista = ferramentaService.getAll();
        Resposta<List<Ferramenta>> resposta = new Resposta<List<Ferramenta>>();

        if (lista.isEmpty()) {
            resposta.setStatus(2);
            resposta.setMensagem("Não há registros!");
        } else {
            resposta.setStatus(1);
            resposta.setData(lista);
        }

        return new ResponseEntity<Resposta<List<Ferramenta>>>(resposta, HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Resposta<Ferramenta>> buscaFerramentaPeloId(@PathVariable("id")long id) {
        Optional<Ferramenta> talvezFerramenta = ferramentaService.findById(id);
        Resposta<Ferramenta> resposta = new Resposta<Ferramenta>();

        if (!talvezFerramenta.isPresent()) {
            return naoEncontrado(resposta);
        }

        resposta.setStatus(1);
        resposta.setData(talvezFerramenta.get());

        return new ResponseEntity<Resposta<Ferramenta>>(resposta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Resposta<Ferramenta>> save(@RequestBody Ferramenta Ferramenta) {
        Ferramenta ferramenta = ferramentaService.save(Ferramenta);

        Resposta<Ferramenta> resposta = new Resposta<Ferramenta>();

        if(Ferramenta == null) {
            resposta.setStatus(3);
            resposta.setMensagem("Ferramenta não foi registrado!");
            return new ResponseEntity<Resposta<Ferramenta>>(resposta, HttpStatus.OK);
        }

        resposta.setStatus(1);
        resposta.setData(ferramenta);
        resposta.setMensagem("Ferramenta cadastrado com sucesso!");

        return new ResponseEntity<Resposta<Ferramenta>>(resposta, HttpStatus.OK);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<?> update(@PathVariable("id")long id, @RequestBody Ferramenta newFerramenta) {
        Optional<Ferramenta> talvezFerramenta = ferramentaService.findById(id);

        Resposta<Ferramenta> resposta = new Resposta<Ferramenta>();
        if (!talvezFerramenta.isPresent()) {
            return naoEncontrado(resposta);
        }

        Ferramenta oldFerramenta = talvezFerramenta.get();

        oldFerramenta.setNome(newFerramenta.getNome());
        oldFerramenta.setDtUltimoReparo(newFerramenta.getDtUltimoReparo());
        oldFerramenta.setProximoReparo(newFerramenta.getProximoReparo());

        Ferramenta FerramentaA = ferramentaService.save(oldFerramenta);

        if(FerramentaA == null) {
            resposta.setStatus(3);
            resposta.setMensagem("Ferramenta não foi alterada!");

            return new ResponseEntity<Resposta<Ferramenta>>(resposta, HttpStatus.OK);
        }

        resposta.setStatus(1);
        resposta.setData(FerramentaA);
        resposta.setMensagem("Ferramenta alterada com sucesso!");

        return new ResponseEntity<Resposta<Ferramenta>>(resposta, HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        Optional<Ferramenta> talvezFerramenta = ferramentaService.findById(id);
        Resposta<Ferramenta> resposta = new Resposta<Ferramenta>();
        if (!talvezFerramenta.isPresent()) {
            return naoEncontrado(resposta);
        }

        ferramentaService.delete(talvezFerramenta.get());

        resposta.setStatus(1);
        resposta.setMensagem("Tipo de Serviço excluída com sucesso!");

        return new ResponseEntity<Resposta<Ferramenta>>(resposta, HttpStatus.OK);
    }

    private ResponseEntity<Resposta<Ferramenta>> naoEncontrado(Resposta<Ferramenta> resposta) {
        resposta.setStatus(2);
        resposta.setMensagem("Ferramenta não encontrada!");
        return new ResponseEntity<Resposta<Ferramenta>>(resposta, HttpStatus.NOT_FOUND);
    }
}
