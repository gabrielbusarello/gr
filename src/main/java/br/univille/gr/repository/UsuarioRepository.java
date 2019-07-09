package br.univille.gr.repository;

import br.univille.gr.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByCpfAndSenha(String cpf, String senha);
    Usuario findByCpf(String cpf);
}
