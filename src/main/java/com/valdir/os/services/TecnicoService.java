package com.valdir.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valdir.os.domain.Tecnico;
import com.valdir.os.dto.TecnicoDTO;
import com.valdir.os.exception.DataIntegrationViolationException;
import com.valdir.os.exception.ObjectNotFoundException;
import com.valdir.os.repositories.TecnicoRepository;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado para o ID: " + id + ", Tipo " + Tecnico.class.getName()));
	}

	public List<Tecnico> findAll() {
		List<Tecnico> list = tecnicoRepository.findAll();
		return list;
	}

	public Tecnico create(TecnicoDTO tecnico) {	
		if(this.findByCPF(tecnico)!= null) {
			throw new DataIntegrationViolationException("CPF já cadastrado na base de dados: " + tecnico.getCpf());
		}
		return tecnicoRepository.save(new Tecnico(null, tecnico.getNome(), tecnico.getTelefone(), tecnico.getCpf()));
	}

	public void delete(Integer id) {
		Tecnico tecnico = findById(id);
		
		if(!tecnico.getList().isEmpty()) {
			throw new DataIntegrationViolationException("Técnico com ordens de serviço, e não pode ser excluído: " + tecnico.getId());
		}
		
		tecnicoRepository.delete(tecnico);
	}

	public Tecnico update(Integer id, Tecnico tecnico) {
		Tecnico obj = findById(id);

		if( (findByCPF(new TecnicoDTO(tecnico)) != null) && (findByCPF(new TecnicoDTO(tecnico)).getId() != id)) {			
			throw new DataIntegrationViolationException("CPF já cadastrado na base de dados: " + tecnico.getCpf());
		}
				
		obj.setCpf(tecnico.getCpf());
		obj.setList(tecnico.getList());
		obj.setNome(tecnico.getNome());
		obj.setTelefone(tecnico.getTelefone());

		obj = tecnicoRepository.save(obj);
		
		return obj;
	}
	
	public Tecnico findByCPF(TecnicoDTO tecnicoDTO) {
		
		Tecnico obj =  this.tecnicoRepository.findByCPF(tecnicoDTO.getCpf());
		if(obj != null) {
			return obj;			
		}
		return null;
	}

}
