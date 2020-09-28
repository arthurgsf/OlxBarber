package com.olxbarber.olxbarber;
import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="quadra")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Quadra {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;

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
    private Double preco;

    @ManyToOne
    @JoinColumn (name = "id_usuario")
    private Usuario usuario;
}
