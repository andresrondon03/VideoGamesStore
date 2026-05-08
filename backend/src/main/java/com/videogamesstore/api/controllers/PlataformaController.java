package com.videogamesstore.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videogamesstore.api.entities.Plataforma;
import com.videogamesstore.api.repositories.PlataformaRepository;

@RestController
@RequestMapping("/api/plataformas")
public class PlataformaController {

    @Autowired
    private PlataformaRepository plataformaRepository;

    @GetMapping
    public List<Plataforma> listarPlataformas() {
        return plataformaRepository.findAll();
    }
}
