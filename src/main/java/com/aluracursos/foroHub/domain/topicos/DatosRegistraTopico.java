package com.aluracursos.foroHub.domain.topicos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistraTopico(
        @NotNull
        String titulo,
        @NotNull
        String mensaje,
        @NotNull
        Long idUsuario,
        @NotNull
        String curso
) {
}
