package br.univille.gr.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -7858869558953243875L;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        final String unable = (String) request.getAttribute("unable");
        final String expired = (String) request.getAttribute("expired");
        final String bearer = (String) request.getAttribute("bearer");
        if (unable != null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, unable);
        } else if (expired != null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, expired);
        } else if (bearer != null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, bearer);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"É necessário estar autenticado para utilizar esse recurso");
        }
    }
}

