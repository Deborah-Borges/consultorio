package br.com.clicanicaodontologica.consultorio.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestExceptionCnpj extends RuntimeException {
    public BadRequestExceptionCnpj(String Cnpj) {
        super("O CNPJ: " + Cnpj + " já existe.");
    }
}
