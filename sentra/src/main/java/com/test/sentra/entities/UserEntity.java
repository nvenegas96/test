package com.test.sentra.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -2989644983507113348L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator")
	@ColumnDefault("random_uuid()")
	@Column(name = "userID")
	private UUID userID;
	
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
	@Column(name = "phoneEntities")
	@ElementCollection
	private List<PhoneEntity> phones;
	@Column(name = "created")
	private Date created;
	@Column(name = "modified")
	private Date modified;
	@Column(name = "lastlogin")
	private Date lastlogin;
	@Column(name = "token")
	private String token;
	@Transient
	private boolean active;
	@Transient
	private boolean isAdmin;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<RoleEntity> roleEntities;

}
