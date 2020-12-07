package com.arthurgsf.oqb.model.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuadraDTO {

    private String nome;
    private String endereco;
    private String descricao;
    private String esportes;
    private Double preco;
    private Long idUsuario;
}
