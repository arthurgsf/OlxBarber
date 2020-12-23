package com.arthurgsf.oqb.controller;

import com.arthurgsf.oqb.model.entity.HorarioFuncionamento;
import com.arthurgsf.oqb.model.entity.Quadra;
import com.arthurgsf.oqb.model.entity.Reserva;
import com.arthurgsf.oqb.model.entity.Usuario;
import com.arthurgsf.oqb.service.ReservaService;
import com.arthurgsf.oqb.model.dto.ReservaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reserva")
public class ReservaController {
    @Autowired
    ReservaService reservaService;

    /**
     * Cria uma reserva a uma quadra relacionando uma data e horário escolhido pelo usuário
     * com uma quadra e seu horario de funcionamento
     * @param dto
     * @return ok ou error
     */
    @PostMapping
    public ResponseEntity salvarReserva(@RequestBody ReservaDTO dto) {

        Reserva reserva = Reserva.builder()
                .hora(dto.getHora())
                .data(dto.getData())
                .usuario(Usuario.builder().id(dto.getIdUsuario()).build())
                .quadra(Quadra.builder().id(dto.getIdQuadra()).build())
                .horarioFuncionamento(HorarioFuncionamento.builder().id(dto.getIdHorarioFuncionamento()).build())
                .build();

        try {
            Reserva novo = reservaService.salvar(reserva);
            return new ResponseEntity(novo, HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // @DeleteMapping
    // public ResponseEntity deletarReserva(@RequestParam Long id){
    //     try{
    //         reservaService.deletar(id);
    //         return ResponseEntity.ok(true);
    //     }catch(RuntimeException e){
    //         return ResponseEntity.badRequest().body(e.getMessage());
    //     }
    // }

    // @GetMapping("/todas")
    // public ResponseEntity obterReservas(){

    //     try{
    //         List<Reserva> Reservas = reservaService.obterReservas();
    //         return new ResponseEntity(Reservas, HttpStatus.OK);
    //     }catch(Exception e){
    //         return ResponseEntity.badRequest().body(e.getMessage());
    //     }
    // }

    // @GetMapping("/{id}")
    // public ResponseEntity obterReserva(@PathVariable("id") Long idReserva) {
    //     Reserva qdr = Reserva.builder().id(idReserva).build();
    //     try {
    //         Reserva Reserva = reservaService.obterReserva(qdr);
    //         return new ResponseEntity(Reserva, HttpStatus.OK);
    //     }
    //     catch(Exception e) {
    //         return ResponseEntity.badRequest().body(e.getMessage());
    //     }
    // }
}
