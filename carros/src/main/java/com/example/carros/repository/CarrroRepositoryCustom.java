package com.example.carros.repository;

import com.example.carros.model.Carro;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarrroRepositoryCustom {

    private EntityManager em;

    public CarrroRepositoryCustom(EntityManager em) {
        this.em = em;
    }

    public List<Carro> find(Long id, String nome, String tipo) {
        String query = "SELECT c FROM carro as c ";
        String condicao = "WHERE";
        if (id != null) {
            query += condicao + " c.id = :id ";
            condicao = "AND";
        }
        if (nome != null) {
            query += condicao + " c.nome LIKE :nome ";
            condicao = "AND";
        }
        if (tipo != null) {
            query += condicao + " c.tipo LIKE :tipo ";
        }

        TypedQuery<Carro> q = em.createQuery(query, Carro.class);


        if (id != null) {
           q.setParameter("id",id);
        }
        if (nome != null) {
            q.setParameter("nome","%"+nome+"%");
        }
        if (tipo != null) {
            q.setParameter("tipo","%"+tipo+"%");
        }

        return q.getResultList();
    }

}
