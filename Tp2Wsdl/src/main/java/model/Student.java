package model;


import enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class Student {
    private int id;
    private String nom;
    private String prenom;
    private Genre genre;

}
