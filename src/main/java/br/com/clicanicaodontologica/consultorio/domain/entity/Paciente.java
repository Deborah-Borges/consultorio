package br.com.clicanicaodontologica.consultorio.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue( strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    private String nome;
    private LocalDate dataNascimento;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_paciente_endereco"))
    private Endereco endereco;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
    private SexoEnum sexo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_contato", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_paciente_contato"))
    private Contato contato;
}
