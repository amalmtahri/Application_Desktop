package com.mutuelle.application.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mutuelle.application.dao.ClientDAO;
import com.mutuelle.application.dao.OfficerDAO;

public class OfficerDAOTesting {

	@Test
	public void test() {
		OfficerDAO officerDAO = new OfficerDAO();
		assertTrue(officerDAO.validateLogin("gmcowis3@alibaba.com", "VCpfMt"));

	}

}
