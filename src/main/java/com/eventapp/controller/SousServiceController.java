package com.eventapp.controller;

import com.eventapp.model.ServiceEntity;
import com.eventapp.model.SousServiceEntity;
import com.eventapp.model.Utilisateur;
import com.eventapp.service.SousServiceService;
import com.eventapp.service.UtilisateurService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sous-services")
public class SousServiceController {
	 @Autowired
	    private UtilisateurService utilisateurService;
	 
    @Autowired
    private SousServiceService sousServiceService;

    @PostMapping
    public ResponseEntity<?> addSousService(@RequestBody SousServiceEntity sousService) {
        if (sousService.getPrestataire() == null || sousService.getPrestataire().getNom() == null) {
            return ResponseEntity.badRequest().body("Le nom du prestataire est requis");
        }

        Optional<Utilisateur> prestataireOpt = utilisateurService.findByNom(sousService.getPrestataire().getNom());

        if (prestataireOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prestataire non trouvé");
        }

        // Associer un prestataire existant géré par Hibernate
        sousService.setPrestataire(prestataireOpt.get());
        sousService.setId(null);
        SousServiceEntity savedSousService = sousServiceService.createSousService(sousService);
        return ResponseEntity.ok(savedSousService);
    }
    @PutMapping("/{id}")
    public SousServiceEntity updateSousService(@PathVariable Long id, @RequestBody SousServiceEntity updatedSousService) {
        return sousServiceService.updateSousService(id, updatedSousService);
    }
    
    @DeleteMapping("/{id}")
    public void deleteSousService(@PathVariable Long id) {
        sousServiceService.deleteSousService(id);
    }
    
    @GetMapping("/by-user-nom")
    public ResponseEntity<?> getServicesByUserNom(@RequestParam String nom) {
        Optional<Utilisateur> utilisateurOpt = utilisateurService.findByNom(nom);
        if (utilisateurOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur not found");
        }
        Utilisateur utilisateur = utilisateurOpt.get();

        List<SousServiceEntity> services = sousServiceService.findByPrestataire(utilisateur);

        return ResponseEntity.ok(services);
    }
}

