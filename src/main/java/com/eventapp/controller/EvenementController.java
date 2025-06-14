package com.eventapp.controller;

import com.eventapp.model.Evenement;
import com.eventapp.model.SousServiceEntity;
import com.eventapp.repository.SousServiceRepository;
import com.eventapp.service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/evenements")
public class EvenementController {

    @Autowired
    private EvenementService evenementService;
    @Autowired
    private SousServiceRepository sousServiceRepository;
    @GetMapping
    public List<Evenement> getAllEvenements() {
        return evenementService.getAllEvenements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evenement> getEvenementById(@PathVariable Long id) {
        Optional<Evenement> evenement = evenementService.getEvenementById(id);
        return evenement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Evenement createEvenement(@RequestBody Evenement evenement) {
        return evenementService.saveEvenement(evenement);
    }

    @DeleteMapping("/{id}")
    public void deleteEvenement(@PathVariable Long id) {
        evenementService.deleteEvenement(id);
    }
    
    @GetMapping("/sous-services-disponibles")
    public ResponseEntity<List<SousServiceEntity>> getSousServicesDisponibles(
            @RequestParam Long typeEvenementId,
            @RequestParam List<Long> serviceIds,  // Accepting a list of service IDs
            @RequestParam List<String> sousServiceNames,  // Accepting a list of sous-service IDs
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
            @RequestParam(required = false) String ville,
            @RequestParam double budgetMax) {
    	if (ville.isEmpty()|| ville==null) {
    		List<SousServiceEntity> sousServicesDisponibles = sousServiceRepository.findSousServicesDisponiblesnoville(
                    serviceIds, dateDebut);
    		 sousServicesDisponibles = sousServicesDisponibles.stream()
    	                .filter(ss -> sousServiceNames.contains(ss.getNom())) // Ensure the sous-service matches
    	                .filter(ss -> ss.getPrix() <= budgetMax)  // Filter by budget
    	                .collect(Collectors.toList());

    	        return ResponseEntity.ok(sousServicesDisponibles);
    	}
    	else if (serviceIds.size() == 1 && serviceIds.get(0).equals(5L)) {
    		List<SousServiceEntity> sousServicesDisponibles = sousServiceRepository.findSousServicesDisponibleslocaleone(
	                ville, serviceIds, dateDebut);
    		 sousServicesDisponibles = sousServicesDisponibles.stream()
    	                .filter(ss -> ss.getPrix() <= budgetMax)  // Filter by budget
    	                .collect(Collectors.toList());

    	        return ResponseEntity.ok(sousServicesDisponibles);

    		
    	}
    	else if (sousServiceNames.isEmpty()) {
    		List<SousServiceEntity> sousServicesDisponibles = sousServiceRepository.findSousServicesville(
                    ville, dateDebut);
    		 sousServicesDisponibles = sousServicesDisponibles.stream()
    	                .filter(ss -> ss.getPrix() <= budgetMax)  // Filter by budget
    	                .collect(Collectors.toList());

    	        return ResponseEntity.ok(sousServicesDisponibles);

    	}

    	else if (serviceIds.contains(5L) ) {
    	    List<SousServiceEntity> sousServicesDisponibles = sousServiceRepository.findSousServicesDisponibleslocale(
    	            ville, serviceIds, dateDebut);

    	        sousServicesDisponibles = sousServicesDisponibles.stream()
    	                .filter(ss -> ss.getPrix() <= budgetMax)  // Filter by budget
    	            .filter(ss -> {
    	                // If serviceId is 5, skip name and budget filters
    	                if (ss.getService().getId() == 5L) {
    	                    return true;
    	                }
    	                // Otherwise, apply filters
    	                return sousServiceNames.contains(ss.getNom()) && ss.getPrix() <= budgetMax;
    	            })
    	            .collect(Collectors.toList());

    	        return ResponseEntity.ok(sousServicesDisponibles);

    	}
    	
    	
    	else {
    		 List<SousServiceEntity> sousServicesDisponibles = sousServiceRepository.findSousServicesDisponibles(
    	                ville, serviceIds, dateDebut);
    		 sousServicesDisponibles = sousServicesDisponibles.stream()
    	                .filter(ss -> sousServiceNames.contains(ss.getNom())) // Ensure the sous-service matches
    	                .filter(ss -> ss.getPrix() <= budgetMax)  // Filter by budget
    	                .collect(Collectors.toList());

    	        return ResponseEntity.ok(sousServicesDisponibles);
    	}
       
    }
}

