package com.eventapp.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "paiement")
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double montant;
    private LocalDate date;
    private String methode;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Paiement() {}

    public Paiement(double montant, LocalDate date, String methode, Reservation reservation, Client client) {
        this.montant = montant;
        this.date = date;
        this.methode = methode;
        this.reservation = reservation;
        this.client = client;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getMethode() { return methode; }
    public void setMethode(String methode) { this.methode = methode; }

    public Reservation getReservation() { return reservation; }
    public void setReservation(Reservation reservation) { this.reservation = reservation; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
}
