package com.mutuelle.application.models;


public class Client{
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String address;
    private String cin;
    private String numeroBadge;
    private String nomEntreprise;
    private String dateDebut;
    
  

    public Client() {
        // TODO Auto-generated constructor stub
    }

    public Client(String firstname, String lastname, String email, String phone, String address, String cin,String numeroBadge,String nomEntreprise,String dateDebut) {
        this.id = 1L + (long) (Math.random() * (10L - 1L));
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.cin = cin;
        this.numeroBadge = numeroBadge;
        this.nomEntreprise = nomEntreprise;
        this.dateDebut = dateDebut;

    }


    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getDateDebut() {
        return dateDebut;
    }
	public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }


	public String getNumeroBadge() {
        return numeroBadge;
    }

    public void setNumeroBadge(String numeroBadge) {
        this.numeroBadge = numeroBadge;
    }
}
