package com.eventapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventapp.model.Evenement;

@Repository
public interface EvenementRepository extends JpaRepository<Evenement, Long> {
}