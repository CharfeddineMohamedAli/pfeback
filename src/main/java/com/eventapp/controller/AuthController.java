package com.eventapp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventapp.dto.LoginRequest;
import com.eventapp.dto.LoginResponse;
import com.eventapp.model.Utilisateur;
import com.eventapp.repository.UtilisateurRepository;

@CrossOrigin(origins = "http://localhost:4200") 
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Utilisateur user = utilisateurRepository.findByEmail(request.getEmail());

        if (user!=null) {
        	// When saving a user

        	// When checking login
        	if (request.getPassword().equals(user.getMotPasse()))
        	{
            // For demo: plain text password check (not secure)

                // TODO: replace this with real JWT
                String fakeToken = "mock-jwt-token";
                return ResponseEntity.ok(new LoginResponse(fakeToken,user.getNom(), user.getRole(),user.isApprouve()));
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}
