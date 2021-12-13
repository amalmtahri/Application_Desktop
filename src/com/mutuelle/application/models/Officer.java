package com.mutuelle.application.models;
public class Officer {
	private Long id;
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	private String address;
	private String cin;
	private String password;
	private String entity;

	public Officer() {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public Officer(String firstname, String lastname, String email, String phone, String address, String cin, String password, String entity) {
		this.id = 1L + (long) (Math.random() * (10L - 1L));
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.cin = cin;
		this.password = password;
		this.entity = entity;
	}
}
