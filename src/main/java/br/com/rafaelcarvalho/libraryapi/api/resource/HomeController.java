package br.com.rafaelcarvalho.libraryapi.api.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String ola(){
        return "Olá, seja bem vindo...";
    }

}
