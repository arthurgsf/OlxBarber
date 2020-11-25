package com.arthurgsf.oqb.controller;

import com.arthurgsf.oqb.model.entity.Quadra;
import com.arthurgsf.oqb.model.entity.Usuario;
import com.arthurgsf.oqb.service.QuadraService;
import com.arthurgsf.oqb.model.dto.QuadraDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quadra")
public class QuadraController {
    @Autowired
    QuadraService service;

    @PostMapping
    public ResponseEntity salvar(@RequestBody QuadraDto dto) {

        Quadra qd = Quadra.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .endereco(dto.getEndereco())
                .esportes(dto.getEsportes())
                .horarios(dto.getHorarios())
                .preco(dto.getPreco())
                .idUsuario(Usuario.builder().id(dto.getIdUsuario()).build())
                .build();

        try {
            Quadra quadra = service.salvar(qd);
            return new ResponseEntity(quadra, HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
