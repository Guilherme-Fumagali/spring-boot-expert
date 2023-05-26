package io.github.gfumagali.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> 
                    authz.requestMatchers("/api/clientes/**").hasAnyRole("USER", "ADMIN"))
                .authorizeHttpRequests((authz) ->
                        authz.requestMatchers("/api/pedidos/**").hasAnyRole("USER", "ADMIN"))
                .authorizeHttpRequests((authz) ->
                    authz.requestMatchers("/api/produtos/**").hasRole("ADMIN"))
                .httpBasic(withDefaults())
                .formLogin(withDefaults());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }    

    @Bean
    InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User
            .withUsername("gfumagali")
            .password(passwordEncoder().encode("password"))
            .roles("USER", "ADMIN")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
}
