package com.eventapp.model;

import jakarta.persistence.*;

@Entity
public class ReservationSousService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SousServiceEntity sousService;

    public ReservationSousService(Long id, SousServiceEntity sousService, Evenement evenement,
			StatutReservation statut) {
		super();
		this.id = id;
		this.sousService = sousService;
		this.evenement = evenement;
		this.statut = statut;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SousServiceEntity getSousService() {
		return sousService;
	}

	public void setSousService(SousServiceEntity sousService) {
		this.sousService = sousService;
	}

	public Evenement getEvenement() {
		return evenement;
	}

	public void setEvenement(Evenement evenement) {
		this.evenement = evenement;
	}

	public StatutReservation getStatut() {
		return statut;
	}

	public void setStatut(StatutReservation statut) {
		this.statut = statut;
	}

	@ManyToOne
    private Evenement evenement;

    @Enumerated(EnumType.STRING)
    private StatutReservation statut; // EN_ATTENTE, APPROUVEE, REFUSEE

    // Constructeurs, Getters, Setters
}

