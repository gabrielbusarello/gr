package br.univille.gr.api;

import br.univille.gr.model.Usuario;
import br.univille.gr.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> lista = usuarioService.getAll();
        return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK);
    }
/*
    @GetMapping()
    public ModelAndView index() {
        List<Usuario> lista = usuarioService.getAll();
        return new ModelAndView("usuario/index", "usuarios", lista);
    }*/

}
