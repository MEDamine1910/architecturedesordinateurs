package org.example.clientrest.Controller;

import org.example.clientrest.services.EtudiantSerice;
import org.example.clientrest.web.EtudiantWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/etudiants")
public class EtudiantClientController {
    @Autowired
    private EtudiantSerice etudiantClientService;
    @Autowired
    private EtudiantWebClient etudiantWebClient;

    @GetMapping
    public List<Map<String, Object>> getAllEtudiants() {
        return etudiantClientService.getAllEtudiants();
    }

    @GetMapping("/{p}")
    public Map<String, Object> getEtudiantById(@PathVariable("p") Integer id) {
        return etudiantClientService.getEtudiantById(id);
    }

    @PostMapping
    public void addEtudiant(@RequestBody Map<String, Object> etudiant) {
        etudiantClientService.addEtudiant(etudiant);
    }

    @PutMapping("/{p}")
    public String updateEtudiant(@PathVariable("p") Integer id, @RequestBody Map<String, Object> etudiant) {
        return etudiantClientService.updateEtudiant(id, etudiant);
    }

    @DeleteMapping("/{p}")
    public String deleteEtudiant(@PathVariable("p") Integer id) {
        return etudiantClientService.deleteEtudiant(id);
    }

    @GetMapping("/nom/{n}")
    public List<Map<String, Object>> getEtudiantsParNom(@PathVariable("n") String nom) {
        return etudiantClientService.getEtudiantsByName(nom);
    }

    @GetMapping("/{p}/{s}/{t}") //http://localhost:8081/etudiants/0/2/nom,desc
    public List<Map<String, Object>> getEtudiants(@PathVariable("p") int page, @PathVariable("s") int size, @PathVariable("t") String sort) {
        return etudiantClientService.getEtudiants(page, size, sort);
    }
}