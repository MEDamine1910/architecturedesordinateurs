package org.onlinecinema.filmservice.config;

import org.onlinecinema.filmservice.entities.Film;
import org.onlinecinema.filmservice.repository.FilmRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner init(FilmRepository repo) {
        return args -> {
            repo.save(new Film(null,"Casanegra", 169, 50, 50));
            repo.save(new Film(null,"Offender", 136, 80, 80));
            repo.save(new Film(null,"A Prayer Before Dawn", 148, 100, 100));
            repo.save(new Film(null,"Matrix", 136, 100, 100));
            repo.save(new Film(null,"Inception", 148, 80, 80));
            repo.save(new Film(null,"Interstellar", 169, 100, 100));
        };
    }
}
