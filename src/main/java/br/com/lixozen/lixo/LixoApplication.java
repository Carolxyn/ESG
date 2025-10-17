package br.com.lixozen.lixo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.lixozen.lixo")
public class LixoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LixoApplication.class, args);
	}
}
