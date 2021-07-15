package com.example.carros.repository;
import com.example.carros.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    List<Carro> findByTipo(String tipo);

    @Query(value = "SELECT * FROM carro WHERE tipo=:tipo", nativeQuery = true)
    List<Carro> list(@Param("tipo")String tipo);

}

