package com.arthurgsf.olxbarber.model.repositorios;

import com.arthurgsf.olxbarber.model.entidades.Horario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioRepository extends JpaRepository<Horario, Long> {
    
}
