package com.uam.citas.Repository;

import com.uam.citas.Entity.DetallePsicologicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePsicologicaRepository extends JpaRepository<DetallePsicologicaEntity, Long> {
    void deleteByCita_IdCita(Long idCita);
}