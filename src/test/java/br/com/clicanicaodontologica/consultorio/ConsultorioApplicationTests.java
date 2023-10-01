package br.com.clicanicaodontologica.consultorio;

import br.com.clicanicaodontologica.consultorio.domain.service.PacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
class ConsultorioApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@SpyBean
	private PacienteService pacienteService;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(mvc);
	}

	@Test
	@DisplayName("Dado uma lista vazia, quando chamamos o endpoint buscar Pacientes, retornar uma lista vazia")
	void dadoUmaListaVazia_quandoChamamosEndpointBuscarPacientes_entaoRetornarListaVazia() throws Exception {
		Mockito.when(pacienteService.buscarPacientes(Mockito.any()))
				.thenReturn(new ArrayList<>());

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get("/v1/pacientes")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(request)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));
	}
}
