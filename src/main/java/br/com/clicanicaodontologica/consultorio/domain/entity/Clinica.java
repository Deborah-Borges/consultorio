package br.com.clicanicaodontologica.consultorio.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "clinicas")
public class Clinica {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    private String nome;
    @Column(length = 20)
    private String cnpj;
    @Size(min = 5, message = "O campo deve ter pelo menos 5 caracteres.")
    private String razaoSocial;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
    private String descricao;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_clinica_endereco"))
    private Endereco endereco;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_contato" , referencedColumnName = "id" , foreignKey = @ForeignKey(name = "fk_clinica_contato"))
    private Contato contatoId;
}
