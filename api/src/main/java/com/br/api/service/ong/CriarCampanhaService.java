package com.br.api.service.ong;

import com.br.api.domain.model.Campanha;
import com.br.api.domain.model.Ong;
import com.br.api.domain.model.Voluntario;
import com.br.api.domain.request.CriarCampanhaDataRequest;
import com.br.api.repository.VoluntarioRepository;
import com.br.api.service.security.AuthenticatedUserService;
import com.br.api.service.notificacao.EnviarNotificacaoCampanhaService;
import com.br.api.service.notificacao.twillio.TwillioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CriarCampanhaService {

    private final VoluntarioRepository voluntarioRepository;

    private final EnviarNotificacaoCampanhaService sendNotificationService;

    private final CadastrarCampanhaService cadastrarCampanhaService;

    private final AuthenticatedUserService authenticatedUserService;

    private final BuscarOngService buscarOngService;

    private final TwillioService twillioService;

    @Transactional
    public void criar(CriarCampanhaDataRequest request, List<MultipartFile> fotos) {

        Campanha campanhaCriada = cadastrarCampanhaService.cadastrar(request, fotos);

        Ong ong = buscarOngService.porIdUsuario(authenticatedUserService.getId());

        List<Voluntario> voluntarioInteressados = voluntarioRepository.findVoluntarioInteressados(campanhaCriada.getId(), ong.getId());
        sendNotificationService.send(twillioService, campanhaCriada, voluntarioInteressados);
    }
}
