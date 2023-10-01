package br.com.clicanicaodontologica.consultorio.api.dto.request;

import br.com.clicanicaodontologica.consultorio.domain.entity.Contato;
import br.com.clicanicaodontologica.consultorio.domain.entity.EspecialidadeEnum;
import br.com.clicanicaodontologica.consultorio.domain.entity.SexoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DentistaRequest {
    @NotBlank
    private String nome;
    @NotNull
    private String cro;
    @NotNull
    private LocalDate dataNascimento;
    @NotNull
    private EspecialidadeEnum especialidade;
    private SexoEnum sexo;
    @NotNull
    private Contato contato;
}
