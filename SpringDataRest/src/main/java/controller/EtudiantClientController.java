package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.EtudiantClientService;
import service.EtudiantWebClient;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/etudiants")
public class EtudiantClientController {
    @Autowired
    private EtudiantClientService etudiantClientService;
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
}