package com.aluracursos.foroHub.domain.topicos;

import com.aluracursos.foroHub.domain.usuarios.Usuario;
import com.aluracursos.foroHub.domain.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    public Topico crearTopico(DatosRegistraTopico datosRegistroTopico) {
        // Buscar el usuario por ID
        Usuario usuario = usuarioRepository.findById(datosRegistroTopico.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Crear una instancia de Topico y asignar el usuario
        Topico topico = new Topico(datosRegistroTopico);
        topico.setUsuario(usuario);

        // Guardar el Topico en la base de datos
        return topicoRepository.save(topico);
    }
}

