package com.mutuelle.application.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mutuelle.application.Impl.ClientImpl;
import com.mutuelle.application.dao.ClientDAO;

public class ClientImplTest {

	@Test
	public void testAddClient() {
		ClientImpl client = new ClientImpl();
		ClientDAO clientDAO = new ClientDAO();
		assertNotNull(client.addClient("amal", "mtahri", "mtahriamal0@gmail.com", "0659697087", "addresse", "HH76598", "M12", "YouCode", "11/11/2021"));
	}

}
