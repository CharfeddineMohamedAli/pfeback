package com.eventapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventapp.model.Prestataire;
import com.eventapp.repository.PrestataireRepository;

@Service
public class PrestataireService {

    @Autowired
    private PrestataireRepository prestataireRepository;

    public List<Prestataire> getAllPrestataires() {
        return prestataireRepository.findAll();
    }

    public Optional<Prestataire> getPrestataireById(Long id) {
        return prestataireRepository.findById(id);
    }

    public Prestataire savePrestataire(Prestataire prestataire) {
        return prestataireRepository.save(prestataire);
    }

    public void deletePrestataire(Long id) {
        prestataireRepository.deleteById(id);
    }
}