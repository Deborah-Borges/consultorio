package br.com.clicanicaodontologica.consultorio.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ClinicaResponse {
    private UUID id;
    private String nome;
    private String cnpj;
    private String razaoSocial;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
    private String descricao;
    private EnderecoResponse endereco;
    private ContatoResponse contato;
}
