package com.valdir.os.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Tecnico extends Pessoa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1592974223202058370L;

	public Tecnico() {		super();

	}

	public Tecnico(Integer id, String nome, String telefone, String cpf) {
		super(id, nome, telefone, cpf);
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "tecnico")
	private List<OS> list = new ArrayList<OS>();

	public List<OS> getList() {
		return list;
	}

	public void setList(List<OS> list) {
		this.list = list;
	}

}
