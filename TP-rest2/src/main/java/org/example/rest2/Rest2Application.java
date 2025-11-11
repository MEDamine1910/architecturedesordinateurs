package org.example.rest2;

import org.example.rest2.Repo.EtudiantRepo;
import org.example.rest2.entité.Etudiant;
import org.example.rest2.enums.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Rest2Application implements CommandLineRunner {
    @Autowired
    EtudiantRepo etudiantRepo;
    @Override
    public void run(String... args) throws Exception {
        Etudiant etudiant = Etudiant.builder().nom("filali").prenom("omar").genre(Genre.autre).build();
        etudiantRepo.save(etudiant);
    }

    public static void main(String[] args) {
        SpringApplication.run(Rest2Application.class, args);
    }

}
