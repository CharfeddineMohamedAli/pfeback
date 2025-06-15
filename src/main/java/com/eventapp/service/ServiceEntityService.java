package com.eventapp.service;

import com.eventapp.model.ServiceEntity;
import com.eventapp.model.SousServiceEntity;
import com.eventapp.model.Utilisateur;
import com.eventapp.repository.ReservationRepository;
import com.eventapp.repository.ServiceRepository;
import com.eventapp.repository.SousServiceRepository;
import com.eventapp.repository.TypeEvenementRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceEntityService {

    @Autowired
    private ServiceRepository serviceRepository;
    
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TypeEvenementRepository typeEvenementRepository;
    @Autowired
    private SousServiceRepository sousserviceRepository;

    public boolean existsByNom(String nom) {
        return serviceRepository.existsByNom(nom);
    }

    public List<ServiceEntity> getAllServices() {
        return serviceRepository.findAll();
    }

    public Optional<ServiceEntity> getServiceById(Long id) {
        return serviceRepository.findById(id);
    }

    public ServiceEntity saveService(ServiceEntity serviceEntity) {
        return serviceRepository.save(serviceEntity);
        
    }
    
    public  List<ServiceEntity> findByPrestataire(Utilisateur prestataire){
    	 return serviceRepository.findByPrestataire(prestataire);
    }

    @Transactional
    public void deleteById(Long serviceId) {
        ServiceEntity service = serviceRepository.findById(serviceId)
            .orElseThrow(() -> new RuntimeException("Service not found"));

        // Step 1: Delete reservations for all sous-services
        for (SousServiceEntity ss : service.getSousServices()) {
            reservationRepository.deleteBySousServiceId(ss.getId());
        }
        typeEvenementRepository.removeServiceLinks(serviceId);
        // Step 2: Now delete the service (which will cascade to sous-services)
        serviceRepository.delete(service);
    }



}