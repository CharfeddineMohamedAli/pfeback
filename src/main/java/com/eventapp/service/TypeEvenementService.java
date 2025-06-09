package com.eventapp.service;

import com.eventapp.dto.TypeEvenementRequestDTO;
import com.eventapp.model.ServiceEntity;
import com.eventapp.model.TypeEvenement;
import com.eventapp.repository.ServiceRepository;
import com.eventapp.repository.TypeEvenementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeEvenementService {

    @Autowired
    private TypeEvenementRepository typeEvenementRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    public TypeEvenement createTypeEvenement(TypeEvenementRequestDTO dto) {
        TypeEvenement type = new TypeEvenement();
        type.setTitre(dto.getTitre());

        List<ServiceEntity> services = serviceRepository.findAllById(dto.getServiceIds());
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
