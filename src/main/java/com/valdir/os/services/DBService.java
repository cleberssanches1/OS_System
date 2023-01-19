package com.valdir.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valdir.os.domain.Cliente;
import com.valdir.os.domain.OS;
import com.valdir.os.domain.Tecnico;
import com.valdir.os.enuns.Prioridade;
import com.valdir.os.enuns.Status;
import com.valdir.os.repositories.ClienteRepository;
import com.valdir.os.repositories.OSRepository;
import com.valdir.os.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private OSRepository oSRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	
	public void instanciaDB() {
		Tecnico t1 = new Tecnico(null, "Cleber Sanches", "(77)977777777", "296.111.270-22");
		Tecnico t2 = new Tecnico(null, "Alice Sanches", "(88)988888888", "732.580.520-93");

		Cliente c1 = new Cliente(null, "Nome 1", "(99)999999999", "512.180.580-21");
		Cliente c2 = new Cliente(null, "Nome 2", "(88)988888888", "258.239.960-47");

		OS os1 = new OS(null, null, Prioridade.ALTA, Status.ABERTO, "Observações 1", t1, c1);
		OS os2 = new OS(null, null, Prioridade.ALTA, Status.ABERTO, "Observações 2", t2, c2);

		t1.getList().add(os1);
		c1.getList().add(os1);

		t2.getList().add(os2);
		c2.getList().add(os2);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1, c2));
		oSRepository.saveAll(Arrays.asList(os1, os2));
	}

}
