package org.onlinecinema.reservationservice.repository;

import org.onlinecinema.reservationservice.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByFilmId(Long filmId);
    List<Reservation> findByUsername(String username);
}
