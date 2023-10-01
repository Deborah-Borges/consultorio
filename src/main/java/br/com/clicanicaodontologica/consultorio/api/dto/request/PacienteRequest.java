package br.com.clicanicaodontologica.consultorio.api.dto.request;

import br.com.clicanicaodontologica.consultorio.domain.entity.Contato;
import br.com.clicanicaodontologica.consultorio.domain.entity.Endereco;
import br.com.clicanicaodontologica.consultorio.domain.entity.SexoEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PacienteRequest {
    @NotNull
    private String nome;
    @NotNull
    private LocalDate dataNascimento;
    @NotNull
    private Endereco endereco;
    @NotNull
    private Contato contato;
    private SexoEnum sexo;

}
