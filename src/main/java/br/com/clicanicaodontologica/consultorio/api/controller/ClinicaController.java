package br.com.clicanicaodontologica.consultorio.api.controller;

import br.com.clicanicaodontologica.consultorio.api.dto.request.ClinicaRequest;
import br.com.clicanicaodontologica.consultorio.api.dto.response.ClinicaResponse;
import br.com.clicanicaodontologica.consultorio.api.dto.response.ContatoResponse;
import br.com.clicanicaodontologica.consultorio.api.dto.response.EnderecoResponse;
import br.com.clicanicaodontologica.consultorio.api.dto.response.list.ClinicaListResponse;
import br.com.clicanicaodontologica.consultorio.api.dto.response.wrapperResponse.ClinicaWrapperResponse;
import br.com.clicanicaodontologica.consultorio.domain.entity.Clinica;
import br.com.clicanicaodontologica.consultorio.domain.entity.Contato;
import br.com.clicanicaodontologica.consultorio.domain.entity.Endereco;
import br.com.clicanicaodontologica.consultorio.domain.service.ClinicaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/clinicas")
@Tag(name = "Clinicas")
public class ClinicaController {

    private final ClinicaService clinicaService;

    @Autowired
    public ClinicaController(ClinicaService clinicaService, ObjectMapper objectMapper) {
        this.clinicaService = clinicaService;
    }

    //criar
    @PostMapping
    ResponseEntity<ClinicaResponse> criarClinica(@RequestBody @Valid ClinicaRequest request) {
        Clinica clinica = new Clinica();

        clinica.setCnpj(request.getCnpj());
        clinica.setNome(request.getNome());
        clinica.setRazaoSocial(request.getRazaoSocial());
        clinica.setDescricao(request.getDescricao());

        Contato contato = new Contato();
        contato.setEmail(request.getContato().getEmail());
        contato.setTelefone(request.getContato().getTelefone());
        clinica.setContatoId(contato);

        Endereco endereco = new Endereco();
        endereco.setLogradouro(request.getEndereco().getLogradouro());
        endereco.setBairro(request.getEndereco().getBairro());
        endereco.setCidade(request.getEndereco().getCidade());
        endereco.setEstado(request.getEndereco().getEstado());
        endereco.setCep(request.getEndereco().getCep());
        clinica.setEndereco(endereco);

        Clinica clinicaCriada = clinicaService.criarClinica(clinica);
        ClinicaResponse response = clinicaResponseByClinica(clinicaCriada);
        return ResponseEntity.ok(response);
    }

    //atualizar
    @PutMapping({"id"})
    ResponseEntity<ClinicaResponse> atualizarClinica(@PathVariable UUID id, @RequestBody @Valid ClinicaRequest request) {
        Clinica clinica = clinicaService.buscarClinicaPorId(id);
        clinica.setCnpj(request.getCnpj());
        clinica.setNome(request.getNome());
        clinica.setRazaoSocial(request.getRazaoSocial());

        Contato contato = clinica.getContatoId();
        contato.setEmail(request.getContato().getEmail());
        contato.setTelefone(request.getContato().getTelefone());
        clinica.setContatoId(contato);

        Endereco endereco = clinica.getEndereco();
        endereco.setLogradouro(request.getEndereco().getLogradouro());
        endereco.setBairro(request.getEndereco().getBairro());
        endereco.setCidade(request.getEndereco().getCidade());
        endereco.setEstado(request.getEndereco().getEstado());
        endereco.setCep(request.getEndereco().getCep());
        clinica.setEndereco(endereco);

        Clinica clinicaAtualizada = clinicaService.atualizarClinica(id, clinica);
        ClinicaResponse response = clinicaResponseByClinica(clinicaAtualizada);
        return ResponseEntity.ok(response);
    }

    //busca por id
    @GetMapping("{id}")
    ResponseEntity<ClinicaResponse> buscarPorId(@PathVariable UUID id) {
        Clinica clinica = clinicaService.buscarClinicaPorId(id);
        ClinicaResponse response = clinicaResponseByClinica(clinica);
        return ResponseEntity.ok(response);
    }

    //buscar todos por termo
    @GetMapping
    ResponseEntity<ClinicaWrapperResponse> buscarClinicasPorTermo(@RequestParam(required = false) String termo) {
        List<Clinica> clinicas = clinicaService.buscarClinicas(termo);
        ClinicaWrapperResponse clinicaWrapperResponse = new ClinicaWrapperResponse();
        clinicaWrapperResponse.setClinicas(clinicas.stream().map(clinica -> {
            ClinicaListResponse clinicaListResponse = new ClinicaListResponse();
            clinicaListResponse.setId(clinica.getId());
            clinicaListResponse.setNome(clinica.getNome());
            clinicaListResponse.setCnpj(clinica.getCnpj());
            return clinicaListResponse;
        }).toList());
        return ResponseEntity.ok(clinicaWrapperResponse);
    }

    //deletar
    @DeleteMapping({"id"})
    ResponseEntity<Void> excluirClinica(@PathVariable UUID id) {
        clinicaService.deletarClinica(id);
        return ResponseEntity.ok().build();
    }

    private ClinicaResponse clinicaResponseByClinica(Clinica clinica) {
        ClinicaResponse clinicaResponse = new ClinicaResponse();
        clinicaResponse.setId(clinica.getId());
        clinicaResponse.setNome(clinica.getNome());
        clinicaResponse.setCnpj(clinica.getCnpj());
        clinicaResponse.setRazaoSocial(clinica.getRazaoSocial());
        clinicaResponse.setCriadoEm(clinica.getCriadoEm());
        clinicaResponse.setAtualizadoEm(clinica.getAtualizadoEm());

        ContatoResponse contato = new ContatoResponse();
        contato.setId(clinica.getContatoId().getId());
        contato.setEmail(clinica.getContatoId().getEmail());
        contato.setTelefone(clinica.getContatoId().getTelefone());
        contato.setCriadoEm(clinica.getContatoId().getCriadoEm());
        contato.setAtualizadoEm(clinica.getContatoId().getAtualizadoEm());

        EnderecoResponse endereco = new EnderecoResponse();
        endereco.setId(clinica.getId());
        endereco.setLogradouro(clinica.getEndereco().getLogradouro());
        endereco.setBairro(clinica.getEndereco().getBairro());
        endereco.setCidade(clinica.getEndereco().getCidade());
        endereco.setEstado(clinica.getEndereco().getEstado());
        endereco.setCep(clinica.getEndereco().getCep());
        endereco.setCriadoEm(clinica.getCriadoEm());
        endereco.setAtualizadoEm(clinica.getAtualizadoEm());

        clinicaResponse.setContato(contato);
        clinicaResponse.setEndereco(endereco);

        return clinicaResponse;
    }
}