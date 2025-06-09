package com.eventapp.repository;

import com.eventapp.model.TypeEvenement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeEvenementRepository extends JpaRepository<TypeEvenement, Long> {
}
