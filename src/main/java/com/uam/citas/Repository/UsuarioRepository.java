package com.uam.citas.Repository;

import com.uam.citas.Entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
