package com.videogamesstore.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.videogamesstore.api.dto.RegistroRequest;
import com.videogamesstore.api.entities.Rol;
import com.videogamesstore.api.entities.Usuario;
import com.videogamesstore.api.repositories.RolRepository;
import com.videogamesstore.api.repositories.UsuarioRepository;
import com.videogamesstore.api.security.JwtUtil;

@Service
public class AuthService {

    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private RolRepository rolRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtUtil jwtUtil;

    public Usuario registrar(RegistroRequest request) {
        if(usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("El correo ya está en uso");
        }

        // Corrección: Búsqueda segura por nombre de rol
        Rol rolCliente = rolRepository.findByRol("CLIENTE")
                .orElseThrow(() -> new RuntimeException("Error: Rol 'CLIENTE' no configurado en la BD"));

        Usuario usuario = new Usuario();
        usuario.setNickname(request.getNickname());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol(rolCliente);

        return usuarioRepository.save(usuario);
    }

    public String iniciarSesion(String email, String rawPassword) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (passwordEncoder.matches(rawPassword, usuario.getPassword())) {
            return jwtUtil.generarToken(usuario.getEmail(), usuario.getRol().getRol());
        } else {
            throw new RuntimeException("Contraseña incorrecta");
        }
    }
}