package com.mutuelle.application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mutuelle.application.models.Client;
import com.mutuelle.databaseConnection.DatabaseConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ClientDAO {
	
	public ObservableList<Client> data = FXCollections.observableArrayList();
	public ObservableList<String> nameCompany = FXCollections.observableArrayList();
	public ObservableList<Client> filtreNameCompany = FXCollections.observableArrayList();
	public ObservableList<Client> filtre = FXCollections.observableArrayList();

	 private static final String INSERT_QUERY = "INSERT INTO client (firstName, lastName, email, phone, addresse, identite, numeroBadge, nomEntreprise, dateDebut) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	 private static final String SELECT_QUERY = "SELECT * FROM client WHERE nomEntreprise = ?";
	 private static final String FILTRE_QUERY = "SELECT * FROM client WHERE firstName = ? OR lastName = ? OR email = ? OR identite = ?";
	 
	 public void addClient(Client client) throws SQLException {
		 
		  DatabaseConnection connectNow = new DatabaseConnection();
	      Connection connectDB = connectNow.getConnection();
	      
	        try (
	            PreparedStatement preparedStatement = connectDB.prepareStatement(INSERT_QUERY)) {
	            preparedStatement.setString(1, client.getFirstname());
	            preparedStatement.setString(2, client.getLastname());
	            preparedStatement.setString(3, client.getEmail());
	            preparedStatement.setString(4, client.getPhone());
	            preparedStatement.setString(5, client.getAddress());
	            preparedStatement.setString(6, client.getCin());
	            preparedStatement.setString(7, client.getNumeroBadge());
	            preparedStatement.setString(8, client.getNomEntreprise());
	            preparedStatement.setString(9, client.getDateDebut());
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            preparedStatement.executeUpdate();
	        }  
	        catch(SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 public ObservableList<Client> buildData() {
		 
		 	DatabaseConnection connectNow = new DatabaseConnection();
		 	Connection connectDB = connectNow.getConnection();
	      
	        try {
	            String requetteSQL  = "SELECT * FROM client";
	            PreparedStatement stat = connectDB.prepareStatement(requetteSQL);
	            ResultSet rs = stat.executeQuery();
	             while (rs.next()) {
	                    data.add(new Client(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
	            }
	        } catch (SQLException ex) {
	            JOptionPane.showMessageDialog(null, ex);
	        }
			return data;
	}
	 
	public ObservableList<String> getNameCompany(){
		

	 	DatabaseConnection connectNow = new DatabaseConnection();
	 	Connection connectDB = connectNow.getConnection();
        try {
            String requetteSQL  = "SELECT nomEntreprise FROM client";
            PreparedStatement stat = connectDB.prepareStatement(requetteSQL);
            ResultSet rs = stat.executeQuery();
             while (rs.next()) {
            	nameCompany.add(rs.getString("nomEntreprise"));
            }
	        } catch (SQLException ex) {
	            JOptionPane.showMessageDialog(null, ex);
	        }
			return nameCompany;
		
	}
	
	public ObservableList<Client> filtreWithCompany(String nomEntreprise){
	
	 	DatabaseConnection connectNow = new DatabaseConnection();
	 	Connection connectDB = connectNow.getConnection();
	        System.out.println(connectDB);
	        try {
	            PreparedStatement preparedStatement = connectDB.prepareStatement(SELECT_QUERY); {
	                preparedStatement.setString(1, nomEntreprise);
	                ResultSet resultSet = preparedStatement.executeQuery();
	                while (resultSet.next()) {
	                	filtreNameCompany.add(new Client(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10)));
	                }
	            }
	        } catch (SQLException ex) {
	            JOptionPane.showMessageDialog(null, ex);
	        }
			return filtreNameCompany;
	}
	
	public ObservableList<Client> filtre(String value){
		DatabaseConnection connectNow = new DatabaseConnection();
	 	Connection connectDB = connectNow.getConnection();
	        System.out.println(connectDB);
	        try {
	            PreparedStatement preparedStatement = connectDB.prepareStatement(FILTRE_QUERY); {
	                preparedStatement.setString(1, value);
	                preparedStatement.setString(2, value);
	                preparedStatement.setString(3, value);
	                preparedStatement.setString(4, value);
	                ResultSet resultSet = preparedStatement.executeQuery();
	                while (resultSet.next()) {
	                	filtre.add(new Client(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10)));
	                }
	            }
	        } catch (SQLException ex) {
	            JOptionPane.showMessageDialog(null, ex);
	        }
			return filtre;
		
	}
}
