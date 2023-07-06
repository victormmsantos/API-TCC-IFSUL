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
public class Interesse {

    private static final String SEQUENCE = "INTERESSE_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "nome",
            nullable = false
    )
    private String nome;

}
