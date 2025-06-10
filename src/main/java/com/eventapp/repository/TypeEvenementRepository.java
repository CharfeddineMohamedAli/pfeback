package com.eventapp.repository;

import com.eventapp.model.TypeEvenement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeEvenementRepository extends JpaRepository<TypeEvenement, Long> {
	List<TypeEvenement> findByTitre(String titre);

}

