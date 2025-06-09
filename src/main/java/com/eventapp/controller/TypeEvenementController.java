package com.eventapp.controller;

import com.eventapp.dto.TypeEvenementRequestDTO;
import com.eventapp.model.ServiceEntity;
import com.eventapp.model.TypeEvenement;
import com.eventapp.service.TypeEvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types-evenement")
@CrossOrigin(origins = "*") // à adapter selon ton frontend
public class TypeEvenementController {

    @Autowired
    private TypeEvenementService typeEvenementService;

    @PostMapping
    public TypeEvenement createTypeEvenement(@RequestBody TypeEvenementRequestDTO dto) {
        return typeEvenementService.createTypeEvenement(dto);
    }

    @GetMapping
    public List<TypeEvenement> getAll() {
        return typeEvenementService.getAllTypes();
    }

    @GetMapping("/{id}")
    public TypeEvenement getById(@PathVariable Long id) {
        return typeEvenementService.getTypeById(id);
    }
    
    @GetMapping("/{id}/services")
    public List<ServiceEntity> getServicesByTypeEvenement(@PathVariable Long id) {
        return typeEvenementService.getServicesByTypeEvenement(id);
    }
    @PutMapping("/{id}")
    public TypeEvenement updateTypeEvenement(@PathVariable Long id, @RequestBody TypeEvenementRequestDTO dto) {
        return typeEvenementService.updateTypeEvenement(id, dto);
    }
    
    @DeleteMapping("/{id}")
    public void deleteTypeEvenement(@PathVariable Long id) {
        typeEvenementService.deleteTypeEvenement(id);
    }
}
