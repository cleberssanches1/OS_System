package com.valdir.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valdir.os.domain.Cliente;
import com.valdir.os.dto.ClienteDTO;
import com.valdir.os.exception.DataIntegrationViolationException;
import com.valdir.os.exception.ObjectNotFoundException;
import com.valdir.os.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado para o ID: " + id + ", Tipo " + Cliente.class.getName()));		 
	}

	public List<Cliente> findAll() {
		List<Cliente> list = clienteRepository.findAll();
		return list;
	}

	public Cliente create(ClienteDTO cliente) {
		
		if(this.findByCPF(cliente)!= null) {
			throw new DataIntegrationViolationException("CPF já cadastrado na base de dados: " + cliente.getCpf());
		}		 
		return clienteRepository.save(new Cliente(null, cliente.getNome(), cliente.getTelefone(), cliente.getCpf()));
	}

	public void delete(Integer id) {
		Cliente cliente = findById(id);
		
		if(!cliente.getList().isEmpty()) {
			throw new DataIntegrationViolationException("Cliente com ordens de serviço, e não pode ser excluído: " + cliente.getId());
		}
		clienteRepository.delete(cliente);
	}

	public Cliente update(Integer id, Cliente cliente) {
		Cliente obj = findById(id);

		if( (findByCPF(new ClienteDTO(cliente)) != null) && (findByCPF(new ClienteDTO(cliente)).getId() != id)) {			
			throw new DataIntegrationViolationException("CPF já cadastrado na base de dados: " + cliente.getCpf());
		}
		
		obj.setCpf(cliente.getCpf());
		obj.setList(cliente.getList());
		obj.setNome(cliente.getNome());
		obj.setTelefone(cliente.getTelefone());

		obj = clienteRepository.save(obj);
		
		return obj;
	}
	
    public Cliente findByCPF(ClienteDTO cliente) {
		
    	Cliente obj =  this.clienteRepository.findByCPF(cliente.getCpf());
		if(obj != null) {
			return obj;			
		}
		return null;
	}

	
}
