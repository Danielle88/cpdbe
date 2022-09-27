package br.com.fiap.epictaskapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    /*@PostMapping
    public ResponseEntity<Object> auth(@RequestBody User user){

        Authentication authentication = 
            new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword());
        authentication.authenticate();

        // verificar se passa na autenticação


        //gerar o token

        //retornar como json

    }*/
    
}
