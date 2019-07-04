package br.univille.gr.api;

import br.univille.gr.config.JwtTokenUtil;
import br.univille.gr.model.JwtRequest;
import br.univille.gr.model.JwtResponse;
import br.univille.gr.model.Usuario;
import br.univille.gr.service.JwtUserDetailsService;
import br.univille.gr.service.UsuarioService;
import br.univille.gr.util.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<Resposta<JwtResponse>> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        Resposta<JwtResponse> resposta = new Resposta<JwtResponse>();
        Usuario usuario = usuarioService.findByUser(userDetails.getUsername());
        resposta.setStatus(1);
        resposta.setData(new JwtResponse(token, usuario));

        return new ResponseEntity<Resposta<JwtResponse>>(resposta, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
