package br.univille.gr.api;

import br.univille.gr.model.Usuario;
import br.univille.gr.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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

    /*@GetMapping()
    public ModelAndView index() {
        List<Usuario> lista = usuarioService.getAll();
        return new ModelAndView("usuario/index", "usuarios", lista);
    }

    @GetMapping("/novo")
    public ModelAndView createForm(@ModelAttribute Usuario usuario) {
        return new ModelAndView("usuario/form");
    }

    @PostMapping(params="form")
    public ModelAndView save(@Valid Usuario usuario) {
        usuarioService.save(usuario);
        return new ModelAndView("redirect:/usuario");
    }

    @GetMapping("/edit/${id}")
    public ModelAndView edit(@PathVariable("id") Usuario usuario) {
        return new ModelAndView("usuario/form", "usuario", usuario);
    }

    @GetMapping("/delete/${id}")
    public ModelAndView delete(@PathVariable("id") Usuario usuario) {
        usuarioService.delete(usuario);
        return new ModelAndView("redirect:/usuario");
    }*/
}
