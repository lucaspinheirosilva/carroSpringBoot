package com.example.carros.model.dto;


import com.example.carros.model.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;


@Data
public class UsuarioDTO {
    private String login;
    private String nome;
    private String email;

    // token jwt
    private String token;

    public UsuarioDTO (Usuario usuario, String token) {
        this.login = usuario.getLogin();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.token = token;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();
        return m.writeValueAsString(this);
    }
}