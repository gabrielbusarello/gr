package br.univille.gr.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface JwtUserDetailsService {
    UserDetails loadUserByUsername(String username);
}
