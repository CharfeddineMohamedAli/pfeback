package com.eventapp.service;

import com.eventapp.model.ServiceEntity;
import com.eventapp.model.Utilisateur;
import com.eventapp.repository.ServiceRepository;
import com.eventapp.repository.SousServiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceEntityService {

    @Autowired
    private ServiceRepository serviceRepository;

    
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

  

}