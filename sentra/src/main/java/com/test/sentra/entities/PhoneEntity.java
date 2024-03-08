package com.test.sentra.entities;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "phones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneEntity implements Serializable {

	private static final long serialVersionUID = -2989644983507113348L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator")
	@ColumnDefault("random_uuid()")
	@Column(name = "phoneID")
	private UUID phoneID;
	@NotBlank(message = "{message.error}")
	@Column(name = "number")
	private String number;
	@NotBlank(message = "{message.error}")
	@Column(name = "citycode")
	private String citycode;
	@NotBlank(message = "{message.error}")
	@Column(name = "contrycode")
	private String contrycode;
	@Column(name = "userID")
	private UUID userID;
}
