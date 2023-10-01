package br.com.clicanicaodontologica.consultorio.domain.service;

import br.com.clicanicaodontologica.consultorio.domain.entity.Paciente;

import java.util.List;
import java.util.UUID;

public interface PacienteService {
    Paciente criarPaciente(Paciente paciente);
    List<Paciente> buscarPacientes(String termo);
    Paciente buscarPacientePorId(UUID uuid);
    Paciente atualizarPaciente(UUID id, Paciente paciente);
    void deletarPaciente(UUID uuid);
}
