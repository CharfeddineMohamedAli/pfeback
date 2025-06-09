package com.eventapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventapp.model.Evenement;
import com.eventapp.model.ServiceEntity;
import com.eventapp.model.SousServiceEntity;
import com.eventapp.model.TypeEvenement;
import com.eventapp.repository.EvenementRepository;
import com.eventapp.repository.SousServiceRepository;
import com.eventapp.repository.TypeEvenementRepository;

@Service
public class EvenementService {

    @Autowired
    private EvenementRepository evenementRepository;
    
    
    @Autowired
    private TypeEvenementRepository typeEvenementRepository;

    @Autowired
    private SousServiceRepository sousServiceRepository;

    public List<Evenement> getAllEvenements() {
        return evenementRepository.findAll();
    }

    public Optional<Evenement> getEvenementById(Long id) {
        return evenementRepository.findById(id);
    }

    public Evenement saveEvenement(Evenement evenement) {
        return evenementRepository.save(evenement);
    }

    public void deleteEvenement(Long id) {
        evenementRepository.deleteById(id);
    }


    public List<SousServiceEntity> trouverSousServicesDisponibles(Long serviceId, LocalDate dateDebut, LocalDate dateFin, String ville, double budgetMax) {
        // Utiliser uniquement le service sélectionné
        List<Long> serviceIds = List.of(serviceId);

        List<SousServiceEntity> sousServicesDisponibles = sousServiceRepository.findSousServicesDisponibles(
            ville, serviceIds, dateDebut
        );

        // Filtrage par budget
        return sousServicesDisponibles.stream()
                .filter(ss -> ss.getPrix() <= budgetMax)
                .toList();
    }


    public Evenement creerEvenement(Evenement evenement) {
        return evenementRepository.save(evenement);
    }
}