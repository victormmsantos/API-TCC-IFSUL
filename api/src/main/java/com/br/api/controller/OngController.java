package com.br.api.controller;

import com.br.api.domain.request.CadastrarOngRequest;
import com.br.api.domain.request.CriarCampanhaDataRequest;
import com.br.api.service.ong.CadastrarOngService;
import com.br.api.service.ong.CriarCampanhaService;
import com.br.api.util.DecodeISO8859Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/ong")
public class OngController {

    @Autowired
    private CadastrarOngService cadastrarOngService;

    @Autowired
    private CriarCampanhaService criarCampanhaService;

    @Autowired
    private DecodeISO8859Util<CriarCampanhaDataRequest> decoder;

    @PostMapping("/cadastrar")
    @ResponseStatus(CREATED)
    private void cadastrarOng(@RequestBody CadastrarOngRequest request) {
        cadastrarOngService.cadastrar(request);
    }

    @PostMapping(path = "/campanha/criar")
    @ResponseStatus(CREATED)
    private void criarCampanha(@RequestPart String data, @RequestPart List<MultipartFile> fotos) {
        CriarCampanhaDataRequest request = decoder.decode(data, CriarCampanhaDataRequest.class);

        criarCampanhaService.criar(request, fotos);
    }
}
