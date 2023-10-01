package br.com.clicanicaodontologica.consultorio.domain.service;

import br.com.clicanicaodontologica.consultorio.domain.entity.Dentista;

import java.util.List;
import java.util.UUID;

public interface DentistaService {
    Dentista criarDentista(Dentista dentista);
    List<Dentista> buscarDentistas(String termo);
    Dentista buscarDentistaPorId(UUID uuid);
    Dentista atualizarDentista(UUID id, Dentista dentista);
    void deletarDentista(UUID uuid);
}
