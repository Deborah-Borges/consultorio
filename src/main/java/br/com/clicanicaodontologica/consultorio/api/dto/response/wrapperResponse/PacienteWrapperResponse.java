package br.com.clicanicaodontologica.consultorio.api.dto.response.wrapperResponse;

import br.com.clicanicaodontologica.consultorio.api.dto.response.list.PacienteListResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PacienteWrapperResponse {
    private List<PacienteListResponse> pacientes;
}
