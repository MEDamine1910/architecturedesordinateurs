package org.onlinecinema.reservationservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "L'ID du film est requis")
    private Long filmId;

    @NotBlank(message = "Le nom du client est requis")
    private String clientName;

    // Le username est rempli automatiquement par le service depuis le token JWT
    // Il n'est pas requis dans la requête du client
    private String username;

    @Min(value = 1, message = "Au moins une place doit être réservée")
    private int seatsReserved;
}
