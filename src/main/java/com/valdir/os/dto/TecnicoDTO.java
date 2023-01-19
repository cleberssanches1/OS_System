package com.valdir.os.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.br.CPF;

import com.valdir.os.domain.Tecnico;

import jakarta.validation.constraints.NotEmpty;

public class TecnicoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6049650991079110071L;
	private Integer id;
	
	@NotEmpty(message = "Campo nome requerido.")
	private String nome;
	@NotEmpty(message = "Campo telefone requerido.")
	private String telefone;
	@CPF
	@NotEmpty(message = "Campo CPF requerido.")
	private String cpf;

	public TecnicoDTO(Tecnico tecnico) {
		super();
		this.id = tecnico.getId();
		this.nome = tecnico.getNome();
		this.telefone = tecnico.getTelefone();
		this.cpf = tecnico.getCpf();
	}

	public TecnicoDTO() {
		super();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TecnicoDTO other = (TecnicoDTO) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
