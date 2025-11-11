package emsi.tp1_graphql.repositories;

import emsi.tp1_graphql.models.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
}
