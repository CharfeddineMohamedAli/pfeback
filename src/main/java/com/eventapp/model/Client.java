package com.eventapp.model;
import jakarta.persistence.*;

@Entity
@Table(name = "client")
public class Client extends Utilisateur {

    public Client() {
        super();
    }

    public Client(String email, String nom, String prenom, String motPasse, String numTel, String adresse) {
        super(email, nom, prenom, motPasse, numTel, adresse);
    }

    // Business method stub
    public void gererProfil() {
        // TODO: implement profile management logic
    }
}