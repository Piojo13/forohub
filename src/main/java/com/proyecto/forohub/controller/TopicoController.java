package com.proyecto.forohub.controller;

import com.proyecto.forohub.dto.DatosListadoTopico;
import com.proyecto.forohub.dto.DatosRegistroTopico;
import com.proyecto.forohub.model.Topico;
import com.proyecto.forohub.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoRepository repository;

    public TopicoController(TopicoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<DatosListadoTopico> listar() {
        return repository.findAll()
                .stream()
                .map(DatosListadoTopico::new)
                .toList();
    }

    @GetMapping("/{id}")
    public DatosListadoTopico detalle(@PathVariable Long id) {
        var topico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado con ID: " + id));

        return new DatosListadoTopico(topico);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DatosListadoTopico registrar(@RequestBody @Valid DatosRegistroTopico datos) {

        repository.findByTituloAndMensaje(datos.titulo(), datos.mensaje())
                .ifPresent(topico -> {
                    throw new RuntimeException("Tópico duplicado");
                });

        Topico topico = new Topico(
                datos.titulo(),
                datos.mensaje(),
                datos.estado(),
                datos.autor(),
                datos.curso()
        );

        Topico topicoGuardado = repository.save(topico);

        return new DatosListadoTopico(topicoGuardado);
    }


    @PutMapping("/{id}")
    public DatosListadoTopico actualizar(@PathVariable Long id,
                                         @RequestBody @Valid DatosRegistroTopico datos) {

        Topico topico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado con id: " + id));

        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        topico.setEstado(datos.estado());
        topico.setAutor(datos.autor());
        topico.setCurso(datos.curso());

        Topico topicoActualizado = repository.save(topico);

        return new DatosListadoTopico(topicoActualizado);
    }


    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        Topico topico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));

        repository.delete(topico);
    }

}