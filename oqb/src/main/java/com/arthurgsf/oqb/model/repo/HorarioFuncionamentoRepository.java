package com.arthurgsf.oqb.model.repo;

import java.util.List;
import java.util.Optional;

import com.arthurgsf.oqb.model.entity.HorarioFuncionamento;
import com.arthurgsf.oqb.model.entity.Quadra;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioFuncionamentoRepository extends JpaRepository<HorarioFuncionamento, Long> {
    Optional<List<HorarioFuncionamento>> findByQuadra(Quadra quadra);
}
