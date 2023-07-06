package com.br.api.controller;

import com.br.api.domain.request.CadastrarAnimalDataRequest;
import com.br.api.domain.response.AnimalModelResponse;
import com.br.api.service.animal.ListarAnimaisOng;
import com.br.api.service.ong.CadastrarAnimalService;
import com.br.api.util.DecodeISO8859Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private CadastrarAnimalService cadastrarAnimalService;

    @Autowired
    private ListarAnimaisOng listarAnimaisOng;

    @Autowired
    private DecodeISO8859Util<CadastrarAnimalDataRequest> decoder;

    @PostMapping("/cadastrar")
    @ResponseStatus(CREATED)
    private void cadastrarAnimal(@RequestPart String data, @RequestPart List<MultipartFile> fotos) {
        CadastrarAnimalDataRequest request = decoder.decode(data, CadastrarAnimalDataRequest.class);

        cadastrarAnimalService.cadastrar(request, fotos);
    }

    @GetMapping("/listar/{id}")
    private List<AnimalModelResponse> listarAnimais(@PathVariable Long id) {
        return listarAnimaisOng.listar(id);
    }
}
