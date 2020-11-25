package com.arthurgsf.oqb.model.repo;

import java.util.Optional;

import com.arthurgsf.oqb.model.entity.Quadra;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuadraRepository extends JpaRepository<Quadra, Long> {

    Optional<Quadra> findByNome(String nome);
    boolean existsByNome(String nome);
    boolean existsByEndereco(String endereco);
}
