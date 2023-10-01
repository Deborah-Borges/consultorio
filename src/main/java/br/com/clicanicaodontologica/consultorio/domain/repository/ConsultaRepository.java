package br.com.clicanicaodontologica.consultorio.domain.repository;

import br.com.clicanicaodontologica.consultorio.domain.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConsultaRepository extends JpaRepository<Consulta, UUID> {

}
