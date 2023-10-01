package br.com.clicanicaodontologica.consultorio.domain.repository;

import br.com.clicanicaodontologica.consultorio.domain.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PacienteRepository extends JpaRepository<Paciente, UUID> {
    List<Paciente> findByNomeStartingWith(String termo);
}
