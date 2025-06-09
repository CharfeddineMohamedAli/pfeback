package com.eventapp.model;

import jakarta.persistence.*;
import java.time.LocalDate;

	@Entity
	@Table(name = "reservation")
		public class Reservation {
		
		    @Id
		    @GeneratedValue(strategy = GenerationType.IDENTITY)
		    private Long id;
		
		    private LocalDate startDate;
		
		    @Enumerated(EnumType.STRING)
		    private StatutReservation statut = StatutReservation.EN_ATTENTE;
		
		    @ManyToOne
		    @JoinColumn(name = "service_id", nullable = true)
		    private ServiceEntity service;
		
		    @ManyToOne
		    @JoinColumn(name = "sous_service_id", nullable = true)
		    private SousServiceEntity sousService;
		
		    @ManyToOne
		    @JoinColumn(name = "client_id", nullable = false)
		    private Utilisateur client;

    public SousServiceEntity getSousService() {
		return sousService;
	}

	public void setSousService(SousServiceEntity sousService) {
		this.sousService = sousService;
	}

	public Reservation() {}

    public Reservation(LocalDate startDate, ServiceEntity service,SousServiceEntity sousService, Utilisateur client) {
        this.startDate = startDate;
        this.service = service;
        this.sousService = sousService;
        this.client = client;
        this.statut = StatutReservation.EN_ATTENTE;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }


    public StatutReservation getStatut() { return statut; }
    public void setStatut(StatutReservation statut) { this.statut = statut; }

    public ServiceEntity getService() { return service; }
    public void setService(ServiceEntity service) { this.service = service; }

    public Utilisateur getClient() { return client; }
    public void setClient(Utilisateur client) { this.client = client; }
}
