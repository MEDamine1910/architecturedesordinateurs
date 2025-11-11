package emsi.tp1_graphql;

import emsi.tp1_graphql.enums.Genre;
import emsi.tp1_graphql.models.Centre;
import emsi.tp1_graphql.models.Etudiant;
import emsi.tp1_graphql.repositories.CentreRepository;
import emsi.tp1_graphql.repositories.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Tp1GraphQlApplication implements CommandLineRunner {
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    CentreRepository centreRepository;
    public static void main(String[] args) {
        SpringApplication.run(Tp1GraphQlApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        Centre centre1=Centre.builder()
                .nom("Maarif")
                .adresse("Biranzarane")
                .build();
        centreRepository.save(centre1);
        Centre centre2=Centre.builder()
                .nom("Oranges")
                .adresse("Oulfa")
                .build();
        centreRepository.save(centre2);
        Etudiant et1=Etudiant.builder()
                .nom("Adnani").
                prenom("Brahim").
                genre(Genre.Homme)
                .centre(centre1).
                build();
        etudiantRepository.save(et1);
        Etudiant et2 = Etudiant.builder()
                .nom("El Amrani")
                .prenom("Sara")
                .genre(Genre.Femme)
                .centre(centre2)
                .build();
        etudiantRepository.save(et2);
    }
}
