package com.aluracursos.foroHub.controller;

import com.aluracursos.foroHub.domain.topicos.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    //Inyeccion de dependencias
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private TopicoService topicoService;

    //Request
    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(
            @RequestBody @Valid DatosRegistraTopico datosRegistroTopico,
            UriComponentsBuilder uriComponentsBuilder) {

        // Guardar en base de datos
        var topico = topicoService.crearTopico(datosRegistroTopico);

        // Output de la request
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico);

        // Construye la URI del nuevo recurso
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopicos>> listarTopicos(@PageableDefault(size = 10, sort = "fecha", direction = Sort.Direction.ASC) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findByStatusTrue(paginacion).map(DatosListadoTopicos::new));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico, @PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.actualizarDatos(datosActualizarTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> detallarUnTopico(@PathVariable Long id) {
        return topicoRepository.findById(id)
                .map(topico -> ResponseEntity.ok(new DatosRespuestaTopico(topico)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }


}
