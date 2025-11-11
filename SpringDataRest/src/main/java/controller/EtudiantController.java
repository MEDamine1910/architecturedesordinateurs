package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import service.EtudiantClient;

import java.util.List;
import java.util.Map;

@RestController
public class EtudiantController {
    EtudiantClient etudiantClient;
    public EtudiantController(EtudiantClient etudiantClient) {
        this.etudiantClient = etudiantClient;
    }
    @GetMapping("/api/etudiants")
    public List<Map<String, Object>> getAllEtudiants() {
// Récupération de la réponse du service Spring Data REST
        Map<String, Object> response = etudiantClient.getAllEtudiants();
// Les données des étudiants sont dans "_embedded" → "etudiants"
        Map<String, Object> embedded = (Map<String, Object>) response.get("_embedded");
        List<Map<String, Object>> etudiantsData = (List<Map<String, Object>>) embedded.get("etudiants");
        return etudiantsData;
    }
}