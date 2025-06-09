package com.eventapp.dto;

public class LoginResponse{
    private String token;
    private String nom;
    private boolean approuve = false;

    public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public boolean isApprouve() {
        return approuve;
    }

    public void setApprouve(boolean approuve) {
        this.approuve = approuve;}
    public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	private String role;

    public LoginResponse(String token, String nom, String role, boolean isApprouve) {
    	this.nom = nom;
        this.token = token;
        this.role = role;
        this.approuve = isApprouve ;
    }

    // Getters & Setters
}
