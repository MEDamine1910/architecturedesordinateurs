package org.onlinecinema.filmservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.onlinecinema.filmservice.entities.Film;
import org.onlinecinema.filmservice.service.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @GetMapping("/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable Long id) {
        Film film = filmService.getFilmById(id);
        return ResponseEntity.ok(film);
    }

    @PutMapping("/{id}/update-places")
    public ResponseEntity<Film> updatePlaces(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePlacesRequest request) {
        Film film = filmService.updateAvailablePlaces(id, request.getSeatsToReserve());
        return ResponseEntity.ok(film);
    }

    @Data
    static class UpdatePlacesRequest {
        @Min(value = 1, message = "Au moins une place doit être réservée")
        private int seatsToReserve;
    }
}

