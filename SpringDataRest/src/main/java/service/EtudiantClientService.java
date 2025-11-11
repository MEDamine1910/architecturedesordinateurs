package service;

import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

public class EtudiantClientService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "http://localhost:8080/etudiants";
    private static final String BASE_URL2 = "http://localhost:8080/etudiants/search/findEtudiantByNom?n={nom}";
    //private final ObjectMapper mapper = new ObjectMapper();
    public List<Map<String, Object>> getAllEtudiants() {
// Envoyer une requête HTTP GET à l’URL BASE_URL et récupère la réponse JSON sous forme de Map.
        Map response = restTemplate.getForObject(BASE_URL, Map.class);
// Accèder à la clé "_embedded" dans la réponse, puis à la liste "etudiants", et la retourne.
        return (List<Map<String, Object>>) ((Map)response.get("_embedded")).get("etudiants");
    }
    public List<Map<String, Object>> getEtudiantsByName(String nom) {
        Map response = restTemplate.getForObject(BASE_URL2, Map.class,nom);
        return (List<Map<String, Object>>) ((Map)response.get("_embedded")).get("etudiants");
    }
    public Map<String, Object> getEtudiantById(Integer id) {
        return restTemplate.getForObject(BASE_URL + "/" + id, Map.class);
    }
    public void addEtudiant(Map<String, Object> etudiant) {
        restTemplate.postForObject(BASE_URL, etudiant, Map.class);
    }
    public String deleteEtudiant(Integer id) {
        String url = BASE_URL + "/" + id;
        restTemplate.delete(url);
        return "Etudiant " + id + " deleted";
    }
    public String updateEtudiant(Integer id, Map<String, Object> etudiant) {
        String url = BASE_URL + "/" + id;
        restTemplate.put(url, etudiant);
        return "Etudiant " + id + " updated";
    }
    public List<Map<String, Object>> getEtudiants(int page, int size, String sort) {
        String url = BASE_URL + "?page={page}&size={size}&sort={sort}";
        Map response = restTemplate.getForObject(url, Map.class,page,size,sort);
        return (List<Map<String, Object>>) ((Map)response.get("_embedded")).get("etudiants");
    }
}
