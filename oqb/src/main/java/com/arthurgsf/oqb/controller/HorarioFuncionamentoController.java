package com.arthurgsf.oqb.controller;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.arthurgsf.oqb.model.dto.HorarioFuncionamentoDTO;
import com.arthurgsf.oqb.model.entity.HorarioFuncionamento;
import com.arthurgsf.oqb.model.entity.Quadra;
import com.arthurgsf.oqb.model.entity.Usuario;
import com.arthurgsf.oqb.model.repo.HorarioFuncionamentoRepository;
import com.arthurgsf.oqb.service.HorarioFuncionamentoService;
import com.arthurgsf.oqb.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/funcionamento")
public class HorarioFuncionamentoController {
    @Autowired
    HorarioFuncionamentoService hrService;

    @PostMapping
    public ResponseEntity SalvaHorarioFuncionamento(@RequestBody HorarioFuncionamentoDTO horario){
        HorarioFuncionamento horarioFuncionamento = 
            HorarioFuncionamento.builder()
                                .diaSemana(horario.getDiaSemana())
                                .hora(LocalTime.of(horario.getHora(), horario.getMinuto()))
                                .quadra(Quadra.builder().Id(horario.getIdQuadra()).build())
                                .build();
        try{
            HorarioFuncionamento salvo = hrService.salvar(horarioFuncionamento);
            return new ResponseEntity(salvo, HttpStatus.OK);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity ListarHorariosFuncionamento(@RequestParam int id_quadra){
        Optional<List<HorarioFuncionamento>> horariosFuncionamento = hrService.buscarPorQuadra(Quadra.builder().Id(id_quadra).build());
        try{
            if (horariosFuncionamento.isPresent()){
                return new ResponseEntity(horariosFuncionamento.get(), HttpStatus.OK);
            }else{
                return new ResponseEntity(Collections.emptyList(), HttpStatus.OK);
            }
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
