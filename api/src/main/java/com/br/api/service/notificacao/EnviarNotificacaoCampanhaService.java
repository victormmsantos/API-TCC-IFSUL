package com.br.api.service.notificacao;

import com.br.api.domain.dto.EnvioNotificacoesDTO;
import com.br.api.domain.model.Campanha;
import com.br.api.domain.model.Voluntario;
import com.br.api.service.notificacao.twillio.TwillioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.br.api.validator.ValidarEnvioNotificacaoValidator.isValid;

@Service
@RequiredArgsConstructor
public class EnviarNotificacaoCampanhaService {

    private final TwillioService twillioService;

    public void send(EnviarNotificacaoInterface servico, Campanha campanha, List<Voluntario> voluntariosParaNotificar) {
        voluntariosParaNotificar.forEach(v -> {

            EnvioNotificacoesDTO envioNotificacoesDTO = EnvioNotificacoesDTO.builder()
                    .endereco(v.getUsuario().getTelefone())
                    .body(makeBody(campanha))
                    .build();

            try {
                if (isValid(campanha, v)) {
                    servico.enviarNotificacao(envioNotificacoesDTO);
                }
            } catch (Exception e) {
                System.out.println("Erro ao enviar notificação");
            }
        });
    }

    private String makeBody(Campanha campanha) {
        return "A campanha "
                .concat(campanha.getTitulo().trim())
                .concat(" se encaixa nos seus interesses \n")
                .concat("Descrição: ")
                .concat(campanha.getDescricao())
                .concat("\n")
                .concat("Dê: ")
                .concat(campanha.getDono().getUsuario().getNome())
                .concat("\n")
                .concat("Entre em contato por ")
                .concat(campanha.getDono().getUsuario().getTelefone());
    }

}
