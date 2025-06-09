package com.eventapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

	@Entity
	@Table(name = "sous_service")
	public class SousServiceEntity {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	
	    private String nom;
		@Column(nullable = true)
	    private String description;
	    private double prix;
	    
	    public String getVille() {
			return ville;
		}
	
		public void setVille(String ville) {
			this.ville = ville;
		}
		@Column(nullable = false)
	    private String ville;
	
	    @ManyToOne
	    @JoinColumn(name = "service_id")
	    @JsonBackReference
	    private ServiceEntity service;

    public SousServiceEntity() {}

    public SousServiceEntity(String nom, double prix, ServiceEntity service) {
        this.nom = nom;
        this.prix = prix;
        this.service = service;
    }
    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur prestataire;
    
    public Utilisateur getPrestataire() {
		return prestataire;
	}

	public void setPrestataire(Utilisateur prestataire) {
		this.prestataire = prestataire;
	}

	public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public ServiceEntity getService() { return service; }
    public void setService(ServiceEntity service) { this.service = service; }
}
