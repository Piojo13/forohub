package com.proyecto.forohub.dto;

import com.proyecto.forohub.model.Topico;
import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        String estado,
        String autor,
        String curso,
        LocalDateTime fechaCreacion
) {
    public DatosListadoTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getEstado(),
                topico.getAutor(),
                topico.getCurso(),
                topico.getFechaCreacion()
        );
    }
}
