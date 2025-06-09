package com.eventapp.model;

import jakarta.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String nom;
    private String prenom;
    private String motPasse;
    private String numTel;
    private String adresse;
    private String role;
    @Column(nullable = false)
    private boolean approuve = false;

    public boolean isApprouve() {
        return approuve;
    }

    public void setApprouve(boolean approuve) {
        this.approuve = approuve;
    }
    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	// Constructors
    public Utilisateur() {}

    public Utilisateur(String email, String nom, String prenom, String motPasse, String numTel, String adresse) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.motPasse = motPasse;
        this.numTel = numTel;
        this.adresse = adresse;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getMotPasse() { return motPasse; }
    public void setMotPasse(String motPasse) { this.motPasse = motPasse; }

    public String getNumTel() { return numTel; }
    public void setNumTel(String numTel) { this.numTel = numTel; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    // Business methods (stubs)
    public void inscrire() {
        // TODO: implement inscription logic
    }

    public void authentification() {
        // TODO: implement authentication logic
    }
}