package br.com.clicanicaodontologica.consultorio.domain.service;
import br.com.clicanicaodontologica.consultorio.domain.entity.Consulta;

import java.util.List;
import java.util.UUID;

public interface ConsultaService {
    Consulta criarConsultas(Consulta consulta);
    List<Consulta> buscarConsulta(String termo);
    Consulta buscarConsultaPorId(UUID uuid);
    Consulta atualizarConsulta(UUID id, Consulta consulta);
    void deletarConsulta(UUID uuid);
}
