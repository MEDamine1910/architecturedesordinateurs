package org.onlinecinema.filmservice.repository;

import org.onlinecinema.filmservice.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path ="films")
public interface FilmRepository extends JpaRepository<Film, Long> {
}
