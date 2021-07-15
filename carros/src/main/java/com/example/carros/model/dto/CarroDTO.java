package com.example.carros.model.dto;


import com.example.carros.model.Carro;
import com.sun.istack.NotNull;
import lombok.*;

@Data
public class CarroDTO {

    private Long id;
    private String nome;
    private String tipo;


    public CarroDTO(Carro c){
        this.id = c.getId();
        this.nome = c.getNome();
        this.tipo = c.getTipo();
    }



}
