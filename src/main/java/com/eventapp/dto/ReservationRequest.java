package com.eventapp.dto;

import java.time.LocalDate;
import java.util.List;
public class ReservationRequest {

    private String serviceIds;       // comma-separated service IDs, e.g., "1,2,3"
    public String sousServiceNom;    // comma-separated sous-service names, e.g., "Nettoyage,Repassage"
    private Long clientId;
    private double prix;
    private LocalDate startDate;

    // Getters and Setters
    public String getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(String serviceIds) {
        this.serviceIds = serviceIds;
    }

    public String getSousServiceNom() {
        return sousServiceNom;
    }

    public void setSousServiceNom(String sousServiceNom) {
        this.sousServiceNom = sousServiceNom;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
    
