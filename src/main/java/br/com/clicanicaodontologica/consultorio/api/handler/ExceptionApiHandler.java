package br.com.clicanicaodontologica.consultorio.api.handler;

import br.com.clicanicaodontologica.consultorio.domain.exception.BadRequestExceptionContato;
import br.com.clicanicaodontologica.consultorio.domain.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionApiHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Problema> notFoundExceptionHandler(NotFoundException e) {
        Problema problema = new Problema(HttpStatus.NOT_FOUND.value(),
                "Algum problema aconteceu", e.getMessage());
        return ResponseEntity.ok().body(problema);
    }

    @ExceptionHandler(BadRequestExceptionContato.class)
    public ResponseEntity<Problema> BadRequestExceptionContato(NotFoundException e) {
        Problema problema = new Problema(HttpStatus.NOT_FOUND.value(),
                "Algum problema aconteceu", e.getMessage());
        return ResponseEntity.ok().body(problema);
    }
}
