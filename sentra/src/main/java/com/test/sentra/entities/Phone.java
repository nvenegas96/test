package com.test.sentra.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "phones")
public class Phone implements Serializable {

	private static final long serialVersionUID = -2989644983507113348L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "phoneID")
	private Long id;
	@NotBlank(message = "{message.error}")
	@Column(name = "number")
	private String number;
	@NotBlank(message = "{message.error}")
	@Column(name = "citycode")
	private String citycode;
	@NotBlank(message = "{message.error}")
	@Column(name = "contrycode")
	private String contrycode;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getcontrycode() {
		return contrycode;
	}

	public void setcontrycode(String contrycode) {
		this.contrycode = contrycode;
	}

}
