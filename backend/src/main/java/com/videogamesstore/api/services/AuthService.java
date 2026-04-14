package com.videogamesstore.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.videogamesstore.api.dto.RegistroRequest;
import com.videogamesstore.api.entities.Rol;
import com.videogamesstore.api.entities.Usuario;
import com.videogamesstore.api.repositories.RolRepository;
import com.videogamesstore.api.repositories.UsuarioRepository;

@Service
public class AuthService {

    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private RolRepository rolRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    // Método para el RF-01: Registro
    public Usuario registrar(RegistroRequest request) {
        // Validar si el correo ya existe
        if(usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("El correo ya está en uso");
        }

        // Obtener el rol CLIENTE (id 2) que insertamos en pgAdmin
        Rol rolCliente = rolRepository.findById(2)
                .orElseThrow(() -> new RuntimeException("Error: Rol base no configurado en la BD"));

        Usuario usuario = new Usuario();
        usuario.setNickname(request.getNickname());
        usuario.setEmail(request.getEmail());
        
        // Encriptar contraseña con BCrypt antes de guardar
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol(rolCliente);

        return usuarioRepository.save(usuario);
    }

    // Método para el RF-02: Inicio de sesión
    public String iniciarSesion(String email, String rawPassword) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Comparamos la contraseña enviada con la encriptada en la BD
        if (passwordEncoder.matches(rawPassword, usuario.getPassword())) {
            return "¡Login Exitoso! Bienvenido " + usuario.getNickname();
        } else {
            throw new RuntimeException("Contraseña incorrecta");
        }
    }
}