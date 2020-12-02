package com.arthurgsf.oqb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HorarioFuncionamentoDTO {
    private long idQuadra;
    private Integer diaSemana;
    private Integer hora;
    private Integer minuto;
}
