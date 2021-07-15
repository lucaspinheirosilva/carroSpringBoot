package com.example.carros.security;
import com.example.carros.model.Usuario;
import com.example.carros.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class UserDatailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRespository userRep;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Usuario usuario = userRep.findByLogin(userName);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario não encontrado");
        }
        return usuario;
    }
}
