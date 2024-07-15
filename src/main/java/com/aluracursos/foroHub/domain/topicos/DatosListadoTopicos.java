package com.aluracursos.foroHub.domain.topicos;

import com.aluracursos.foroHub.domain.usuarios.Usuario;
import java.time.LocalDateTime;

public record DatosListadoTopicos(
         String titulo,
         String mensaje,
         LocalDateTime fecha,
         String status,
         String nombreUsuario,
         String curso

) {
    public DatosListadoTopicos(Topico topico) {
        this(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha(),
                topico.getStatus() ? "Activo" : "Este tópico ya no está activo",
                topico.getUsuario().getUsername(), topico.getCurso());
    }
}
