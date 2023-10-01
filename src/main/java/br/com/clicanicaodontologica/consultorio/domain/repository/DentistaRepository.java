package br.com.clicanicaodontologica.consultorio.domain.repository;

import br.com.clicanicaodontologica.consultorio.domain.entity.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DentistaRepository extends JpaRepository<Dentista, UUID> {
    List<Dentista> findByNomeStartingWith(String termo);
}
