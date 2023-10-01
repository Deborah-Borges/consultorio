package br.com.clicanicaodontologica.consultorio.api.dto.request;

import br.com.clicanicaodontologica.consultorio.domain.entity.Clinica;
import br.com.clicanicaodontologica.consultorio.domain.entity.Dentista;
import br.com.clicanicaodontologica.consultorio.domain.entity.Paciente;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ConsultaRequest {
    @NotNull
    private Paciente paciente;
    @NotNull
    private Dentista dentista;
    @NotNull
    private Clinica clinica;
    @NotNull
    private LocalDate dataConsulta;
    @NotEmpty
    private String descricao;
    @NotNull
    private Boolean cancelada;
    private String motivoCancelamento;
}
