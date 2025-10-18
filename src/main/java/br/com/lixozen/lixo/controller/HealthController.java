package br.com.lixozen.lixo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    
    @GetMapping("/")
    public String home() {
        return "LixoZen API está rodando! ✅";
    }
    
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}