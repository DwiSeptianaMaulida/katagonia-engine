package com.mulyadime.backend.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "acronym")
public class Acronym  implements Serializable {
	
	private static final long serialVersionUID = 910408847785132971L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_acronym")
	private Long id;
	
	private String name;
	
	private String category;
	
	private String indonesia;
	
	private String english;
	
}
