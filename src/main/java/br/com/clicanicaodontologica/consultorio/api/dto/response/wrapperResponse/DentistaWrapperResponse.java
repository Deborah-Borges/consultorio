package br.com.clicanicaodontologica.consultorio.api.dto.response.wrapperResponse;

import br.com.clicanicaodontologica.consultorio.api.dto.response.list.DentistaListResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DentistaWrapperResponse {
    private List<DentistaListResponse> dentistas;
}
