package com.br.api.service.midia;

import com.br.api.service.midia.SalvarMidiaInterface;
import com.br.api.service.midia.aws.AwsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalvarFotoService {

    private final AwsService service;

    public String salvar(MultipartFile file, SalvarMidiaInterface bucket) {
        return bucket.uploadFile(file);
    }

    public List<String> salvar(List<MultipartFile> file, SalvarMidiaInterface bucket) {
        return file.stream().map(bucket::uploadFile).collect(Collectors.toList());
    }
}
