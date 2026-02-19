package com.proyecto.forohub.service;

import com.proyecto.forohub.repository.TopicoRepository;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    private final TopicoRepository repository;

    public TopicoService(TopicoRepository repository) {
        this.repository = repository;
    }

}
