package br.com.clicanicaodontologica.consultorio;
import br.com.clicanicaodontologica.consultorio.api.dto.request.EnderecoRequest;
import br.com.clicanicaodontologica.consultorio.domain.entity.Contato;
import br.com.clicanicaodontologica.consultorio.domain.entity.Endereco;
import com.github.javafaker.Faker;
import br.com.clicanicaodontologica.consultorio.api.dto.request.ContatoRequest;
import br.com.clicanicaodontologica.consultorio.api.dto.request.PacienteRequest;
import br.com.clicanicaodontologica.consultorio.domain.entity.SexoEnum;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Locale;

public final class Fixture {
    private static final Faker FAKER = new Faker(new Locale("pt", "BR"));

    public static class PacienteFake {

        public static PacienteRequest anyPaciente() {
            PacienteRequest request = new PacienteRequest();
            request.setNome(FAKER.rickAndMorty().character());
            request.setDataNascimento(LocalDate.now());
            request.setSexo(SexoEnum.M);

            @NotNull
            Contato contatoRequest = new Contato();
            contatoRequest.setEmail(FAKER.internet().emailAddress());
            contatoRequest.setTelefone("(99) 9999-4444");
            request.setContato(contatoRequest);

            @NotNull
            Endereco enderecoRequest = new Endereco();
            enderecoRequest.setLogradouro(FAKER.address().streetAddress());
            enderecoRequest.setBairro(FAKER.address().secondaryAddress());
            enderecoRequest.setCidade(FAKER.address().cityName());
            enderecoRequest.setEstado(FAKER.address().state());
            enderecoRequest.setCep(FAKER.address().zipCode());
            request.setEndereco(enderecoRequest);
            return request;
        }
    }
}
