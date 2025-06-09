package com.eventapp.controller;

import com.eventapp.model.Utilisateur;
import com.eventapp.model.Client;
import com.eventapp.model.Prestataire;
import com.eventapp.service.UtilisateurService;
import com.eventapp.service.ClientService;
import com.eventapp.service.PrestataireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

	@Autowired
	private UtilisateurService utilisateurService;

	@GetMapping
	public List<Utilisateur> getAllUtilisateurs() {
		return utilisateurService.getAllUtilisateurs();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Long id) {
		Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateurById(id);
		return utilisateur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur) {
		utilisateur.setApprouve(utilisateur.getRole().equals("client")); // approuvé automatiquement si client
		return utilisateurService.saveUtilisateur(utilisateur);
	}

	@DeleteMapping("/{id}")
	public void deleteUtilisateur(@PathVariable Long id) {
		utilisateurService.deleteUtilisateur(id);
	}
	
	@GetMapping("/prestataires/non-approuves")
	public List<Utilisateur> getPrestatairesNonApprouves() {
	    return utilisateurService.findByRoleAndApprouveFalse("prestataire");
	}
	
	@PutMapping("/{id}/approuver")
	public ResponseEntity<?> approuverUtilisateur(@PathVariable Long id) {
	    Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateurById(id);
	    if (utilisateur.isPresent()) {
	        Utilisateur u = utilisateur.get();
	        u.setApprouve(true);
	        utilisateurService.saveUtilisateur(u);
	        return ResponseEntity.ok().build(); // ✅ Pas de contenu, donc pas d'erreur de parsing
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@GetMapping("/prestataires")
	public List<Utilisateur> getAllPrestataires() {
	    return utilisateurService.findByRole("prestataire");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur updated) {
	    Optional<Utilisateur> optionalUser = utilisateurService.getUtilisateurById(id);
	    if (optionalUser.isPresent()) {
	        Utilisateur user = optionalUser.get();
	        user.setNom(updated.getNom());
	        user.setPrenom(updated.getPrenom());
	        user.setEmail(updated.getEmail());
	        user.setAdresse(updated.getAdresse());
	        user.setNumTel(updated.getNumTel());
	        // No need to update role or mot de passe unless explicitly allowed
	        utilisateurService.saveUtilisateur(user);
	        return ResponseEntity.ok(user);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	@GetMapping("/nom/{nom}")
	public ResponseEntity<Utilisateur> getUtilisateurByNom(@PathVariable String nom) {
	    Optional<Utilisateur> utilisateur = utilisateurService.findByNom(nom);
	    return utilisateur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
}