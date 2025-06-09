package com.eventapp;

import com.eventapp.model.Utilisateur;
import com.eventapp.repository.UtilisateurRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public ApplicationRunner initializer(UtilisateurRepository repository) {
    	
        return args -> {
        	Utilisateur utilisateur = repository.findByEmail("admin");
if(utilisateur == null) {
                Utilisateur admin = new Utilisateur();	
                admin.setEmail("admin");
                admin.setMotPasse("admin");
                admin.setNom("Admin");
                admin.setPrenom("Admin");
                admin.setRole("admin");
                // You can set other fields as needed
                repository.save(admin);
}
        };
    }
}
