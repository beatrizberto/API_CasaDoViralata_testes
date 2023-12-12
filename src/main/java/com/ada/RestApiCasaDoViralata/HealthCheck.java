package com.ada.RestApiCasaDoViralata;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthCheck {

    @GetMapping
    public String healthCheck(){
        return "CASA DO VIRALATA: Application is running.";
    }


}

