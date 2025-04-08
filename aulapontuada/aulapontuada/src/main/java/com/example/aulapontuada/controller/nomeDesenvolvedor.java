package com.example.aulapontuada.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dev")
public class nomeDesenvolvedor {
    @RequestMapping("/dev")
    public String nomeDesenvolvedor() {
        return "Nome do desenvolvedor: Caio Fernando Souza Pinheiro";
    }

}
