package com.uam.citas.Repository;

import com.uam.citas.Entity.ExpedienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpedienteRepository extends JpaRepository<ExpedienteEntity, Long> {

}