package com.eventapp.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
@CrossOrigin("*") // à adapter selon ta config CORS

@Entity
@Table(name = "service")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private double prix;
    @Lob
    @Column(name = "image", nullable = false,columnDefinition = "LONGBLOB")
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur prestataire;
    
    
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<SousServiceEntity> sousServices = new ArrayList<>();

    
    // Getter et Setter
    public List<SousServiceEntity> getSousServices() {
        return sousServices;
    }

    public void setSousServices(List<SousServiceEntity> sousServices) {
        this.sousServices = sousServices;
    }

    public ServiceEntity() {}

    public ServiceEntity(String nom, String description, double prix, Utilisateur prestataire, byte[] image) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.prestataire = prestataire;
        this.image = image;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public Utilisateur getPrestataire() { return prestataire; }
    public void setPrestataire(Utilisateur prestataire) { this.prestataire = prestataire; }

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
    
    
}