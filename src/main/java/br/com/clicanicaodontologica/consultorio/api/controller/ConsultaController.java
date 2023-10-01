package br.com.clicanicaodontologica.consultorio.api.controller;

import br.com.clicanicaodontologica.consultorio.api.dto.request.ConsultaRequest;
import br.com.clicanicaodontologica.consultorio.api.dto.response.ConsultaResponse;
import br.com.clicanicaodontologica.consultorio.api.dto.response.list.ConsultaListResponse;
import br.com.clicanicaodontologica.consultorio.api.dto.response.wrapperResponse.ClinicaWrapperResponse;
import br.com.clicanicaodontologica.consultorio.api.dto.response.wrapperResponse.ConsultaWrapperResponse;
import br.com.clicanicaodontologica.consultorio.domain.entity.Consulta;
import br.com.clicanicaodontologica.consultorio.domain.service.ConsultaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/consultas")
@Tag(name = "Consultas")
public class ConsultaController {
    private final ConsultaService consultaService;

    @Autowired
    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    ResponseEntity<ConsultaResponse> criarConsulta(@RequestBody @Valid ConsultaRequest request) {
        Consulta consulta = new Consulta();
        consulta.setPaciente(request.getPaciente());
        consulta.setDentistaId(request.getDentista());
        consulta.setClinica(request.getClinica());
        consulta.setDataConsulta(request.getDataConsulta());
        consulta.setDescricao(request.getDescricao());
        consulta.setCancelada(request.getCancelada());
        consulta.setMotivoCancelamento(request.getMotivoCancelamento());

        Consulta consultaCriada = consultaService.criarConsultas(consulta);
        ConsultaResponse response = consultaResponseByConsulta(consultaCriada);

        return ResponseEntity.ok(response);
    }

    @PutMapping({"id"})
    ResponseEntity<ConsultaResponse> atualizarConsulta(@PathVariable UUID id, @RequestBody @Valid ConsultaRequest request) {
        Consulta consulta = consultaService.buscarConsultaPorId(id);
        consulta.setPaciente(request.getPaciente());
        consulta.setDentistaId(request.getDentista());
        consulta.setClinica(request.getClinica());
        consulta.setDataConsulta(request.getDataConsulta());
        consulta.setDescricao(request.getDescricao());
        consulta.setCancelada(request.getCancelada());
        consulta.setMotivoCancelamento(request.getMotivoCancelamento());

        Consulta consultaAtualizada = consultaService.criarConsultas(consulta);
        ConsultaResponse response = consultaResponseByConsulta(consultaAtualizada);
        return ResponseEntity.ok(response);
    }

    @GetMapping({"id"})
    ResponseEntity<ConsultaResponse> buscarPorId(@PathVariable UUID id) {
        Consulta consulta = consultaService.buscarConsultaPorId(id);
        ConsultaResponse response = consultaResponseByConsulta(consulta);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    ResponseEntity<ClinicaWrapperResponse> buscarClinicaPorTermo(@RequestParam(required = false) String termo) {
        List<Consulta> consultas = consultaService.buscarConsulta(termo);
        ConsultaWrapperResponse consultaWrapperResponse = new ConsultaWrapperResponse();
        consultaWrapperResponse.setConsultas(consultas.stream().map(consulta -> {
            ConsultaListResponse consultaListResponse = new ConsultaListResponse();
            consultaListResponse.setId(consulta.getId());
            consultaListResponse.setDataConsulta(consulta.getDataConsulta());
            return consultaListResponse;
        }).toList());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping({"id"})
    ResponseEntity<Void> excluirConsulta(@PathVariable UUID id) {
        consultaService.deletarConsulta(id);
        return ResponseEntity.ok().build();
    }

    private ConsultaResponse consultaResponseByConsulta(Consulta consulta) {
        ConsultaResponse consultaResponse = new ConsultaResponse();
        consultaResponse.setId(consulta.getId());
        consulta.setPaciente(consulta.getPaciente());
        consulta.setDentistaId(consulta.getDentistaId());
        consulta.setClinica(consulta.getClinica());

        consulta.setDataConsulta(consulta.getDataConsulta());
        consulta.setDescricao(consulta.getDescricao());
        consulta.setCancelada(consulta.getCancelada());
        consulta.setMotivoCancelamento(consulta.getMotivoCancelamento());
        return consultaResponse;
    }

}
