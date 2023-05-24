package io.github.gfumagali;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import io.github.gfumagali.annotations.Development;

@Development
public class DevelopmentConfiguration {

    @Bean
    public CommandLineRunner exec(){
        return args -> {
            System.out.println("Ambiente de Desenvolvimento");
        };
    }
}
