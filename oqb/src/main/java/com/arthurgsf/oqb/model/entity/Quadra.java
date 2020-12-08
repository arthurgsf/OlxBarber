package com.arthurgsf.oqb.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "quadra")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Quadra {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "esportes")
    private String esportes;

    @Column(name = "preco")
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}