package com.aluracursos.foroHub.domain.usuarios;

import com.aluracursos.foroHub.domain.topicos.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
