package br.com.clicanicaodontologica.consultorio.domain.service.impl;

import br.com.clicanicaodontologica.consultorio.domain.entity.Clinica;
import br.com.clicanicaodontologica.consultorio.domain.exception.BadRequestExceptionCnpj;
import br.com.clicanicaodontologica.consultorio.domain.exception.BadRequestExceptionContato;
import br.com.clicanicaodontologica.consultorio.domain.exception.NotFoundException;
import br.com.clicanicaodontologica.consultorio.domain.repository.ClinicaRepository;
import br.com.clicanicaodontologica.consultorio.domain.service.ClinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClinicaServiceImpl implements ClinicaService {

    private final ClinicaRepository clinicaRepository;

    @Autowired
    public ClinicaServiceImpl(ClinicaRepository clinicaRepository) {
        this.clinicaRepository = clinicaRepository;
    }

    @Override
    public Clinica criarClinica(Clinica clinica) {
        boolean cnpjExiste = clinicaRepository.existsByCnpj(clinica.getCnpj());
        if (cnpjExiste){
            throw new BadRequestExceptionCnpj(clinica.getCnpj());
        }
        if(clinica.getContatoId().getEmail() == null && clinica.getContatoId().getTelefone() == null) {
            throw new BadRequestExceptionContato();
        }
        return clinicaRepository.save(clinica);
    }

    @Override
    public List<Clinica> buscarClinicas(String termo) {
        return clinicaRepository.findByNomeStartingWith(termo);
    }

    @Override
    public Clinica buscarClinicaPorId(UUID id) {
        try {
            return clinicaRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            throw new NotFoundException(id);
        }
    }

    @Override
    public Clinica atualizarClinica(UUID id, Clinica clinica) {
        try {
            clinicaRepository.findById(id).orElseThrow();;
        } catch (Exception e) {
            throw new NotFoundException(id);
        }
        return clinicaRepository.save(clinica);
    }

    @Override
    public void deletarClinica(UUID id) {
        try {
            clinicaRepository.findById(id).orElseThrow();
            clinicaRepository.deleteById(id);
        } catch (Exception e){
            throw new NotFoundException(id);
        }
    }
}