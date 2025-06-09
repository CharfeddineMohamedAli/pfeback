package com.eventapp.repository;

import com.eventapp.model.ServiceEntity;
import com.eventapp.model.Utilisateur;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
	List<ServiceEntity> findByPrestataire(Utilisateur pestataire);

}
