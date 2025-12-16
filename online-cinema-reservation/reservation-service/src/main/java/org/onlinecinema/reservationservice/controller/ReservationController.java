package org.onlinecinema.reservationservice.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.onlinecinema.reservationservice.entities.Reservation;
import org.onlinecinema.reservationservice.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Reservation> create(@Valid @RequestBody Reservation reservation) {
        Reservation created = reservationService.createReservation(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getMyReservations() {
        // Récupérer le username de l'utilisateur authentifié
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication != null ? authentication.getName() : null;
        
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        // Retourner uniquement les réservations de l'utilisateur connecté
        return ResponseEntity.ok(reservationService.getReservationsByUsername(username));
    }

    @GetMapping("/film/{filmId}")
    public ResponseEntity<List<Reservation>> getByFilmId(@PathVariable Long filmId) {
        return ResponseEntity.ok(reservationService.getReservationsByFilmId(filmId));
    }
}
