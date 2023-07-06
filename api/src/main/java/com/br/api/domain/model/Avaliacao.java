package com.br.api.domain.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Avaliacao {

    private static final String SEQUENCE = "AVALIACAO_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "comentario"
    )
    private String comentario;

    @Column(
            name = "nota",
            nullable = false
    )
    private Double nota;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario avaliador;
}

