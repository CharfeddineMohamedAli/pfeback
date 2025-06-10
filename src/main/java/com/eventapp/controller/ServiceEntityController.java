package com.eventapp.controller;

import com.eventapp.dto.ServiceResponseDTO;
import com.eventapp.model.ServiceEntity;
import com.eventapp.model.Utilisateur;
import com.eventapp.repository.UtilisateurRepository;
import com.eventapp.service.ServiceEntityService;
import com.eventapp.service.UtilisateurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/services")
public class ServiceEntityController {

    @Autowired
    private ServiceEntityService serviceEntityService;
    
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private UtilisateurRepository utilisateurrepo;

    @GetMapping
    public List<ServiceResponseDTO> getAllServices() {
        List<ServiceEntity> services = serviceEntityService.getAllServices();

        return services.stream().map(service -> {
            ServiceResponseDTO dto = new ServiceResponseDTO();
            dto.setId(service.getId());
            dto.setNom(service.getNom());
            dto.setDescription(service.getDescription());
            dto.setPrix(service.getPrix());
            dto.setNomPrestataire(service.getPrestataire().getNom());
            dto.setImage(service.getImage());// 💡 important

            List<ServiceResponseDTO.SousServiceDTO> sousDtos = service.getSousServices().stream().map(ss -> {
                ServiceResponseDTO.SousServiceDTO ssdto = new ServiceResponseDTO.SousServiceDTO();
                ssdto.setNom(ss.getNom());
                ssdto.setPrix(ss.getPrix());
                ssdto.setId(ss.getId());
                ssdto.setIds(ss.getService().getId());

                return ssdto;
            }).collect(Collectors.toList());

            dto.setSousServices(sousDtos);
            return dto;
        }).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceEntity> getServiceById(@PathVariable Long id) {
        Optional<ServiceEntity> service = serviceEntityService.getServiceById(id);
        return service.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ServiceEntity> createService(
            @RequestParam("nom") String nom,
            @RequestParam("description") String description,
            @RequestParam("prestataireId") Long prestataireId,
            @RequestParam("image") MultipartFile imageFile) throws IOException {

        // Vérification si un service avec ce nom existe déjà
        if (serviceEntityService.existsByNom(nom)) {
            throw new RuntimeException("Un service avec ce nom existe déjà.");
        }

        Utilisateur prestataire = utilisateurrepo.findById(prestataireId)
                .orElseThrow(() -> new RuntimeException("Prestataire non trouvé"));

        // Création du service
        ServiceEntity service = new ServiceEntity();
        service.setNom(nom);
        service.setDescription(description);
        service.setImage(imageFile.getBytes());
        service.setPrestataire(prestataire);

        ServiceEntity savedService = serviceEntityService.saveService(service);
        return ResponseEntity.ok(savedService);
    }



    
    @GetMapping("/by-user-nom")
    public ResponseEntity<?> getServicesByUserNom(@RequestParam String nom) {
        Optional<Utilisateur> utilisateurOpt = utilisateurService.findByNom(nom);
        if (utilisateurOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur not found");
        }
        Utilisateur utilisateur = utilisateurOpt.get();

        List<ServiceEntity> services = serviceEntityService.findByPrestataire(utilisateur);

        return ResponseEntity.ok(services);
    }
}