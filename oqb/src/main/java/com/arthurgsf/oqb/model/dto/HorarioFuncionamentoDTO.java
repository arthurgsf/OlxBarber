package com.arthurgsf.oqb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class HorarioFuncionamentoDTO {
    private long idQuadra;
    private Integer diaSemana;
    private Integer hora;
    private Integer minuto;
}
