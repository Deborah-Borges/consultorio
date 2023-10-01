package br.com.clicanicaodontologica.consultorio.api.dto.response;

import br.com.clicanicaodontologica.consultorio.domain.entity.Clinica;
import br.com.clicanicaodontologica.consultorio.domain.entity.EspecialidadeEnum;
import br.com.clicanicaodontologica.consultorio.domain.entity.SexoEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class DentistaResponse {
    private UUID id;
    private String nome;
    private String cro;
    private LocalDate dataNascimento;
    private EspecialidadeEnum especialidade;
    private SexoEnum sexo;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
    private ContatoResponse contato;
    private Set<Clinica> clinicaDentista;
}
