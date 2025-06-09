package com.eventapp.dto;

import java.time.LocalDate;
import java.util.List;

public class ReservationStatusUpdateRequest {
    private List<Long> sousServiceIds;
    private LocalDate date;
    private String statut;
	public List<Long> getSousServiceIds() {
		return sousServiceIds;
	}
	public void setSousServiceIds(List<Long> sousServiceIds) {
		this.sousServiceIds = sousServiceIds;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}

    // Getters and Setters
}