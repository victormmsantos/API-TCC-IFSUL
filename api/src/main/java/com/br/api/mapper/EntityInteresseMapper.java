package com.br.api.mapper;

import com.br.api.domain.model.Interesse;

import java.util.function.Function;

public class EntityInteresseMapper implements Function<String, Interesse> {
    @Override
    public Interesse apply(String nome) {
        return Interesse.builder()
                .nome(nome)
                .build();
    }
}
