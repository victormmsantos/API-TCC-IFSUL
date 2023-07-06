package com.br.api.util;

import com.br.api.domain.request.CriarCampanhaDataRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class DecodeISO8859Util<T> {

    private final ObjectMapper objectMapper;


    public T decode(String stringToDecode, Class<T> requestClass) {
        try {
            objectMapper.registerModule(new JavaTimeModule());

            byte[] isoBytes = stringToDecode.getBytes(StandardCharsets.ISO_8859_1);

            String decodedData = new String(isoBytes, StandardCharsets.UTF_8);

            return objectMapper.readValue(decodedData, requestClass);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possível decodificar os dados");
        }
    }

}
