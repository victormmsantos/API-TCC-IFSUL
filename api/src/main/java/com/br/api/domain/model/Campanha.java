package com.br.api.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Campanha {

    private static final String SEQUENCE = "CAMPANHA_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "titulo",
            nullable = false
    )
    private String titulo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(
            name = "data_de_encerramento",
            nullable = false
    )
    private LocalDate dataEncerramento;

    @Column(
            name = "data_criacao",
            nullable = false,
            updatable = false
    )
    private LocalDate dataCriacao;

    @Column(name = "descricao")
    private String descricao;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_campanha", referencedColumnName = "id")
    private List<Foto> fotos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ong")
    private Ong dono;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "campanha_interesse",
            joinColumns = @JoinColumn(name = "campanha_id"),
            inverseJoinColumns = @JoinColumn(name = "interesse_id"))
    private List<Interesse> interesses;

    @PrePersist
    public void setDataCriacaoQuandoCriar() {
        this.dataCriacao = LocalDate.now();
    }
}
