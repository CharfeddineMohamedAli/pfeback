package com.eventapp.service;

import com.eventapp.model.ServiceEntity;
import com.eventapp.model.SousServiceEntity;
import com.eventapp.model.Utilisateur;
import com.eventapp.repository.ReservationRepository;
import com.eventapp.repository.SousServiceRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SousServiceService {

    @Autowired
    private SousServiceRepository sousServiceRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public SousServiceEntity getSousServiceById(Long id) {
        return sousServiceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Sous-service introuvable avec id: " + id));
    }
    public SousServiceEntity createSousService(SousServiceEntity sousService) {
        return sousServiceRepository.save(sousService);
    }
    
    public SousServiceEntity updateSousService(Long id, SousServiceEntity updatedSousService) {
        SousServiceEntity existing = sousServiceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Sous-service not found"));

        existing.setNom(updatedSousService.getNom());
        existing.setPrix(updatedSousService.getPrix());
        existing.setService(updatedSousService.getService()); // if updatable

        return sousServiceRepository.save(existing);
    }
    public List<SousServiceEntity> getByNom(String nom) {
        return sousServiceRepository.findByNom(nom);
    }
    public void deleteSousService(Long id) {
        if (!sousServiceRepository.existsById(id)) {
            throw new RuntimeException("Sous-service not found");
        }
        reservationRepository.deleteBySousServiceId(id);
        sousServiceRepository.deleteById(id);
    }
    
    public  List<SousServiceEntity> findByPrestataire(Utilisateur prestataire){
   	 return sousServiceRepository.findByPrestataire(prestataire);
   }


}
