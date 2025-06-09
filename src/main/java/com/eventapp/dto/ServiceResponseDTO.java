package com.eventapp.dto;

import java.util.List;

public class ServiceResponseDTO {
    private Long id;
    private String nom;
    private String description;
    private double prix;
    private String nomPrestataire;
    private List<SousServiceDTO> sousServices;
    private byte[] image;

    public static class SousServiceDTO {
    	private long id;
        private String nom;
        private double prix;
    	private long ids;

        // Getters et Setters
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		public double getPrix() {
			return prix;
		}
		public void setPrix(double prix) {
			this.prix = prix;
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public long getIds() {
			return ids;
		}
		public void setIds(long ids) {
			this.ids = ids;
		}
		
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public String getNomPrestataire() {
		return nomPrestataire;
	}

	public void setNomPrestataire(String nomPrestataire) {
		this.nomPrestataire = nomPrestataire;
	}

	public List<SousServiceDTO> getSousServices() {
		return sousServices;
	}

	public void setSousServices(List<SousServiceDTO> sousServices) {
		this.sousServices = sousServices;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	

    // Getters et Setters
}

