package com.eventapp.repository;

import com.eventapp.model.Reservation;
import com.eventapp.model.ServiceEntity;
import com.eventapp.model.StatutReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // Trouver les réservations approuvées pour un service donné et qui se chevauchent avec une période
	    List<Reservation> findByServiceAndStatutAndStartDate(
	            ServiceEntity service,
	            StatutReservation statut,
	            LocalDate startDate);

    // Optionnel : trouver toutes les réservations d’un client
    List<Reservation> findByClientId(Long clientId);
    
    @Query("""
    	    SELECT r 
    	    FROM Reservation r
    	    LEFT JOIN r.service s
    	    LEFT JOIN r.sousService ss
    	    LEFT JOIN ss.prestataire ssPrestataire
    	    LEFT JOIN s.prestataire sPrestataire
    	    WHERE LOWER(sPrestataire.nom) = LOWER(:prestataireNom)
    	    OR LOWER(ssPrestataire.nom) = LOWER(:prestataireNom)
    	""")
    	List<Reservation> findReservationsByPrestataireNom(@Param("prestataireNom") String prestataireNom);

        Optional<Reservation> findBySousServiceIdAndStartDate(Long sousServiceId, LocalDate date);

}
