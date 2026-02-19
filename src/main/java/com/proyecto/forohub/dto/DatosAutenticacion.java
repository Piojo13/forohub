package com.proyecto.forohub.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacion(
        @NotBlank
        String username,
        @NotBlank
        String password
) {}
