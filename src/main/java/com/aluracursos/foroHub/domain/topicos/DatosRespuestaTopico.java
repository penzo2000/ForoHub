package com.aluracursos.foroHub.domain.topicos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        Boolean status,
        Long idUsuario,
        String curso,
        LocalDateTime fecha
) {

}
