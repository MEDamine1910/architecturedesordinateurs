package emsi.tp1_graphql.dtos;

import emsi.tp1_graphql.enums.Genre;

public record EtudiantDTO(String nom,
                          String prenom,
                          Genre genre,
                          Long centreId) {
}
