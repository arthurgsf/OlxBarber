package com.arthurgsf.oqb.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Quadra")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Quadra {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "nome")
    private String Nome;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "email")
    private String Email;

    @Column(name = "esportes")
    private String Esportes;
    
    @Column(name = "preco")
    private Double Preco;

}
