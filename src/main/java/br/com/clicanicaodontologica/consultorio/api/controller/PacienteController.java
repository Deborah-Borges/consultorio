package br.com.clicanicaodontologica.consultorio.api.controller;

import br.com.clicanicaodontologica.consultorio.api.dto.request.PacienteRequest;
import br.com.clicanicaodontologica.consultorio.api.dto.response.ContatoResponse;
import br.com.clicanicaodontologica.consultorio.api.dto.response.EnderecoResponse;
import br.com.clicanicaodontologica.consultorio.api.dto.response.PacienteResponse;
import br.com.clicanicaodontologica.consultorio.api.dto.response.list.PacienteListResponse;
import br.com.clicanicaodontologica.consultorio.api.dto.response.wrapperResponse.PacienteWrapperResponse;
import br.com.clicanicaodontologica.consultorio.domain.entity.Contato;
import br.com.clicanicaodontologica.consultorio.domain.entity.Endereco;
import br.com.clicanicaodontologica.consultorio.domain.entity.Paciente;
import br.com.clicanicaodontologica.consultorio.domain.service.PacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/pacientes")
@Tag(name = "Pacientes")
public class PacienteController {
    public final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService, ObjectMapper objectMapper) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    ResponseEntity<PacienteResponse> criarPaciente(@RequestBody @Valid PacienteRequest request) {
        Paciente paciente = new Paciente();

        paciente.setNome(request.getNome());
        paciente.setDataNascimento(request.getDataNascimento());
        paciente.setSexo(request.getSexo());

        Contato contato = new Contato();
        contato.setEmail(request.getContato().getEmail());
        contato.setTelefone(request.getContato().getTelefone());
        paciente.setContato(contato);

        Endereco endereco = new Endereco();
        endereco.setLogradouro(request.getEndereco().getLogradouro());
        endereco.setBairro(request.getEndereco().getBairro());
        endereco.setCidade(request.getEndereco().getCidade());
        endereco.setEstado(request.getEndereco().getEstado());
        endereco.setCep(request.getEndereco().getCep());

        Paciente pacienteCriado = pacienteService.criarPaciente(paciente);
        PacienteResponse response = pacienteResponseByPaciente(pacienteCriado);
        return ResponseEntity.ok(response);
    }

    @PutMapping({"id"})
    ResponseEntity<PacienteResponse> atualizarPaciente(@PathVariable UUID id, @RequestBody @Valid PacienteRequest request) {
        Paciente paciente = pacienteService.buscarPacientePorId(id);

        paciente.setNome(request.getNome());
        paciente.setDataNascimento(request.getDataNascimento());
        paciente.setSexo(request.getSexo());

        Contato contato = new Contato();
        contato.setEmail(request.getContato().getEmail());
        contato.setTelefone(request.getContato().getTelefone());
        paciente.setContato(contato);

        Endereco endereco = new Endereco();
        endereco.setLogradouro(request.getEndereco().getLogradouro());
        endereco.setBairro(request.getEndereco().getBairro());
        endereco.setCidade(request.getEndereco().getCidade());
        endereco.setEstado(request.getEndereco().getEstado());
        endereco.setCep(request.getEndereco().getCep());

        Paciente pacienteAtualizado = pacienteService.atualizarPaciente(id, paciente);
        PacienteResponse response = pacienteResponseByPaciente(pacienteAtualizado);
        return ResponseEntity.ok(response);
    }

    @GetMapping({"id"})
    ResponseEntity<PacienteResponse> buscarPorId(@PathVariable UUID id) {
        Paciente paciente = pacienteService.buscarPacientePorId(id);
        PacienteResponse response = pacienteResponseByPaciente(paciente);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    ResponseEntity<PacienteWrapperResponse> buscarPacientesPorTermo(@RequestParam(required = false) String termo) {
        List<Paciente> pacientes = pacienteService.buscarPacientes(termo);
        PacienteWrapperResponse pacienteWrapperResponse= new PacienteWrapperResponse();
        pacienteWrapperResponse.setPacientes(pacientes.stream().map(paciente -> {
            PacienteListResponse pacienteListResponse =new PacienteListResponse();
            pacienteListResponse.setId(paciente.getId());
            pacienteListResponse.setNome(paciente.getNome());
            return pacienteListResponse;
        }).toList());
        return ResponseEntity.ok(pacienteWrapperResponse);
    }

    @DeleteMapping({"id"})
    ResponseEntity<Void> excluirPaciente (@PathVariable UUID id) {
        pacienteService.deletarPaciente(id);
        return ResponseEntity.ok().build();
    }

    private PacienteResponse pacienteResponseByPaciente(Paciente paciente) {
        PacienteResponse pacienteResponse = new PacienteResponse();
        pacienteResponse.setId(paciente.getId());
        pacienteResponse.setNome(paciente.getNome());
        pacienteResponse.setDataNascimento(paciente.getDataNascimento());
        pacienteResponse.setSexo(paciente.getSexo());
        pacienteResponse.setCriadoEm(paciente.getCriadoEm());
        pacienteResponse.setAtualizadoEm(paciente.getAtualizadoEm());

        EnderecoResponse endereco = new EnderecoResponse();
        endereco.setId(paciente.getId());
        endereco.setLogradouro(paciente.getEndereco().getLogradouro());
        endereco.setBairro(paciente.getEndereco().getBairro());
        endereco.setCidade(paciente.getEndereco().getCidade());
        endereco.setEstado(paciente.getEndereco().getEstado());
        endereco.setCep(paciente.getEndereco().getCep());
        endereco.setCriadoEm(paciente.getCriadoEm());
        endereco.setAtualizadoEm(paciente.getAtualizadoEm());

        ContatoResponse contato = new ContatoResponse();
        contato.setId(paciente.getContato().getId());
        contato.setEmail(paciente.getContato().getEmail());
        contato.setTelefone(paciente.getContato().getTelefone());
        contato.setCriadoEm(paciente.getContato().getCriadoEm());
        contato.setAtualizadoEm(paciente.getContato().getAtualizadoEm());

        pacienteResponse.setEndereco(endereco);
        pacienteResponse.setContato(contato);

        return pacienteResponse;
    }
}
