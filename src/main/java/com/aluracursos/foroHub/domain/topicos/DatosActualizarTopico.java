package com.aluracursos.foroHub.domain.topicos;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        String titulo,
        String mensaje,
        Long id_usuario,
        String curso
) {
}
