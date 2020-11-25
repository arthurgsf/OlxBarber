package com.arthurgsf.oqb.model.dto;

import com.arthurgsf.oqb.model.entity.Quadra;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
public class QuadraDto {

    private String nome;
    private String endereco;
    private String descricao;
    private String esportes;
    private Double preco;
    private Timestamp horarios;
    private Long idUsuario;

}
