package com.uam.citas.Repository;

import com.uam.citas.Entity.CitaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<CitaEntity, Long> {
    List<CitaEntity> findByUsuario_IdUsuario(Long idUsuario);
}