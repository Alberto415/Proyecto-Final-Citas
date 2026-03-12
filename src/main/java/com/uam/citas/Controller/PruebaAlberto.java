package com.uam.citas.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PruebaAlberto {

    @GetMapping("/prueba")
    public String pruebaController(){
        return "Controller ya funciona";
    }

}