package br.com.clicanicaodontologica.consultorio.api.dto.response.wrapperResponse;

import br.com.clicanicaodontologica.consultorio.api.dto.response.list.ConsultaListResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConsultaWrapperResponse {
    private List<ConsultaListResponse> consultas;
}
