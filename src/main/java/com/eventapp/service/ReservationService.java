package com.eventapp.service;

import com.eventapp.dto.ReservationGroupDTO;
import com.eventapp.model.*;
import com.eventapp.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	public Reservation reserver(ServiceEntity service, SousServiceEntity sousService, Utilisateur client,
			LocalDate startDate) {
		// Vérifier si la période est déjà réservée
		List<Reservation> conflits = reservationRepository.findByServiceAndStatutAndStartDate(service,
				StatutReservation.APPROUVEE, startDate);

		if (!conflits.isEmpty()) {
			throw new IllegalStateException("Le service est déjà réservé pour cette période.");
		}

		Reservation reservation = new Reservation(startDate, service, sousService, client);
		return reservationRepository.save(reservation);
	}

	public Reservation approuver(Long reservationId) {
		Reservation res = reservationRepository.findById(reservationId)
				.orElseThrow(() -> new RuntimeException("Réservation introuvable"));

		res.setStatut(StatutReservation.APPROUVEE);
		return reservationRepository.save(res);
	}

	public Reservation refuser(Long reservationId) {
		Reservation res = reservationRepository.findById(reservationId)
				.orElseThrow(() -> new RuntimeException("Réservation introuvable"));

		res.setStatut(StatutReservation.REFUSEE);
		return reservationRepository.save(res);
	}
	public void updateReservationStatuses(List<Long> sousServiceIds, LocalDate date, String statut) {
	    for (Long sousServiceId : sousServiceIds) {
	        Optional<Reservation> optional = reservationRepository.findBySousServiceIdAndStartDate(sousServiceId, date);
	        optional.ifPresent(reservation -> {
	            reservation.setStatut(StatutReservation.valueOf(statut)); // enum
	            reservationRepository.save(reservation);
	        });
	    }
	}
	public List<Reservation> getAll() {
		return reservationRepository.findAll();
	}

	public List<Reservation> getByClientId(Long clientId) {
		return reservationRepository.findByClientId(clientId);
	}

	public List<Reservation> getReservationsForPrestataire(String prestataireNom) {
		return reservationRepository.findReservationsByPrestataireNom(prestataireNom);
	}

	public List<ReservationGroupDTO> getGroupedReservations(String prestataireNom) {
		List<Reservation> reservations = reservationRepository.findReservationsByPrestataireNom(prestataireNom);
		System.out.println(reservations);
		return reservations.stream().filter(r -> r.getSousService() != null)
				.collect(Collectors.groupingBy(r -> new AbstractMap.SimpleEntry<>(r.getClient(),
						new AbstractMap.SimpleEntry<>(r.getStartDate(), r.getStatut()))))
				.entrySet().stream().map(entry -> {
					Utilisateur client = entry.getKey().getKey();
					LocalDate date = entry.getKey().getValue().getKey();
					StatutReservation statut = entry.getKey().getValue().getValue();
					List<SousServiceEntity> sousServices = entry.getValue().stream().map(Reservation::getSousService)
							.collect(Collectors.toList());

					double total = sousServices.stream().mapToDouble(SousServiceEntity::getPrix).sum();

					ReservationGroupDTO dto = new ReservationGroupDTO();
					dto.setClient(client);
					dto.setDate(date);
					dto.setStatut(statut);
					dto.setSousServices(sousServices);
					dto.setTotalPrix(total);
					return dto;
				}).collect(Collectors.toList());
	}

}
