package com.mutuelle.application.Interfaces;

import com.mutuelle.application.models.Client;

public interface ClientInterface {
    public Client addClient(String firstName, String lastName, String email, String phone, String addresse, String identite, String numeroBadge, String nomEntreprise, String dateDebut);
}
