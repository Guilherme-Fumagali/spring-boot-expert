package io.github.gfumagali;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StandardConfiguration {

    @Bean(name = "today")
    String today() {
        return LocalDate.now().toString();
    }

    @Bean(name = "yesterday")
    String yesterday() {
        return LocalDate.now().minusDays(1).toString();
    }
}
