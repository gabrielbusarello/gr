package br.univille.gr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsuarioController {

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "Hello World!!";
    }

}
