package com.arthurgsf.oqb.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.*;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@Entity
@Table(name = "reserva")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "data")   
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate data;

    @Column(name = "hora")
    @Convert(converter = Jsr310JpaConverters.LocalTimeConverter.class)
    private LocalTime hora;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_quadra")
    private Quadra quadra;

    @ManyToOne
    @JoinColumn(name = "id_horario_funcionamento")
    private HorarioFuncionamento horarioFuncionamento;
}