package com.arthurgsf.oqb.model.dto;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

import com.arthurgsf.oqb.model.entity.Quadra;
import com.arthurgsf.oqb.model.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {
    private LocalDate data;
    private LocalTime hora;
    private Long idUsuario;
    private Long idQuadra;
    private Long idHorarioFuncionamento;
}
