package com.br.api.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnvioNotificacoesDTO {

    private String endereco;

    private String body;

    private String titulo;

    private String anexos;

}
