package com.eventapp.dto;

import java.time.LocalDate;
import java.util.List;
public class ReservationSous {

    private long sousserviceId;       // comma-separated service IDs, e.g., "1,2,3"
    public String clientNom;    // comma-separated sous-service names, e.g., "Nettoyage,Repassage"
    private Long serviceId;
    private LocalDate startDate;
	public long getSousserviceId() {
		return sousserviceId;
	}
	public void setSousserviceId(long sousserviceId) {
		this.sousserviceId = sousserviceId;
	}
	public String getClientNom() {
		return clientNom;
	}
	public void setClientNom(String clientNom) {
		this.clientNom = clientNom;
	}
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

    // Getters and Setters
}
    
