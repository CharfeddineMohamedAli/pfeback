package com.eventapp.repository;

import com.eventapp.model.ServiceEntity;
import com.eventapp.model.SousServiceEntity;
import com.eventapp.model.Utilisateur;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SousServiceRepository extends JpaRepository<SousServiceEntity, Long> {
	List<SousServiceEntity> findByNom(String nom);

	@Query("SELECT ss FROM SousServiceEntity ss " +
		       "WHERE (:ville IS NULL OR ss.ville = :ville) " +
		       "AND ss.service.id IN :serviceIds " +
		       "AND ss.id NOT IN (" +
		       "   SELECT r.sousService.id FROM Reservation r " +
		       "   WHERE r.startDate = :dateDebut " +
		       "   AND (r.statut = 'EN_ATTENTE' OR r.statut = 'APPROUVEE')" +
		       ")")
		List<SousServiceEntity> findSousServicesDisponibles(@Param("ville") String ville,
		                                                    @Param("serviceIds") List<Long> serviceIds,
		                                                    @Param("dateDebut") LocalDate dateDebut);

	@Query("SELECT ss FROM SousServiceEntity ss " +
		       "WHERE ss.service.id IN :serviceIds " +
		       "AND ss.id NOT IN (" +
		       "   SELECT r.sousService.id FROM Reservation r " +
		       "   WHERE r.startDate = :dateDebut " +
		       "   AND (r.statut = 'EN_ATTENTE' OR r.statut = 'APPROUVEE')" +
		       ")")
		List<SousServiceEntity> findSousServicesDisponiblesnoville(@Param("serviceIds") List<Long> serviceIds,
		                                                           @Param("dateDebut") LocalDate dateDebut);

	@Query("SELECT ss FROM SousServiceEntity ss " +
		       "WHERE (:ville IS NULL OR ss.ville = :ville) " +
		       "AND ss.id NOT IN (" +
		       "   SELECT r.sousService.id FROM Reservation r " +
		       "   WHERE r.startDate = :dateDebut " +
		       "   AND (r.statut = 'EN_ATTENTE' OR r.statut = 'APPROUVEE')" +
		       ")")
		List<SousServiceEntity> findSousServicesville(@Param("ville") String ville,
		                                              @Param("dateDebut") LocalDate dateDebut);


	@Query("SELECT ss FROM SousServiceEntity ss " +
		       "WHERE ss.service.id IN :serviceIds " +
		       "AND (ss.service.id != 5 OR ss.ville = :ville) " +
		       "AND ss.id NOT IN (" +
		       "   SELECT r.sousService.id FROM Reservation r " +
		       "   WHERE r.startDate = :dateDebut " +
		       "   AND (r.statut = 'EN_ATTENTE' OR r.statut = 'APPROUVEE')" +
		       ")")
		List<SousServiceEntity> findSousServicesDisponibleslocale(@Param("ville") String ville,
		                                                          @Param("serviceIds") List<Long> serviceIds,
		                                                          @Param("dateDebut") LocalDate dateDebut);

	@Query("SELECT ss FROM SousServiceEntity ss " +
		       "WHERE ss.service.id = 5 " +
		       "AND ss.ville = :ville " +
		       "AND ss.id NOT IN (" +
		       "   SELECT r.sousService.id FROM Reservation r " +
		       "   WHERE r.startDate = :dateDebut " +
		       "   AND (r.statut = 'EN_ATTENTE' OR r.statut = 'APPROUVEE')" +
		       ")")
		List<SousServiceEntity> findSousServicesDisponibleslocaleone(@Param("ville") String ville,
		                                                             @Param("serviceIds") List<Long> serviceIds,  // can be ignored
		                                                             @Param("dateDebut") LocalDate dateDebut);

	List<SousServiceEntity> findByPrestataire(Utilisateur pestataire);
	@Modifying
	@Query("DELETE FROM SousServiceEntity s WHERE s.prestataire.id = :prestataireId")
	void deleteByPrestataireId(@Param("prestataireId") Long prestataireId);


}