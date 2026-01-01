package org.example.spring_security.controller;

import org.example.spring_security.model.Etudiant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EtudiantController {
    @GetMapping("/liste")
    public List<Etudiant>getEtudiants(){
        return List.of(
                new Etudiant(1,"Lamrani","Yousra"),
                new Etudiant(2,"kessou","Abdo"),
                new Etudiant(3,"chadli","Adam")
        );
    }
    @GetMapping("/msg")
    public String getMessage(){
        return "Utilistaurs";
    }
}