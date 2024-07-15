package com.aluracursos.foroHub.domain.topicos;

import com.aluracursos.foroHub.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha;
    private Boolean status;
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "idUsuario")
    private Usuario usuario;
    private String curso;

    public Topico(DatosRegistraTopico datosRegistroTopico) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fecha = LocalDateTime.now();
        this.status = true;
        this.curso = datosRegistroTopico.curso();
    }

}
