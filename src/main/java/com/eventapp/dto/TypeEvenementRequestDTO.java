package com.eventapp.dto;

import java.util.List;

public class TypeEvenementRequestDTO {
    private String titre;
    private List<Long> serviceIds;
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public List<Long> getServiceIds() {
		return serviceIds;
	}
	public void setServiceIds(List<Long> serviceIds) {
		this.serviceIds = serviceIds;
	}

    // Getters et Setters
}