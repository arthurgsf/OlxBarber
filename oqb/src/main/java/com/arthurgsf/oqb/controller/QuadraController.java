package com.arthurgsf.oqb.controller;

import com.arthurgsf.oqb.model.entity.Quadra;
import com.arthurgsf.oqb.model.entity.Usuario;
import com.arthurgsf.oqb.service.QuadraService;
import com.arthurgsf.oqb.model.dto.QuadraDTO;
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

    /**
     * Recebe um de quadra DTO e salva no banco de dados
     *
     * @param dto
     * @return ok ou error
     */
    @PostMapping
    public ResponseEntity salvarQuadra(@RequestBody QuadraDTO dto) {

        Quadra qd = Quadra.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .endereco(dto.getEndereco())
                .esportes(dto.getEsportes())                
                .preco(dto.getPreco())
                .usuario(Usuario.builder().id(dto.getIdUsuario()).build())
                .build();

        try {
            Quadra quadra = qdService.salvar(qd);
            return new ResponseEntity(quadra, HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Deleta objeto quadra do banco
     *
     * @param id
     * @return ok ou error
     */
    @DeleteMapping
    public ResponseEntity deletarQuadra(@RequestParam Long id){
        try{
            qdService.deletar(id);
            return ResponseEntity.ok(true);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Retorna todas as quadras cadastradas na aplicação
     * @return List<Quadra>
     */
    @GetMapping("/todas")
    public ResponseEntity obterQuadras(){

        try{
            List<Quadra> quadras = qdService.obterQuadras();
            return new ResponseEntity(quadras, HttpStatus.OK);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Obtém uma quadra a partir do id
     * @param idQuadra
     * @return quadra
     */

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
