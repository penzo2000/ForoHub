package com.aluracursos.foroHub.domain.topicos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        String status,
        Long id_usuario,
        String curso,
        LocalDateTime fecha
) {

    public DatosRespuestaTopico(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getStatus() ? "Activo" : "Este tópico ya no está activo",
                topico.getUsuario().getId(),
                topico.getCurso(),
                topico.getFecha());
    }
}
