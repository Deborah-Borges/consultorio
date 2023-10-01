package br.com.clicanicaodontologica.consultorio.api.controller;

import br.com.clicanicaodontologica.consultorio.api.dto.request.DentistaRequest;
import br.com.clicanicaodontologica.consultorio.api.dto.response.ContatoResponse;
import br.com.clicanicaodontologica.consultorio.api.dto.response.DentistaResponse;
import br.com.clicanicaodontologica.consultorio.api.dto.response.list.DentistaListResponse;
import br.com.clicanicaodontologica.consultorio.api.dto.response.wrapperResponse.DentistaWrapperResponse;
import br.com.clicanicaodontologica.consultorio.domain.entity.Contato;
import br.com.clicanicaodontologica.consultorio.domain.entity.Dentista;
import br.com.clicanicaodontologica.consultorio.domain.service.DentistaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/destistas")
@Tag(name = "Dentistas")
public class DentistaController {

    private final DentistaService dentistaService;

    @Autowired
    public DentistaController(DentistaService dentistaService) {
        this.dentistaService = dentistaService;
    }

    @PostMapping
    ResponseEntity<DentistaResponse> criarDentista(@RequestBody @Valid DentistaRequest request) {
        Dentista dentista = new Dentista();
        dentista.setNome(request.getNome());
        dentista.setCro(request.getCro());
        dentista.setDataNascimento(request.getDataNascimento());
        dentista.setEspecialidade(request.getEspecialidade());
        dentista.setSexo(request.getSexo());

        Contato contato = new Contato();
        contato.setTelefone(request.getContato().getTelefone());
        contato.setEmail(request.getContato().getEmail());
        dentista.setContato(contato);

        Dentista dentistaCriado = dentistaService.criarDentista(dentista);
        DentistaResponse response = dentistaResponseByDentista(dentistaCriado);

        return ResponseEntity.ok(response);
    }

    @PutMapping({"id"})
    ResponseEntity<DentistaResponse> atualizarClinica(@PathVariable UUID id, @RequestBody @Valid DentistaRequest request) {
        Dentista dentista = dentistaService.buscarDentistaPorId(id);
        dentista.setNome(request.getNome());
        dentista.setCro(request.getCro());
        dentista.setDataNascimento(request.getDataNascimento());
        dentista.setEspecialidade(request.getEspecialidade());
        dentista.setSexo(request.getSexo());

        Contato contato = new Contato();
        contato.setTelefone(request.getContato().getTelefone());
        contato.setEmail(request.getContato().getEmail());
        dentista.setContato(contato);

        Dentista dentistaAtualizado = dentistaService.atualizarDentista(id, dentista);
        DentistaResponse response = dentistaResponseByDentista(dentistaAtualizado);
        return ResponseEntity.ok(response);
    }

    @GetMapping({"id"})
    ResponseEntity<DentistaResponse> buscarPorId(@PathVariable UUID id) {
        Dentista dentista = dentistaService.buscarDentistaPorId(id);
        DentistaResponse response = dentistaResponseByDentista(dentista);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    ResponseEntity<DentistaWrapperResponse> buscarTodosPorTermo(@RequestParam String termo) {
        List<Dentista> dentistas = dentistaService.buscarDentistas(termo);
        DentistaWrapperResponse dentistaWrapperResponse = new DentistaWrapperResponse();
        dentistaWrapperResponse.setDentistas(dentistas.stream().map(dentista -> {
            DentistaListResponse dentistaListResponse = new DentistaListResponse();
            dentistaListResponse.setId(dentista.getId());
            dentistaListResponse.setNome(dentista.getNome());
            dentistaListResponse.setCro(dentista.getCro());
            return dentistaListResponse;
        }).toList());
        return ResponseEntity.ok(dentistaWrapperResponse);
    }

    @DeleteMapping({"id"})
    ResponseEntity<Void> excluirDentista(@PathVariable UUID id) {
        dentistaService.deletarDentista(id);
        return ResponseEntity.ok().build();
    }

    private DentistaResponse dentistaResponseByDentista(Dentista dentista) {
        DentistaResponse dentistaResponse = new DentistaResponse();
        dentistaResponse.setId(dentista.getId());
        dentistaResponse.setNome(dentista.getNome());
        dentistaResponse.setCro(dentista.getCro());
        dentistaResponse.setSexo(dentista.getSexo());

        ContatoResponse contato = new ContatoResponse();
        contato.setId(contato.getId());
        contato.setTelefone(dentista.getContato().getTelefone());
        contato.setEmail(dentista.getContato().getEmail());
        contato.setCriadoEm(contato.getCriadoEm());
        contato.setAtualizadoEm(contato.getAtualizadoEm());

        dentistaResponse.setContato(contato);

        return dentistaResponse;
    }
    
}
