package io.github.gfumagali.config;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import io.github.gfumagali.service.impl.UsuarioServiceImpl;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfig {   
    @Autowired
    UsuarioServiceImpl usuarioService;

    @Bean 
    DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher(ApplicationEventPublisher publisher) {
        return new DefaultAuthenticationEventPublisher(publisher);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        http
            .csrf(csrf -> csrf.disable())
            /* .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/api/clientes/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/api/pedidos/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/api/produtos/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/usuarios/**").permitAll()
                .anyRequest().authenticated())  */
            .authorizeHttpRequests((authorize) -> authorize
            .requestMatchers(HttpMethod.POST, "/api/usuarios/**").permitAll()
                .anyRequest().permitAll())
            .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder();
    }
}