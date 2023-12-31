package br.com.clicanicaodontologica.consultorio.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(length = 100)
    private String logradouro;
    @Column(length = 100)
    private String bairro;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
    @Column(length = 50)
    private String cidade;
    @Column(length = 50)
    private String estado;
    @Column(length = 10)
    private String cep;
}
