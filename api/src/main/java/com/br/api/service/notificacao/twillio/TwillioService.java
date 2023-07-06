package com.br.api.service.notificacao.twillio;

import com.br.api.domain.dto.EnvioNotificacoesDTO;
import com.br.api.service.notificacao.EnviarNotificacaooInterface;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwillioService implements EnviarNotificacaooInterface {

    @Value("${twilio.ACCOUNT_SID}")
    private String ACCOUNT_SID;

    @Value("${twilio.AUTH_TOKEN}")
    private String AUTH_TOKEN;

    private static final String prefix = "whatsapp:+55";

    @Override
    public void enviarNotificacao(EnvioNotificacoesDTO envioNotificacoesDTO) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        String numero = formatToNumber(envioNotificacoesDTO.getEndereco());

        Message.creator(
                        new PhoneNumber(numero),
                        new PhoneNumber("whatsapp:+14155238886"),
                        envioNotificacoesDTO.getBody())
                .create();
    }


    private String formatToNumber(String numero) {
        return prefix.concat(numero);
    }


}
