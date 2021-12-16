package com.mutuelle.application.testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mutuelle.application.dao.ClientDAO;

public class ClientDAOTesting {

	@Test
	public void test() {
		ClientDAO clientDAO = new ClientDAO();
		List<String> test = new ArrayList<>();
		//select * where nameCompany = ?
		assertNotEquals(test, clientDAO.filtreWithCompany("YouCode"));
		//select where firstname = ? or lastname = ?  identite = ? or email = ?
		assertNotEquals(test, clientDAO.filtre("amal"));
		//select where identite = ? OR numeroBadge = ?
		assertNotEquals(test, clientDAO.search("QP7999927","K12"));
		
	}
	@Before
	public void testBuildData() {
		ClientDAO clientDAO = new ClientDAO();
		assertNotNull(clientDAO.buildData());
		assertNotNull(clientDAO.getNameCompany());
	}
	


}
