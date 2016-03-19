package com.epam.moovies.model;

public class UserRole{

	private String role;

	public UserRole() {
	}

	public UserRole(String userRole) {
		this.role=userRole;
	}


	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}