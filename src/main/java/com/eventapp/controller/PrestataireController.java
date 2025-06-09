package com.eventapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventapp.model.Prestataire;
import com.eventapp.service.PrestataireService;

@RestController
@RequestMapping("/api/prestataires")
public class PrestataireController {

    @Autowired
    private PrestataireService prestataireService;

    @GetMapping
    public List<Prestataire> getAllPrestataires() {
        return prestataireService.getAllPrestataires();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestataire> getPrestataireById(@PathVariable Long id) {
        Optional<Prestataire> prestataire = prestataireService.getPrestataireById(id);
        return prestataire.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Prestataire createPrestataire(@RequestBody Prestataire prestataire) {
        return prestataireService.savePrestataire(prestataire);
    }

    @DeleteMapping("/{id}")
    public void deletePrestataire(@PathVariable Long id) {
        prestataireService.deletePrestataire(id);
    }
}
