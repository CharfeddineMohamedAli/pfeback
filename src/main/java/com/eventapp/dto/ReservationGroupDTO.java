package com.eventapp.dto;
import java.time.LocalDate;
import java.util.List;

import com.eventapp.model.SousServiceEntity;
import com.eventapp.model.StatutReservation;
import com.eventapp.model.Utilisateur;

public class ReservationGroupDTO {
    private Utilisateur client;
    private LocalDate date;
    private StatutReservation statut;
    private List<SousServiceEntity> sousServices;
    private double totalPrix;
    
    
	public ReservationGroupDTO() {
		super();
	}
	public ReservationGroupDTO(Utilisateur client, LocalDate date, StatutReservation statut,
			List<SousServiceEntity> sousServices, double totalPrix) {
		super();
		this.client = client;
		this.date = date;
		this.statut = statut;
		this.sousServices = sousServices;
		this.totalPrix = totalPrix;
	}
	public Utilisateur getClient() {
		return client;
	}
	public void setClient(Utilisateur client) {
		this.client = client;
	}
	public LocalDate getDate() {
		return date;
	}
		public void setDate(LocalDate date) {
			this.date = date;
	}
	public StatutReservation getStatut() {
		return statut;
	}
	public void setStatut(StatutReservation statut) {
		this.statut = statut;
	}
	public List<SousServiceEntity> getSousServices() {
		return sousServices;
	}
	public void setSousServices(List<SousServiceEntity> sousServices) {
		this.sousServices = sousServices;
	}
	public double getTotalPrix() {
		return totalPrix;
	}
	public void setTotalPrix(double totalPrix) {
		this.totalPrix = totalPrix;
	}

    // constructor, getters, setters
}
