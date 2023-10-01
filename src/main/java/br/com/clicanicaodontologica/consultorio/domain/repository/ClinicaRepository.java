package br.com.clicanicaodontologica.consultorio.domain.repository;

import br.com.clicanicaodontologica.consultorio.domain.entity.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClinicaRepository extends JpaRepository<Clinica, UUID> {
    boolean existsByCnpj(String cnpj);
    List<Clinica> findByNomeStartingWith(String termo);
}
