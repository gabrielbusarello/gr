package br.univille.gr.controller;

import br.univille.gr.model.Usuario;
import br.univille.gr.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    public ModelAndView index() {
        List<Usuario> lista = usuarioService.getAll();
        return new ModelAndView("usuario/index", "usuarios", lista);
    }

}
