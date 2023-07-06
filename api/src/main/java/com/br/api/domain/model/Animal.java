package com.br.api.domain.model;

import com.br.api.domain.enumaration.Especie;
import com.br.api.domain.enumaration.Genero;
import com.br.api.domain.enumaration.Porte;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

    private static final String SEQUENCE = "ANIMAL_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "especie",
            nullable = false
    )
    private Especie especie;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "genero",
            nullable = false
    )
    private Genero genero;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "porte",
            nullable = false
    )
    private Porte porte;

    @Column(
            name = "data_de_nascimento",
            nullable = false
    )
    private LocalDate dataDeNascimento;

    @Column(
            name = "nome",
            nullable = false
    )
    private String nome;

    @Column(
            name = "is_excluido",
            nullable = false
    )
    private Boolean isExcluido;

    @Column(
            name = "raca",
            nullable = false
    )
    private String raca;

    @Column(
            name = "situacao_vacinal",
            nullable = false
    )
    private String situacaoVacinal;

    @Column(
            name = "caracteristicas"
    )
    private String caracteristicas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ong")
    private Ong dono;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_animal", referencedColumnName = "id")
    private List<Foto> fotos;

}
