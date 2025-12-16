package org.onlinecinema.reservationservice.service;


import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.onlinecinema.reservationservice.client.FilmClient;
import org.onlinecinema.reservationservice.entities.Reservation;
import org.onlinecinema.reservationservice.exception.FilmNotFoundException;
import org.onlinecinema.reservationservice.exception.InsufficientPlacesException;
import org.onlinecinema.reservationservice.repository.ReservationRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final FilmClient filmClient;

    @Transactional
    public Reservation createReservation(Reservation reservation) {
        try {
            // Récupérer le username de l'utilisateur authentifié
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication != null ? authentication.getName() : null;
            
            if (username == null) {
                throw new RuntimeException("Utilisateur non authentifié");
            }
            
            // Associer le username à la réservation
            reservation.setUsername(username);
            
            // Vérifier que le film existe et récupérer ses informations
            FilmClient.FilmDTO film = filmClient.getFilmById(reservation.getFilmId());
            
            // Vérifier la disponibilité
            if (film.placesDisponibles < reservation.getSeatsReserved()) {
                throw new InsufficientPlacesException(
                    "Pas assez de places disponibles ! Disponibles: " + 
                    film.placesDisponibles + ", Demandées: " + reservation.getSeatsReserved()
                );
            }

            // Mettre à jour les places disponibles dans Film Service
            FilmClient.UpdatePlacesRequest updateRequest = new FilmClient.UpdatePlacesRequest();
            updateRequest.seatsToReserve = reservation.getSeatsReserved();
            
            filmClient.updatePlaces(reservation.getFilmId(), updateRequest);

            // Créer la réservation
            return reservationRepository.save(reservation);
            
        } catch (FeignException.NotFound e) {
            throw new FilmNotFoundException("Film non trouvé avec l'ID: " + reservation.getFilmId());
        } catch (FeignException e) {
            throw new RuntimeException("Erreur de communication avec le service Film: " + e.getMessage());
        }
    }
    
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
    
    public List<Reservation> getReservationsByUsername(String username) {
        return reservationRepository.findByUsername(username);
    }
    
    public List<Reservation> getReservationsByFilmId(Long filmId) {
        return reservationRepository.findByFilmId(filmId);
    }
}
