package com.uam.citas.Repository;

import com.uam.citas.Entity.DetalleMedicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleMedicaRepository extends JpaRepository<DetalleMedicaEntity, Long> {
    void deleteByCita_IdCita(Long idCita);
}