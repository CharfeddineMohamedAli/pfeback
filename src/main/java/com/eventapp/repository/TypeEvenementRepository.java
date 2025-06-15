package com.eventapp.repository;

import com.eventapp.model.TypeEvenement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TypeEvenementRepository extends JpaRepository<TypeEvenement, Long> {
	List<TypeEvenement> findByTitre(String titre);
	@Modifying
	@Query(value = "DELETE FROM type_evenement_service WHERE service_id = :serviceId", nativeQuery = true)
	void removeServiceLinks(@Param("serviceId") Long serviceId);
}

