package com.valdir.os.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.valdir.os.domain.Tecnico;
import com.valdir.os.dto.TecnicoDTO;
import com.valdir.os.services.TecnicoService;

import jakarta.validation.Valid;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

	@Autowired
	private TecnicoService tecnicoService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		TecnicoDTO dto = new TecnicoDTO(tecnicoService.findById(id));
		return ResponseEntity.ok().body(dto);
	}
 	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll() {

		List<TecnicoDTO> list = tecnicoService.findAll().stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
		
//		List<TecnicoDTO> listDto = new ArrayList<TecnicoDTO>();
//
//		for (Tecnico tec : tecnicoService.findAll()) {
//			listDto.add(new TecnicoDTO(tec));
//		}
//		return ResponseEntity.ok().body(listDto);
		
		return ResponseEntity.ok().body(list); 
	}
	
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO tecnicoDto) {
		Tecnico tecnico = tecnicoService.create(tecnicoDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(tecnico.getId()).toUri();

		return ResponseEntity.created(uri).body( new TecnicoDTO(tecnico));
	}
 
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		tecnicoService.delete(id);
		return ResponseEntity.noContent().build();
	}
 
	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody Tecnico tecnico) {		 
		return ResponseEntity.ok().body(new TecnicoDTO(tecnicoService.update(id, tecnico)));
	}

}
