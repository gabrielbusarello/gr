package br.univille.gr.api;

import br.univille.gr.model.Usuario;
import br.univille.gr.service.UsuarioService;
import br.univille.gr.util.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Resposta<List<Usuario>>> listarUsuarios() {
        List<Usuario> lista = usuarioService.getAll();
        Resposta<List<Usuario>> resposta = new Resposta<List<Usuario>>();
        if (lista.isEmpty()) {
            resposta.setStatus(2);
            resposta.setMensagem("Não há registros!");
        } else {
            resposta.setStatus(1);
            resposta.setData(lista);
        }
        return new ResponseEntity<Resposta<List<Usuario>>>(resposta, HttpStatus.OK);
    }

    // Usar de exemplo para filtragem - Depois retirar
    @GetMapping(params = { "user", "password" })
    public ResponseEntity<Resposta<Usuario>> buscaUsuarioESenha(@RequestParam String user, @RequestParam String password) {
        Usuario usuario = usuarioService.findByUserAndPassword(user, password);
        Resposta<Usuario> resposta = new Resposta<Usuario>();
        if (usuario == null) {
            resposta.setStatus(2);
            resposta.setMensagem("Usuário não encontrado!");
            return new ResponseEntity<Resposta<Usuario>>(resposta, HttpStatus.NOT_FOUND);
        }
        resposta.setStatus(1);
        resposta.setData(usuario);

        return new ResponseEntity<Resposta<Usuario>>(resposta, HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Resposta<Usuario>> buscaUsuarioPeloId(@PathVariable("id")long id) {
        Optional<Usuario> talvezUsuario = usuarioService.findById(id);
        Resposta<Usuario> resposta = new Resposta<Usuario>();
        if (!talvezUsuario.isPresent()) {
            resposta.setStatus(2);
            resposta.setMensagem("Usuário não encontrado!");
            return new ResponseEntity<Resposta<Usuario>>(resposta, HttpStatus.NOT_FOUND);
        }
        talvezUsuario.get().setSenha(null);
        resposta.setStatus(1);
        resposta.setData(talvezUsuario.get());

        return new ResponseEntity<Resposta<Usuario>>(resposta, HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<Resposta<Usuario>> save(@RequestBody Usuario usuario) {
//        Usuario usuarioI = usuarioService.save(usuario);
//        Resposta<Usuario> resposta = new Resposta<Usuario>();
//        if(usuario == null) {
//            resposta.setStatus(3);
//            resposta.setMensagem("Usuário não foi registrado!");
//            return new ResponseEntity<Resposta<Usuario>>(resposta, HttpStatus.OK);
//        }
//        resposta.setStatus(1);
//        resposta.setData(usuarioI);
//        resposta.setMensagem("Usuário cadastrado com sucesso!");
//        return new ResponseEntity<Resposta<Usuario>>(resposta, HttpStatus.OK);
//    }

    @PutMapping(path="/{id}")
    public ResponseEntity<Resposta<Usuario>> update(@PathVariable("id")long id, @RequestBody Usuario newUsuario) {
        Optional<Usuario> talvezUsuario = usuarioService.findById(id);
        if (!talvezUsuario.isPresent())
            return ResponseEntity.notFound().build();

        Usuario oldUsuario = talvezUsuario.get();

        oldUsuario.setEmail(newUsuario.getEmail());
        oldUsuario.setNome(newUsuario.getNome());
        oldUsuario.setTelefone(newUsuario.getTelefone());
        oldUsuario.setPermissao(newUsuario.getPermissao());

        Usuario usuarioA = usuarioService.save(oldUsuario);

        Resposta<Usuario> resposta = new Resposta<Usuario>();
        if(usuarioA == null) {
            resposta.setStatus(3);
            resposta.setMensagem("Usuário não foi alterado!");
            return new ResponseEntity<Resposta<Usuario>>(resposta, HttpStatus.OK);
        }
        resposta.setStatus(1);
        resposta.setData(usuarioA);
        resposta.setMensagem("Usuário alterado com sucesso!");
        return new ResponseEntity<Resposta<Usuario>>(resposta, HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Resposta<Usuario>> delete(@PathVariable("id") long id){
        Optional<Usuario> talvezUsuario = usuarioService.findById(id);
        if (!talvezUsuario.isPresent())
            return ResponseEntity.notFound().build();

        usuarioService.delete(talvezUsuario.get());

        Resposta<Usuario> resposta = new Resposta<Usuario>();
        resposta.setStatus(1);
        resposta.setMensagem("Usuário excluído com sucesso!");

        return new ResponseEntity<Resposta<Usuario>>(resposta, HttpStatus.OK);
    }
}
