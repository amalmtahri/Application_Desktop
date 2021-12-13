package com.mutuelle.application.Interfaces;

import java.util.List;

import com.mutuelle.application.models.Officer;


public interface OfficerInterface {
	public int verifEmail(String email, List<Officer> officers);
	public boolean verifPassword(String password, int index, List<Officer> officers );
	
}

