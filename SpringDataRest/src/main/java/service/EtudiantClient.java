package service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(name = "etudiant-service", url = "http://localhost:8080")
public interface EtudiantClient {
    @GetMapping(value="/etudiants", produces = "application/json")
    Map<String, Object> getAllEtudiants();
}