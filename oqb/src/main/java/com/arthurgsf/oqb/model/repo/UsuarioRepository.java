package com.arthurgsf.oqb.model.repo;

import java.util.Optional;

import com.arthurgsf.oqb.model.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String Email);
    boolean existsByEmail(String Email);
}