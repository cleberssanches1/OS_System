package com.valdir.os.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente extends Pessoa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8771704309331319151L;

	public Cliente() {
		super();
	}

	public Cliente(Integer id, String nome, String telefone, String cpf) {
		super(id, nome, telefone, cpf);
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<OS> list = new ArrayList<OS>();

	public List<OS> getList() {
		return list;
	}

	public void setList(List<OS> list) {
		this.list = list;
	}
	
}
