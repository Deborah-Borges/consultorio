package br.com.clicanicaodontologica.consultorio.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "dentistas")
public class Dentista {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    private String nome;
    private String cro;
    private LocalDate dataNascimento;
    @Column(length = 80)
    private EspecialidadeEnum especialidade;
    private SexoEnum sexo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_contato", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_dentista_contato"))
    private Contato contato;
    @ManyToMany
    @JoinTable(
            name = "clinicaDentista",
            joinColumns = @JoinColumn(name = "id_dentista", foreignKey = @ForeignKey(name = "fk_dentista_clinica")),
            inverseJoinColumns = @JoinColumn(name = "id_clinica", foreignKey = @ForeignKey(name = "fk_clinica_dentista"))
    )
    private Set<Clinica> clinicasDentistas;
}
