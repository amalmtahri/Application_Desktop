package com.mutuelle.application.Impl;

import java.util.List;

import com.mutuelle.application.Interfaces.OfficerInterface;
import com.mutuelle.application.models.Officer;


public class OfficerImpl implements OfficerInterface{
	//int indexEmail;
	@Override
	public int verifEmail(String email, List<Officer> officers) {
		// TODO Auto-generated method stub
		int indexEmail= -1;
		for (Officer officer : officers) {
			if(officer.getEmail().equals(email)) {
				indexEmail = officers.indexOf(officer);
				break;
			}
		}
		return indexEmail;
	}

	@Override
	public boolean verifPassword(String password, int index, List<Officer> officers) {
		// TODO Auto-generated method stub
		if(officers.get(index).getPassword().equals(password)){
			return true;
		}
		return false;
	}

}
