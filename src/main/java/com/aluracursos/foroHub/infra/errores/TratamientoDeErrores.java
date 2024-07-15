package com.aluracursos.foroHub.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class TratamientoDeErrores {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> tratarError403(SQLIntegrityConstraintViolationException e){
        if(e.getMessage().contains("topics.titulo")){
            String mensaje = "El título  ya se encuentra en la base de datos, no puedes repetirlo";
            return ResponseEntity.badRequest().body(mensaje);
        }
        return ResponseEntity.badRequest().body("El mensaje ya se encuentra en la base de datos, no puedes repetirlo");
    }

    //Representar errores de validación de una forma sencilla y estructurada
    private record DatosErrorValidacion(String campo, String error){
        public DatosErrorValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
