package com.arthurgsf.oqb.controller;

import com.arthurgsf.oqb.model.entity.Quadra;
import com.arthurgsf.oqb.model.entity.Usuario;
import com.arthurgsf.oqb.service.QuadraService;
import com.arthurgsf.oqb.model.dto.QuadraDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quadra")
public class QuadraController {
    @Autowired
    QuadraService qdService;

    @PostMapping
    public ResponseEntity salvarQuadra(@RequestBody QuadraDto dto) {

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
            Quadra quadra = qdService.salvar(qd);
            return new ResponseEntity(quadra, HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity deletarQuadra(@RequestParam Long id){
        try{
            qdService.deletar(id);
            return ResponseEntity.ok(true);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity obterQuadras(){

        try{
            List<Quadra> quadras = qdService.obterQuadras();
            return new ResponseEntity(quadras, HttpStatus.OK);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity obterQuadra(@PathVariable("id") Long idQuadra) {
        Quadra qdr = Quadra.builder().id(idQuadra).build();
        try {
            Quadra quadra = qdService.obterQuadra(qdr);
            return new ResponseEntity(quadra, HttpStatus.OK);
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
