package com.eventapp.service;

import com.eventapp.dto.TypeEvenementRequestDTO;
import com.eventapp.model.ServiceEntity;
import com.eventapp.model.TypeEvenement;
import com.eventapp.repository.ServiceRepository;
import com.eventapp.repository.TypeEvenementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeEvenementService {

    @Autowired
    private TypeEvenementRepository typeEvenementRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    public TypeEvenement createTypeEvenement(TypeEvenementRequestDTO dto) {
    	
    	
        String titre = dto.getTitre().trim();
       
        List<Long> incomingServiceIds = dto.getServiceIds();

        // Rechercher tous les types d'événements avec le même titre
        List<TypeEvenement> existingTypes = typeEvenementRepository.findByTitre(titre);
        
        
        if (!existingTypes.isEmpty()) {
        	throw new IllegalArgumentException("Un type d'événement avec ce titre existe déjà.");

        }

        // Charger les services demandés
        List<ServiceEntity> services = serviceRepository.findAllById(incomingServiceIds);

        // Vérifier si une combinaison exacte existe déjà
        for (TypeEvenement existing : existingTypes) {
            List<Long> existingServiceIds = existing.getServices()
                                                    .stream()
                                                    .map(ServiceEntity::getId)
                                                    .sorted()
                                                    .toList();
            List<Long> requestedServiceIds = new ArrayList<>(incomingServiceIds);
            requestedServiceIds.sort(Long::compareTo);

            if (existingServiceIds.equals(requestedServiceIds)) {
                // Déjà existant : ne pas enregistrer
            	throw new IllegalArgumentException("Un type d'événement avec ce titre et ces services existe déjà.");
            }
        }

        // Pas de doublon trouvé : créer
        TypeEvenement type = new TypeEvenement();
        type.setTitre(titre);
        type.setServices(services);

        return typeEvenementRepository.save(type);
    }

    public List<TypeEvenement> getAllTypes() {
        return typeEvenementRepository.findAll();
    }

    public TypeEvenement getTypeById(Long id) {
        return typeEvenementRepository.findById(id).orElse(null);
    }
    
    public List<ServiceEntity> getServicesByTypeEvenement(Long typeEvenementId) {
        TypeEvenement typeEvenement = typeEvenementRepository.findById(typeEvenementId)
            .orElseThrow(() -> new RuntimeException("TypeEvenement not found"));
        return typeEvenement.getServices();
    }
    
    public TypeEvenement updateTypeEvenement(Long id, TypeEvenementRequestDTO dto) {
        TypeEvenement type = typeEvenementRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Type d'événement non trouvé"));

        type.setTitre(dto.getTitre());
        // gérer aussi les services si nécessaires
        return typeEvenementRepository.save(type);
    }
    
    public void deleteTypeEvenement(Long id) {
        typeEvenementRepository.deleteById(id);
    }
}
