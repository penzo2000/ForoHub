package com.aluracursos.foroHub.controller;

import com.aluracursos.foroHub.domain.topicos.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    //Inyeccion de dependencias
    @Autowired
    private TopicoRepository topicoRepository;


    //Request
    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarMedico(@RequestBody @Valid DatosRegistraTopico datosRegistroTopico,
                                                                UriComponentsBuilder uriComponentsBuilder) {
        //Guardar en base de datos
        TopicoService topicoService = new TopicoService();
        var topico = topicoService.crearTopico(datosRegistroTopico);

        //Output de la request
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId() ,topico.getTitulo(), topico.getMensaje(),
                        topico.getStatus(),topico.getUsuario().getId(), topico.getCurso(), topico.getFecha());

        //Construye la URI del nuevo recurso
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }
}
