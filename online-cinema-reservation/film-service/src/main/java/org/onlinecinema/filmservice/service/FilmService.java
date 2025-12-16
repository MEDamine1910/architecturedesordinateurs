package org.onlinecinema.filmservice.service;

import lombok.RequiredArgsConstructor;
import org.onlinecinema.filmservice.entities.Film;
import org.onlinecinema.filmservice.exception.FilmNotFoundException;
import org.onlinecinema.filmservice.exception.InsufficientPlacesException;
import org.onlinecinema.filmservice.repository.FilmRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;

    public Film getFilmById(Long id) {
        return filmRepository.findById(id)
                .orElseThrow(() -> new FilmNotFoundException("Film non trouvé avec l'ID: " + id));
    }

    @Transactional
    public Film updateAvailablePlaces(Long id, int seatsToReserve) {
        Film film = getFilmById(id);
        
        if (seatsToReserve <= 0) {
            throw new IllegalArgumentException("Le nombre de places à réserver doit être positif");
        }
        
        if (film.getPlacesDisponibles() < seatsToReserve) {
            throw new InsufficientPlacesException(
                "Pas assez de places disponibles. Disponibles: " + 
                film.getPlacesDisponibles() + ", Demandées: " + seatsToReserve
            );
        }
        
        film.setPlacesDisponibles(film.getPlacesDisponibles() - seatsToReserve);
        return filmRepository.save(film);
    }

    @Transactional
    public Film releasePlaces(Long id, int seatsToRelease) {
        Film film = getFilmById(id);
        
        if (seatsToRelease <= 0) {
            throw new IllegalArgumentException("Le nombre de places à libérer doit être positif");
        }
        
        int newAvailable = film.getPlacesDisponibles() + seatsToRelease;
        
        if (newAvailable > film.getPlacesTotales()) {
            newAvailable = film.getPlacesTotales();
        }
        
        film.setPlacesDisponibles(newAvailable);
        return filmRepository.save(film);
    }
}

