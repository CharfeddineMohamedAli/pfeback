package com.eventapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventapp.model.Utilisateur;
import com.eventapp.repository.ServiceRepository;
import com.eventapp.repository.SousServiceRepository;
import com.eventapp.repository.UtilisateurRepository;

import jakarta.transaction.Transactional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private SousServiceRepository sousServiceRepository;
    
    
    @Autowired
    private ServiceRepository serviceRepository;

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateur> getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id);
    }

    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @Transactional
    public void deleteUtilisateur(Long utilisateurId) {
        // 1. Supprimer les sous-services liés à cet utilisateur
        sousServiceRepository.deleteByPrestataireId(utilisateurId);
        // 2. Supprimer les services liés à cet utilisateur (si nécessaire)
        serviceRepository.deleteByPrestataireId(utilisateurId);
        // 3. Supprimer l'utilisateur
        utilisateurRepository.deleteById(utilisateurId);
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
