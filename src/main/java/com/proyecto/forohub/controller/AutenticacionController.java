package com.proyecto.forohub.controller;

import com.proyecto.forohub.dto.DatosAutenticacion;
import com.proyecto.forohub.model.Usuario;
import com.proyecto.forohub.repository.UsuarioRepository;
import com.proyecto.forohub.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    private final AuthenticationManager authManager;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AutenticacionController(AuthenticationManager authManager,
                                   UsuarioRepository usuarioRepository,
                                   PasswordEncoder passwordEncoder,
                                   TokenService tokenService) {
        this.authManager = authManager;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }


    @PostMapping
    public String autenticar(@RequestBody @Valid DatosAutenticacion datos) {

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        datos.username(),
                        datos.password()
                )
        );

        Usuario usuario = (Usuario) authentication.getPrincipal();

        return tokenService.generarToken(usuario);
    }



    @PostMapping("/registrar")
    public String registrar(@RequestBody @Valid DatosAutenticacion datos) {

        if (usuarioRepository.findByUsername(datos.username()).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        Usuario usuario = new Usuario(
                datos.username(),
                passwordEncoder.encode(datos.password())
        );

        usuarioRepository.save(usuario);

        return "Usuario registrado correctamente";
    }
}