package org.example.spring_security.model;

import jakarta.persistence.*;

@Table(name="notes")
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String username;
    String matiere;
    int note;
}
