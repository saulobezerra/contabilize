package com.github.saulobezerra.contabilize.resources;

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

import com.github.saulobezerra.contabilize.entities.Despesa;
import com.github.saulobezerra.contabilize.entities.dtos.DespesaDTO;
import com.github.saulobezerra.contabilize.services.DespesaService;

@RestController
@RequestMapping(value = "/despesas")
public class DespesaResource {
	
	@Autowired
	private DespesaService service;
	
	@GetMapping
	public ResponseEntity<List<DespesaDTO>> findAll(){
		List<Despesa> list = service.findAll();
		List<DespesaDTO> listDto = list.stream().map(despesa -> new DespesaDTO(despesa)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@CrossOrigin
	@GetMapping(value = "/usuario/{idUsuario}")
	public ResponseEntity<List<DespesaDTO>> findByUser(@PathVariable Long idUsuario) {
		List<Despesa> list = service.findByUser(idUsuario);
		List<DespesaDTO> listDto = list.stream().map(despesa -> new DespesaDTO(despesa)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@CrossOrigin
	@GetMapping(value = "/{id}")
	public ResponseEntity<DespesaDTO> findById(@PathVariable Long id) {
		Despesa obj = service.findById(id);
		DespesaDTO despesaDto = new DespesaDTO(obj);
		return ResponseEntity.ok().body(despesaDto);
	}
	
	@CrossOrigin
	@PostMapping
	public ResponseEntity<DespesaDTO> insert(@RequestBody Despesa obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(obj.getId()).toUri();
		DespesaDTO despesaDto = new DespesaDTO(obj);
		return ResponseEntity.created(uri).body(despesaDto);
	}
	
	@CrossOrigin
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@CrossOrigin
	@PutMapping(value = "/{id}")
	public ResponseEntity<DespesaDTO> update(@PathVariable Long id, @RequestBody DespesaDTO objDto) {
		Despesa despesa = service.update(id, objDto);
		DespesaDTO despesaDto = new DespesaDTO(despesa);
		return ResponseEntity.ok().body(despesaDto);
	}
}
