package com.mutuelle.application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mutuelle.application.models.Client;
import com.mutuelle.databaseConnection.DatabaseConnection;

import javafx.collections.ObservableList;

public class StatistiqueDAO {
	
	 private static final String STATISTIQUE_QUERY = "SELECT created_at, COUNT(*) as 'count crated_at' FROM client GROUP BY created_at";

	 public void statistique() {
		 
		 	DatabaseConnection connectNow = new DatabaseConnection();
		 	Connection connectDB = connectNow.getConnection();
	        try {
	            PreparedStatement stat = connectDB.prepareStatement(STATISTIQUE_QUERY);
	            ResultSet rs = stat.executeQuery();
	            
	             while (rs.next()) {
	            	 System.out.println(rs.getString("created_at"));
	            	 System.out.println(rs.getInt("count crated_at"));
	            }
	        } catch (SQLException ex) {
	            JOptionPane.showMessageDialog(null, ex);
	        }
	}
}
