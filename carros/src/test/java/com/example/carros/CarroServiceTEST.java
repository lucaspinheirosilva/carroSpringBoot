package com.example.carros;

import com.example.carros.exception.ObjectNotFound;
import com.example.carros.model.Carro;
import com.example.carros.model.CarroService;
import com.example.carros.model.dto.CarroDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.Optional;


@SpringBootTest
public class CarroServiceTEST {

    @Autowired
    private CarroService service;

    @Test
    public void insert(){
        Carro carro = new Carro();

        carro.setNome("Fusca");
        carro.setTipo("Classico");
        carro.setDescricao("Volkswagen Fusca 1968");
        CarroDTO c = service.insert(carro);

        Assert.notNull(c,"NAO PODE SER NULO");


        //busca objeto
        c = service.getCarrosById(c.getId());
        Assert.notNull(c,"NAO PODE SER NULO");



        assertEquals("Fusca",c.getNome());
        assertEquals("Classico",c.getTipo());

        service.delete(c.getId());

        try {
            Assert.isNull(service.getCarrosById(c.getId()));
            System.out.println("O CARRO NAO FOI EXCLUIDO");
        }catch (ObjectNotFound objectNotFound){
            //OK
        }



    }

}
