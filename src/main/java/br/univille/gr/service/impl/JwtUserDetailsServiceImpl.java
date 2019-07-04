package br.univille.gr.service.impl;

import br.univille.gr.model.Usuario;
import br.univille.gr.repository.UsuarioRepository;
import br.univille.gr.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService, UserDetailsService {
    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Usuario usuario = repository.findByCpf(username);
        if (usuario.getCpf().equals(username)) {
            return new User(usuario.getCpf(), usuario.getSenha(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username" + username);
        }
    }
}
