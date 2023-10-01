package br.com.clicanicaodontologica.consultorio.domain.service.impl;

import br.com.clicanicaodontologica.consultorio.domain.entity.Paciente;
import br.com.clicanicaodontologica.consultorio.domain.exception.BadRequestExceptionContato;
import br.com.clicanicaodontologica.consultorio.domain.exception.NotFoundException;
import br.com.clicanicaodontologica.consultorio.domain.repository.PacienteRepository;
import br.com.clicanicaodontologica.consultorio.domain.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente criarPaciente(Paciente paciente) {
        if(paciente.getContato().getEmail() == null && paciente.getContato().getTelefone() == null) {
            throw new BadRequestExceptionContato();
        }
        return null;
    }

    @Override
    public List<Paciente> buscarPacientes(String termo) {
        return pacienteRepository.findByNomeStartingWith(termo);
    }


    @Override
    public Paciente buscarPacientePorId(UUID id) {
        try {
            return pacienteRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            throw new NotFoundException(id);
        }
    }

    @Override
    public Paciente atualizarPaciente(UUID id, Paciente paciente) {
        try {
            pacienteRepository.findById(id).orElseThrow();;
        } catch (Exception e) {
            throw new NotFoundException(id);
        }
        return pacienteRepository.save(paciente);
    }

    @Override
    public void deletarPaciente(UUID id) {
        try {
            pacienteRepository.findById(id).orElseThrow();
            pacienteRepository.deleteById(id);
        } catch (Exception e){
            throw new NotFoundException(id);
        }

    }
}
