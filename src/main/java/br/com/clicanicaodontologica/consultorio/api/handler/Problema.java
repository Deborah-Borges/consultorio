package br.com.clicanicaodontologica.consultorio.api.handler;

public record Problema (Integer status,
                        String message,
                        String detail) {
}
