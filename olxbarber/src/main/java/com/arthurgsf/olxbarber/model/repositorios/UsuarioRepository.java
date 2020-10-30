package com.arthurgsf.olxbarber.model.repositorios;
import java.util.Optional;

import com.arthurgsf.olxbarber.model.entidades.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
