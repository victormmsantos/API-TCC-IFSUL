package com.br.api.domain.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class Usuario {

    private static final String SEQUENCE = "USUARIO_SEQ";

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

    @Column(
            name = "email",
            nullable = false,
            unique = true
    )
    private String email;

    @Column(
            name = "senha",
            nullable = false,
            unique = true
    )
    private String senha;

    @Column(
            name = "endereco",
            nullable = false
    )
    private String endereco;

    @Column(name = "foto")
    private String foto;

    @Column(
            name = "telefone",
            nullable = false,
            unique = true
    )
    private String telefone;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private List<Avaliacao> avaliacoesRecebidas;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<Permission> permissions;
}
