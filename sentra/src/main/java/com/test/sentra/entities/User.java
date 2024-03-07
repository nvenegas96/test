package com.test.sentra.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = -2989644983507113348L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "{message.error}")
	@Column(name = "name")
	private String name;
	//@ExistByEmail
	@NotBlank(message = "{message.email}")
	@Column(unique = true, name = "email")
	@Email
	private String email;
	@NotBlank(message = "{message.error}")
	@Column(name = "password")
	private String password;
	@Column(name = "phones")
	@ElementCollection
	private List<Phone> phones;
	@Column(name = "CREATED")
	private String created;
	@Column(name = "MODIFIED")
	private String modified;
	@Column(name = "LAST_LOGIN")
	private String lastLogin;
	@Column(name = "TOKEN")
	private String token;
	@Transient
	private boolean isActive;
	@Transient
	private boolean isAdmin;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<Role> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
