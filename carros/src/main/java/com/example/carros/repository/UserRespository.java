package com.example.carros.repository;

import com.example.carros.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository <Usuario,Long>{
    Usuario findByLogin(String login);
}
