package org.example.clientrest.web;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class EtudiantWebClient {
    private final WebClient webClient = WebClient.create("http://localhost:8080");
    private static final String BASE_URL = "http://localhost:8080/etudiants";
    public Map getEtudiants() {
        return webClient.get()// Préparer une requête HTTP GET avec WebClient.
// Spécifie l’URI à appeler (ici le point d’accès /etudiants du serveur cible).
                .uri("/etudiants")
// Envoie la requête et prépare la récupération de la réponse.
                .retrieve()
// Convertir le corps de la réponse en un objet réactif Mono contenant une Map.
                .bodyToMono(Map.class)
// Bloque l’exécution jusqu’à recevoir la réponse (mode synchrone).
                .block();
    }
    public String deleteEtudiant(Long id) {
        webClient.delete() // Prépare une requête HTTP DELETE avec WebClient.
                .uri(BASE_URL + "/" + id) // Spécifie l’URL complète de la ressource à supprimer
                .retrieve() // Envoie la requête DELETE et prépare la récupération de la réponse du serveur.
                .toBodilessEntity() // Indique qu’on ne s’attend à aucun corps (body) dans la réponse, seulement le statut HTTP.
                .block();
        return "Etudiant: " + id + " deleted";
    }

    public Map<String, Object> addEtudiant(Map<String, Object> etudiant) {
        try {
            return webClient.post()
                    .uri(BASE_URL)
                    .contentType(MediaType.APPLICATION_JSON) // Spécifie que le corps de la requête sera au format JSON.
                    .bodyValue(etudiant) // Définit le corps de la requête : l’objet 'etudiant' sera converti en JSON.
                    .retrieve() // Envoie la requête POST et prépare la récupération de la réponse
                    .bodyToMono(Map.class) // Convertit la réponse JSON reçue en un objet Map.
                    .block();
        } catch (Exception e) {
            e.printStackTrace();
            return Map.of("error", "Erreur lors de l’ajout");
        }
    }
}
