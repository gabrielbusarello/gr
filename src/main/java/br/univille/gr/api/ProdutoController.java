package br.univille.gr.api;

import br.univille.gr.model.Produto;
import br.univille.gr.service.ProdutoService;
import br.univille.gr.util.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Resposta<List<Produto>>> listarProdutos() {
        List<Produto> lista = produtoService.getAll();
        Resposta<List<Produto>> resposta = new Resposta<List<Produto>>();

        if (lista.isEmpty()) {
            resposta.setStatus(2);
            resposta.setMensagem("Não há registros!");
        } else {
            resposta.setStatus(1);
            resposta.setData(lista);
        }

        return new ResponseEntity<Resposta<List<Produto>>>(resposta, HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Resposta<Produto>> buscaTipoServicoPeloId(@PathVariable("id")long id) {
        Optional<Produto> talvezProduto = produtoService.findById(id);
        Resposta<Produto> resposta = new Resposta<Produto>();

        if (!talvezProduto.isPresent()) {
            return naoEncontrado(resposta);
        }

        resposta.setStatus(1);
        resposta.setData(talvezProduto.get());

        return new ResponseEntity<Resposta<Produto>>(resposta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Resposta<Produto>> save(@RequestBody Produto Produto) {
        Produto produto = produtoService.save(Produto);

        Resposta<Produto> resposta = new Resposta<Produto>();

        if(Produto == null) {
            resposta.setStatus(3);
            resposta.setMensagem("Produto não foi registrado!");
            return new ResponseEntity<Resposta<Produto>>(resposta, HttpStatus.OK);
        }

        resposta.setStatus(1);
        resposta.setData(produto);
        resposta.setMensagem("Produto cadastrado com sucesso!");

        return new ResponseEntity<Resposta<Produto>>(resposta, HttpStatus.OK);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<?> update(@PathVariable("id")long id, @RequestBody Produto newProduto) {
        Optional<Produto> talvezProduto = produtoService.findById(id);

        Resposta<Produto> resposta = new Resposta<Produto>();
        if (!talvezProduto.isPresent()) {
            return naoEncontrado(resposta);
        }

        Produto oldProduto = talvezProduto.get();

        oldProduto.setNome(newProduto.getNome());
        oldProduto.setValor(newProduto.getValor());
        oldProduto.setQuantidade(newProduto.getQuantidade());

        Produto ProdutoA = produtoService.save(oldProduto);

        if(ProdutoA == null) {
            resposta.setStatus(3);
            resposta.setMensagem("Produto não foi alterado!");

            return new ResponseEntity<Resposta<Produto>>(resposta, HttpStatus.OK);
        }

        resposta.setStatus(1);
        resposta.setData(ProdutoA);
        resposta.setMensagem("Produto alterado com sucesso!");

        return new ResponseEntity<Resposta<Produto>>(resposta, HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        Optional<Produto> talvezProduto = produtoService.findById(id);
        Resposta<Produto> resposta = new Resposta<Produto>();
        if (!talvezProduto.isPresent()) {
            return naoEncontrado(resposta);
        }

        produtoService.delete(talvezProduto.get());

        resposta.setStatus(1);
        resposta.setMensagem("Produto excluído com sucesso!");

        return new ResponseEntity<Resposta<Produto>>(resposta, HttpStatus.OK);
    }

    private ResponseEntity<Resposta<Produto>> naoEncontrado(Resposta<Produto> resposta) {
        resposta.setStatus(2);
        resposta.setMensagem("Produto não encontrado!");
        return new ResponseEntity<Resposta<Produto>>(resposta, HttpStatus.NOT_FOUND);
    }
}
