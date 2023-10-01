package br.com.clicanicaodontologica.consultorio.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestExceptionContato extends RuntimeException{
    public BadRequestExceptionContato() {
        super("Email e Telefone estão nulos!");
    }
}
