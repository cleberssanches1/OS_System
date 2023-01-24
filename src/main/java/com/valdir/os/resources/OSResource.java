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

import com.valdir.os.domain.OS;
import com.valdir.os.dto.OSDTO;
import com.valdir.os.services.OSService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/os")
public class OSResource {

	@Autowired
	private OSService osService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<OSDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(new OSDTO(osService.findById(id)));
	}

	@GetMapping
	public ResponseEntity<List<OSDTO>> findAll() {
		List<OSDTO> list = osService.findAll().stream().map(obj -> new OSDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<OSDTO> create(@Valid @RequestBody OSDTO os) {
		OS osEntity = osService.create(os);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(osEntity.getId()).toUri();
		return ResponseEntity.created(uri).body(new OSDTO(osEntity));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		osService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<OSDTO> update(@PathVariable Integer id, @Valid @RequestBody OSDTO os) {
		OS osEntity = osService.update(id, os);
		return ResponseEntity.ok().body(new OSDTO(osEntity));
	}

}
