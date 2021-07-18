package com.example.carros.service;

import com.example.carros.exception.ObjectNotFound;
import com.example.carros.model.Carro;
import com.example.carros.model.dto.CarroDTO;
import com.example.carros.repository.CarroRepository;

import com.example.carros.repository.CarrroRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {
    @Autowired
    private CarroRepository rep;
    @Autowired
    private CarrroRepositoryCustom repCustom;


    public List<CarroDTO> getCarros() {
        List<Carro> carros = rep.findAll();

        List<CarroDTO> listAll = carros.stream().map(c -> new CarroDTO(c)).collect(Collectors.toList());

        return listAll;
    }

    public CarroDTO getCarrosById(Long id) {
        return rep.findById(id).map(c -> new CarroDTO(c)).orElseThrow(()->new ObjectNotFound("Carro não encontrado"));
    }

    public List<CarroDTO> getCarroByTipo(String tipo) {
        List<Carro> carros = rep.findByTipo(tipo);
        List<CarroDTO> listByTipo = carros.stream().map(c -> new CarroDTO(c)).collect(Collectors.toList());
        return listByTipo;

    }

    public CarroDTO insert(Carro carro) {
        Assert.isNull(carro.getId(),"Nao foi possivel inserir o registro!");
        return new CarroDTO(rep.save(carro));
    }

    public CarroDTO update(Carro carro, Long id) {
        Assert.notNull(id, "ID nao localizada, impossivel alterar!");

        //Busca o carro no bando de dados
        Optional<Carro> optional = rep.findById(id);
        if (optional.isPresent()) {
            Carro db = optional.get();
            //Copiar as propriedades do Body para o banco de dados
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            db.setDescricao(carro.getDescricao());

            rep.save(db);
            return new CarroDTO(db);

        } else {
            return null;
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }

    public List<Carro> getAllTipo(String tipo) {
        return rep.list(tipo);
    }

    public List<Carro> getAllCustom(Long id, String nome, String tipo) {
        return repCustom.find(id, nome, tipo);
    }
}
