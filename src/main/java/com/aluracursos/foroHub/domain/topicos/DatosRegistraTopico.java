package com.aluracursos.foroHub.domain.topicos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistraTopico(
        @NotNull
        @NotBlank
        String titulo,
        @NotNull
        @NotBlank
        String mensaje,
        @NotNull
        Long id_usuario,
        @NotNull
        @NotBlank
        String curso
) {
}
