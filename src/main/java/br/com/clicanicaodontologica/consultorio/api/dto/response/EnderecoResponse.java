package br.com.clicanicaodontologica.consultorio.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class EnderecoResponse {
    private UUID id;
    private String logradouro;
    private String bairro;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
    private String cidade;
    private String estado;
    private String cep;
}
