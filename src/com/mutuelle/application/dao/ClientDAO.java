package com.mutuelle.application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.mutuelle.application.dao.Interface.ClientDAOInterface;
import com.mutuelle.application.models.Client;
import com.mutuelle.databaseConnection.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class ClientDAO implements ClientDAOInterface{
	
	public ObservableList<Client> data = FXCollections.observableArrayList();
	public ObservableList<String> nameCompany = FXCollections.observableArrayList();
	public ObservableList<Client> filtreNameCompany = FXCollections.observableArrayList();
	public ObservableList<Client> filtre = FXCollections.observableArrayList();
	
	public List<String> search = new ArrayList<>();
	public List<Map<String, Integer>> statistiqueList; 
//  WHERE NOT EXISTS (SELECT * FROM client  WHERE identite = ? AND numeroBadge = ?)
	 private static final String INSERT_QUERY = "INSERT INTO client (firstName, lastName, email, phone, addresse, identite, numeroBadge, nomEntreprise, dateDebut, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
	 private static final String SELECT_QUERY = "SELECT * FROM client WHERE nomEntreprise = ?";
	 private static final String FILTRE_QUERY = "SELECT * FROM client WHERE firstName = ? OR lastName = ? OR email = ? OR identite = ?";
	 private static final String GETDATA_QUERY = "SELECT * FROM client";
	 private static final String STATISTIQUE_QUERY = "SELECT created_at, COUNT(*) as 'count crated_at' FROM client GROUP BY created_at";
	 
	 @Override
	 public void addClient(Client client) throws SQLException {
		 
		  DatabaseConnection connectNow = new DatabaseConnection();
	      Connection connectDB = connectNow.getConnection();
	      LocalDate date = LocalDate.now();
	      if(search(client.getCin().toString(), client.getNumeroBadge().toString()).isEmpty()) {
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
	  	            preparedStatement.setString(10, date.toString());
	  	            System.out.println(preparedStatement);
	  	            preparedStatement.executeUpdate();
		  	  		Alert alert = new Alert(AlertType.INFORMATION);
		  	  		alert.setTitle("Success");
		  	  		alert.setHeaderText("Results:");
		  	  		alert.setContentText("Client Sera ajouter!");
		  	  		alert.showAndWait();
	  	        }  
	  	        catch(SQLException e) {
	  	            e.printStackTrace();
	  	        }
	      }
	      else {
	    	  System.out.println("ces données deja enregistrer");
	      }
	      
	        
	    }
	 public List<String> search(String identite, String numeroBadge) {
		 String requette = "SELECT * FROM client  WHERE identite = ? OR numeroBadge = ?";
		 DatabaseConnection connectNow = new DatabaseConnection();
		 	Connection connectDB = connectNow.getConnection();
	      
	        try {
	            PreparedStatement stat = connectDB.prepareStatement(requette);
	            stat.setString(1, identite);
	            stat.setString(2, numeroBadge);
                ResultSet resultSet = stat.executeQuery();
                while (resultSet.next()) {
                	search.add(resultSet.getString("identite"));
                	search.add(resultSet.getString("numeroBadge"));
            }
	           
	        } catch (SQLException ex) {
	            JOptionPane.showMessageDialog(null, ex);
	        }
			return search;			
	 }
	 @Override
	 public ObservableList<Client> buildData() {
		 
		 	DatabaseConnection connectNow = new DatabaseConnection();
		 	Connection connectDB = connectNow.getConnection();
	      
	        try {
	            PreparedStatement stat = connectDB.prepareStatement(GETDATA_QUERY);
	            ResultSet rs = stat.executeQuery();
	             while (rs.next()) {
	                    data.add(new Client(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
	            }
	        } catch (SQLException ex) {
	            JOptionPane.showMessageDialog(null, ex);
	        }
			return data;
	}
	 
	 @Override
	public ObservableList<String> getNameCompany(){
		

	 	DatabaseConnection connectNow = new DatabaseConnection();
	 	Connection connectDB = connectNow.getConnection();
        try {
            String requetteSQL  = "SELECT distinct nomEntreprise FROM client";
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
	@Override
	public ObservableList<Client> filtreWithCompany(String nomEntreprise){
	 	DatabaseConnection connectNow = new DatabaseConnection();
	 	Connection connectDB = connectNow.getConnection();
		if(!nomEntreprise.equalsIgnoreCase("All")) {
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
		 }
		else {
			try {
	            PreparedStatement preparedStatement = connectDB.prepareStatement(GETDATA_QUERY); {
	                ResultSet resultSet = preparedStatement.executeQuery();
	                while (resultSet.next()) {
	                	filtreNameCompany.add(new Client(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10)));
	                }
	            }
	        } catch (SQLException ex) {
	            JOptionPane.showMessageDialog(null, ex);
	        }
		}
			return filtreNameCompany;
	}
	
	@Override
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
	
	@Override
	 public List<Map<String, Integer>> statistique() {
		 statistiqueList=new ArrayList<Map<String,Integer>>();
		 	DatabaseConnection connectNow = new DatabaseConnection();
		 	Connection connectDB = connectNow.getConnection();
	        try {
	            PreparedStatement stat = connectDB.prepareStatement(STATISTIQUE_QUERY);
	            ResultSet rs = stat.executeQuery();  
	            while (rs.next()) {
                    Map<String,Integer> temp = new HashMap<String,Integer>();                
                    temp.put(rs.getString("created_at"), rs.getInt("count crated_at"));
                    statistiqueList.add(temp);
                    System.out.println(statistiqueList);
                }
	        } catch (SQLException ex) {
	            JOptionPane.showMessageDialog(null, ex);
	        }
	        
			return statistiqueList;
	}
}

