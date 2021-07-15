package com.example.carros.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "carro")
@NoArgsConstructor
@AllArgsConstructor
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Column(name = "id")
    private Long id;
    @Column(name = "nome")
    @Getter
    @Setter
    private String nome;
    @Column(name = "tipo")
    @Getter
    @Setter
    private String tipo;
    @Column(name = "descricao")
    @Getter
    @Setter
    private String descricao;


}

