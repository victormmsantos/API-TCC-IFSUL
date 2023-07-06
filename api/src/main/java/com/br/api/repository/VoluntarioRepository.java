package com.br.api.repository;

import com.br.api.domain.model.Usuario;
import com.br.api.domain.model.Voluntario;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {


    Optional<Voluntario> findByUsuario(Usuario usuario);

    @Query(value = "SELECT * FROM voluntario v" +
            " INNER JOIN usuario u ON u.id = v.user_id" +
            " WHERE u.id = :id",
            nativeQuery = true)
    Optional<Voluntario> findByIdUsuario(@Param(value = "id") final Long id);

    @Query(value = "SELECT DISTINCT v.* FROM voluntario v" +
            " INNER JOIN voluntario_interesse vi ON vi.voluntario_id = v.id" +
            " INNER JOIN campanha c ON c.id = :idCampanha" +
            " WHERE c.id_ong = :idOng",
            nativeQuery = true)
    List<Voluntario> findVoluntarioInteressados(Long idCampanha, Long idOng);
}

