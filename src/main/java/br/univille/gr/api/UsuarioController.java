package br.univille.gr.api;

import br.univille.gr.model.Usuario;
import br.univille.gr.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> lista = usuarioService.getAll();
        return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK);
    }

    @GetMapping(path="{id}")
    public ResponseEntity<Usuario> buscaUsuarioPeloId(@PathVariable("id")long id) {
        Optional<Usuario> talvezUsuario = usuarioService.findById(id);
        if (!talvezUsuario.isPresent())
            return ResponseEntity.notFound().build();

        return new ResponseEntity<Usuario>(talvezUsuario.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Usuario usuario) {
        usuarioService.save(usuario);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<?> update(@PathVariable("id")long id, @RequestBody Usuario newUsuario) {
        Optional<Usuario> talvezUsuario = usuarioService.findById(id);
        if (!talvezUsuario.isPresent())
            return ResponseEntity.notFound().build();

        Usuario oldUsuario = talvezUsuario.get();

        oldUsuario.setCpf(newUsuario.getCpf());
        oldUsuario.setEmail(newUsuario.getEmail());
        oldUsuario.setMatricula(newUsuario.getMatricula());
        oldUsuario.setNome(newUsuario.getNome());
        oldUsuario.setCpf(newUsuario.getCpf());
        oldUsuario.setSenha(newUsuario.getSenha());
        oldUsuario.setTelefone(newUsuario.getTelefone());
        oldUsuario.setPermissao(newUsuario.getPermissao());

        usuarioService.save(oldUsuario);

        return ResponseEntity.ok().build();

    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        Optional<Usuario> talvezUsuario = usuarioService.findById(id);
        if (!talvezUsuario.isPresent())
            return ResponseEntity.notFound().build();

        usuarioService.delete(talvezUsuario.get());

        return ResponseEntity.ok().build();
    }
}
