package com.uam.citas.Repository;

import com.uam.citas.Entity.EspecialistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialistaRepository extends JpaRepository<EspecialistaEntity, Long> {
}