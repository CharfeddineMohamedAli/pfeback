package com.eventapp.service;

import com.eventapp.model.ServiceEntity;
import com.eventapp.model.Utilisateur;
import com.eventapp.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceEntityService {

    @Autowired
    private ServiceRepository serviceRepository;

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

    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
}