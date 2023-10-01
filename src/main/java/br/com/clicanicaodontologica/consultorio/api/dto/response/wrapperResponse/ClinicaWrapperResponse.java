package br.com.clicanicaodontologica.consultorio.api.dto.response.wrapperResponse;

import br.com.clicanicaodontologica.consultorio.api.dto.response.list.ClinicaListResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClinicaWrapperResponse {
    private List<ClinicaListResponse> clinicas;
}
