package br.com.clicanicaodontologica.consultorio.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "contatos")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(length = 100)
    @Email
    private String email;
    @Column(length = 15)
    private String telefone;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
    private String fax;
}
