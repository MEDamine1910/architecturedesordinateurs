package org.example.rest2.Repo;

import org.example.rest2.entité.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface EtudiantRepo extends JpaRepository<Etudiant, Long> {
}
