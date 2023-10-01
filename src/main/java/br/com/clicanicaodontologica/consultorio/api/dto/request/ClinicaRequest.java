package br.com.clicanicaodontologica.consultorio.api.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

@Getter
@Setter
public class ClinicaRequest {
    @NotBlank
    public String nome;
    @CNPJ
    @NotBlank
    public String cnpj;
    @NotBlank
    @Length(min = 5, max = 255)
    @JsonAlias(value = "razao_social")
    private String razaoSocial;
    @NotBlank
    private String descricao;
    @NotNull
    private EnderecoRequest endereco;
    @NotNull
    private ContatoRequest contato;
}
