package org.onlinecinema.reservationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "film-service")
public interface FilmClient {

    @GetMapping("/films/{id}")
    FilmDTO getFilmById(@PathVariable("id") Long id);

    @PutMapping("/films/{id}/update-places")
    FilmDTO updatePlaces(@PathVariable("id") Long id, @RequestBody UpdatePlacesRequest request);

    class FilmDTO {
        public Long id;
        public String titre;
        public int dureeMinutes;
        public int placesDisponibles;
    }

    class UpdatePlacesRequest {
        public int seatsToReserve;
    }
}
