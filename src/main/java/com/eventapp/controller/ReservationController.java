package com.eventapp.controller;

import com.eventapp.dto.ReservationGroupDTO;
import com.eventapp.dto.ReservationRequest;
import com.eventapp.dto.ReservationSous;
import com.eventapp.dto.ReservationStatusUpdateRequest;
import com.eventapp.model.Reservation;
import com.eventapp.model.ServiceEntity;
import com.eventapp.model.SousServiceEntity;
import com.eventapp.model.Utilisateur;
import com.eventapp.service.ReservationService;
import com.eventapp.service.ServiceEntityService;
import com.eventapp.service.SousServiceService;
import com.eventapp.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin("*") // à adapter selon ta config CORS
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ServiceEntityService serviceEntityService;
    
    @Autowired
    private SousServiceService sousServiceService;

    @Autowired
    private UtilisateurService utilisateurService;

    // 🔹 1. Créer une réservation
    @PostMapping
    public ResponseEntity<?> creerReservation(@RequestBody ReservationRequest req) {
        try {
            Utilisateur client = utilisateurService.getUtilisateurById(req.getClientId())
                    .orElseThrow(() -> new RuntimeException("Client introuvable"));

            LocalDate startDate = req.getStartDate();

            if (req.getServiceIds() != null && !req.getServiceIds().isEmpty()) {
                String[] serviceIdStrings = req.getServiceIds().split(",");
                for (String idStr : serviceIdStrings) {
                    Long serviceId = Long.parseLong(idStr.trim());
                    ServiceEntity service = serviceEntityService.getServiceById(serviceId)
                            .orElseThrow(() -> new RuntimeException("Service introuvable (id=" + serviceId + ")"));

                    reservationService.reserver(service, null, client, startDate);
                }
            }

            if (req.getSousServiceNom() != null && !req.getSousServiceNom().isEmpty()) {
                String[] sousServiceNoms = req.getSousServiceNom().split(",");
                for (String sousServiceNom : sousServiceNoms) {
                    List<SousServiceEntity> matching = sousServiceService.getByNom(sousServiceNom.trim());

                    if (matching == null || matching.isEmpty()) {
                        throw new RuntimeException("Aucun sous-service trouvé pour: " + sousServiceNom);
                    }

                    SousServiceEntity cheapest = matching.stream()
                            .min(Comparator.comparingDouble(SousServiceEntity::getPrix))
                            .orElseThrow(() -> new RuntimeException("Erreur de tri de sous-services"));

                    reservationService.reserver(null, cheapest, client, startDate);
                }
            }

            return ResponseEntity.ok("");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/sous")
    public ResponseEntity<?> sousReservation(@RequestBody ReservationSous req) {
        try {
            Utilisateur client = utilisateurService.findByNom(req.getClientNom())
                    .orElseThrow(() -> new RuntimeException("Client introuvable"));

            LocalDate startDate = req.getStartDate();
            Optional<ServiceEntity> service = serviceEntityService.getServiceById(req.getServiceId());
            SousServiceEntity sousservice = sousServiceService.getSousServiceById(req.getSousserviceId());


           reservationService.reserver(service.get(), sousservice, client, startDate);
           return ResponseEntity.ok("");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
                
    }

    // 🔹 2. Approuver une réservation
    @PutMapping("/{id}/approuver")
    public Reservation approuver(@PathVariable Long id) {
        return reservationService.approuver(id);
    }

    // 🔹 3. Refuser une réservation
    @PutMapping("/{id}/refuser")
    public Reservation refuser(@PathVariable Long id) {
        return reservationService.refuser(id);
    }

    // 🔹 4. Récupérer les réservations d’un client
    @GetMapping("/client/{clientId}")
    public List<Reservation> getByClient(@PathVariable Long clientId) {
        return reservationService.getByClientId(clientId);
    }
    @PutMapping("/update-status")
    public ResponseEntity<?> updateStatusForSousServices(@RequestBody ReservationStatusUpdateRequest request) {
        reservationService.updateReservationStatuses(request.getSousServiceIds(), request.getDate(), request.getStatut());
        return ResponseEntity.ok().build();
    }
    
    
    // 🔹 5. Récupérer toutes les réservations (admin ou prestataire)
    @GetMapping
    public List<Reservation> getAll() {
        return reservationService.getAll();
    }
    
    @GetMapping("/prestataire")
    public ResponseEntity<List<ReservationGroupDTO>> getGroupedReservations(@RequestParam String prestataireNom) {
        List<ReservationGroupDTO> result = reservationService.getGroupedReservations(prestataireNom);
        return ResponseEntity.ok(result);
    }

}
