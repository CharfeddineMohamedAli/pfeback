package com.eventapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventapp.model.Prestataire;

@Repository
public interface PrestataireRepository extends JpaRepository<Prestataire, Long> {
}
