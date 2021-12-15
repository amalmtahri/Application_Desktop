package com.mutuelle.application.dao.Interface;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.mutuelle.application.models.Client;

import javafx.collections.ObservableList;

public interface ClientDAOInterface {
	
	public void addClient(Client client) throws SQLException;
	public ObservableList<Client> buildData();
	public ObservableList<String> getNameCompany();
	public ObservableList<Client> filtreWithCompany(String nomEntreprise);
	ObservableList<Client> filtre(String value);
	List<Map<String, Integer>> statistique();

}
