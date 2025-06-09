package com.eventapp.model;
import jakarta.persistence.*;

@Entity
@Table(name = "prestataire")
public class Prestataire extends Utilisateur {

    public Prestataire() {
        super();
    }

    public Prestataire(String email, String nom, String prenom, String motPasse, String numTel, String adresse) {
        super(email, nom, prenom, motPasse, numTel, adresse);
    }

    // Business method stub
    public void gererComptePrestataire() {
        // TODO: implement prestataire account management logic
    }
}