package com.br.api.service.midia;

import org.springframework.web.multipart.MultipartFile;

public interface SalvarMidiaInterface {

    public String uploadFile(MultipartFile file);

    public void deleteFile(String fileId);

}
