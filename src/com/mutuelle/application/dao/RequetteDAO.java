package com.mutuelle.application.dao;

public final class RequetteDAO {
	
	 public static final String INSERT_QUERY = "INSERT INTO client (firstName, lastName, email, phone, addresse, identite, numeroBadge, nomEntreprise, dateDebut, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
	 public static final String SELECT_QUERY = "SELECT * FROM client WHERE nomEntreprise = ?";
	 public static final String FILTRE_QUERY = "SELECT * FROM client WHERE firstName = ? OR lastName = ? OR email = ? OR identite = ?";
	 public static final String GETDATA_QUERY = "SELECT * FROM client";
	 public static final String STATISTIQUE_QUERY = "SELECT created_at, COUNT(*) as 'count crated_at' FROM client GROUP BY created_at";
	 public static final String LOGIN_QUERY = "SELECT * FROM officer WHERE email = ?";
	 
}
