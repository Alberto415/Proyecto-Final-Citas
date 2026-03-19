package com.uam.citas.Repository;

import com.uam.citas.Entity.DetalleNutricionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DetalleNutricionRepository extends JpaRepository<DetalleNutricionEntity, Long> {

}