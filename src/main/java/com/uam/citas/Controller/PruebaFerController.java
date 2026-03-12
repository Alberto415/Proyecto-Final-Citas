package com.uam.citas.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class PruebaFerController {

    @GetMapping("/pruebaFer")
    public String PruebaController(){
        return "Esta es mi prueba: Fernanda";
    }



}
