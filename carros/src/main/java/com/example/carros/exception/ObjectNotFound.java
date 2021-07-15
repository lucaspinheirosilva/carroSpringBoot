package com.example.carros.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ObjectNotFound extends RuntimeException {
    public  ObjectNotFound(String mensagem){super(mensagem);}

    public ObjectNotFound(String mensagem,Throwable causa){super(mensagem,causa);}
}
