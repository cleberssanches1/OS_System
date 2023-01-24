package com.valdir.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valdir.os.domain.OS;
import com.valdir.os.dto.OSDTO;
import com.valdir.os.enuns.Prioridade;
import com.valdir.os.enuns.Status;
import com.valdir.os.exception.ObjectNotFoundException;
import com.valdir.os.repositories.ClienteRepository;
import com.valdir.os.repositories.OSRepository;
import com.valdir.os.repositories.TecnicoRepository;

import jakarta.validation.Valid;

@Service
public class OSService {

	@Autowired
	private OSRepository oSRepository;
	
	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired 
	private ClienteRepository clienteRepository;

	
	public OS findById(Integer id) {
		Optional<OS> obj = oSRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado para o ID: " + id + ", Tipo " + OS.class.getName()));			
	}

	public List<OS> findAll() {
		List<OS> list = oSRepository.findAll();
		return list;
	}

	public OS create(@Valid OSDTO os) {	 
		return oSRepository.save(toOsEntity(os));
	}

	private OS toOsEntity(OSDTO os) {
		OS osEntity = new OS();		
		osEntity.setObservacoes(os.getObservacoes());		
		osEntity.setCliente(clienteRepository.findById(os.getCliente()).get());
		osEntity.setTecnico(tecnicoRepository.findById(os.getTecnico()).get());
		
		osEntity.setPrioridade(os.getPrioridade());
		osEntity.setStatus(os.getStatus());
		
		return osEntity;
	}

	public void delete(Integer id) {
		OS os = findById(id);
		oSRepository.delete(os);
	}

	public OS update(Integer id, OSDTO osDto) {
		OS obj = findById(id);
		
		obj.setObservacoes(osDto.getObservacoes());
		obj.setPrioridade(osDto.getPrioridade());
		obj.setStatus(osDto.getStatus());
		obj.setTecnico(tecnicoRepository.findById(osDto.getTecnico()).get());
		obj.setCliente(clienteRepository.findById(osDto.getCliente()).get());	 
			
		if(Status.ENCERRADO.equals(osDto.getStatus())) {
			obj.setDataFechamento(LocalDateTime.now());
		}else {
			obj.setDataFechamento(null);
		}
		
		obj = oSRepository.save(obj);

		return obj;
	}

}
