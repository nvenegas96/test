package com.test.sentra.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name="roles")
@Data
@AllArgsConstructor
public class RoleEntity implements Serializable{
	
	private static final long serialVersionUID = -2989644983507113348L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true, name="roleID")
	private String name;
	
}
