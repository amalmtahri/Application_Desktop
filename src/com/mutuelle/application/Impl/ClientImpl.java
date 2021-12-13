package com.mutuelle.application.Impl;

import com.mutuelle.application.Interfaces.ClientInterface;
import com.mutuelle.application.models.Client;

public class ClientImpl implements ClientInterface{

	@Override
	public Client addClient(String firstName, String lastName, String email, String phone, String addresse,
			String identite, String numeroBadge, String nomEntreprise, String dateDebut) {
		// TODO Auto-generated method stub
		 	Client c = new Client();
	        c.setFirstname(firstName);
	        c.setLastname(lastName);
	        c.setEmail(email);
	        c.setPhone(phone);
	        c.setAddress(addresse);
	        c.setCin(identite);
	        c.setNumeroBadge(numeroBadge);
	        c.setNomEntreprise(nomEntreprise);
	        c.setDateDebut(dateDebut);
	        return c;
	}

}
