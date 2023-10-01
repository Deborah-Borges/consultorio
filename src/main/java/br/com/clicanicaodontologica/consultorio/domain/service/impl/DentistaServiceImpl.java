package br.com.clicanicaodontologica.consultorio.domain.service.impl;

import br.com.clicanicaodontologica.consultorio.domain.entity.Dentista;
import br.com.clicanicaodontologica.consultorio.domain.exception.BadRequestExceptionContato;
import br.com.clicanicaodontologica.consultorio.domain.exception.NotFoundException;
import br.com.clicanicaodontologica.consultorio.domain.repository.DentistaRepository;
import br.com.clicanicaodontologica.consultorio.domain.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DentistaServiceImpl implements DentistaService {

    private final DentistaRepository dentistaRepository;

    @Autowired
    public DentistaServiceImpl(DentistaRepository dentistaRepository) {
        this.dentistaRepository = dentistaRepository;
    }

    @Override
    public Dentista criarDentista(Dentista dentista) {

        if(dentista.getContato().getEmail() == null && dentista.getContato().getTelefone() == null) {
            throw new BadRequestExceptionContato();
        }
        return null;
    }

    @Override
    public List<Dentista> buscarDentistas(String termo) {

        return dentistaRepository.findByNomeStartingWith(termo);
    }

    @Override
    public Dentista buscarDentistaPorId(UUID id) {
        try {
            return dentistaRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            throw new NotFoundException(id);
        }
    }

    @Override
    public Dentista atualizarDentista(UUID id, Dentista dentista) {
        try {
            dentistaRepository.findById(id).orElseThrow();;
        } catch (Exception e) {
            throw new NotFoundException(id);
        }
        return dentistaRepository.save(dentista);
    }

    @Override
    public void deletarDentista(UUID id) {
        try {
            dentistaRepository.findById(id).orElseThrow();
            dentistaRepository.deleteById(id);
        } catch (Exception e){
            throw new NotFoundException(id);
        }

    }
}
