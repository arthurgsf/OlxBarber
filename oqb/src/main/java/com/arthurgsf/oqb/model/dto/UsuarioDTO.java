package com.arthurgsf.oqb.model.dto;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UsuarioDTO {
    private String nome;
    private String login;
    private String senha;
    private String email;
    private String telefone;
}
