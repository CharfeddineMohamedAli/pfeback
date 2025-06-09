package com.eventapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventapp.model.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByEmail(String email);
    
    List<Utilisateur> findByRoleAndApprouveFalse(String role);
    List<Utilisateur> findByRole(String role);
    Optional<Utilisateur> findByNom(String nom);

}

