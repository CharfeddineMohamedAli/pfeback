package com.eventapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventapp.model.Utilisateur;
import com.eventapp.repository.UtilisateurRepository;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateur> getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id);
    }

    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    public Utilisateur getByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }
    public List<Utilisateur> findByRoleAndApprouveFalse(String role) {
    	return utilisateurRepository.findByRoleAndApprouveFalse(role);
    }
    
    public List<Utilisateur> findByRole(String role) {
    	return utilisateurRepository.findByRole(role);
    }

    public Optional<Utilisateur> findByNom(String nom){
    	return utilisateurRepository.findByNom(nom);
    }
    
}
