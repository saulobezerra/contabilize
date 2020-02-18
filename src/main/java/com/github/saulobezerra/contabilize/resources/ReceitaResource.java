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

import com.github.saulobezerra.contabilize.entities.Receita;
import com.github.saulobezerra.contabilize.entities.dtos.ReceitaDTO;
import com.github.saulobezerra.contabilize.services.ReceitaService;

@RestController
@RequestMapping(value = "/receitas")
public class ReceitaResource {
	
	@Autowired
	private ReceitaService service;
	
	@GetMapping
	public ResponseEntity<List<ReceitaDTO>> findAll(){
		List<Receita> list = service.findAll();
		List<ReceitaDTO> listDto = list.stream().map(receita -> new ReceitaDTO(receita)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@CrossOrigin
	@GetMapping(value = "/all/usuario/{idUsuario}")
	public ResponseEntity<List<ReceitaDTO>> findByUser(@PathVariable Long idUsuario) {
		List<Receita> list = service.findByUser(idUsuario);
		List<ReceitaDTO> listDto = list.stream().map(receita -> new ReceitaDTO(receita)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@CrossOrigin
	@GetMapping(value = "/usuario/{idUsuario}")
	public ResponseEntity<List<ReceitaDTO>> findByUserAndCurrentMonth(@PathVariable Long idUsuario) {
		List<Receita> list = service.findByUserAndCurrentMonth(idUsuario);
		List<ReceitaDTO> listDto = list.stream().map(receita -> new ReceitaDTO(receita)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@CrossOrigin
	@GetMapping(value = "/{id}")
	public ResponseEntity<ReceitaDTO> findById(@PathVariable Long id) {
		Receita obj = service.findById(id);
		ReceitaDTO receitaDto = new ReceitaDTO(obj);
		return ResponseEntity.ok().body(receitaDto);
	}
	
	@CrossOrigin
	@PostMapping
	public ResponseEntity<ReceitaDTO> insert(@RequestBody Receita obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(obj.getId()).toUri();
		ReceitaDTO receitaDto = new ReceitaDTO(obj);
		return ResponseEntity.created(uri).body(receitaDto);
	}
	
	@CrossOrigin
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@CrossOrigin
	@PutMapping(value = "/{id}")
	public ResponseEntity<ReceitaDTO> update(@PathVariable Long id, @RequestBody Receita obj) {
		obj = service.update(id, obj);
		ReceitaDTO receitaDto = new ReceitaDTO(obj);
		return ResponseEntity.ok().body(receitaDto);
	}
	
	@CrossOrigin
	@GetMapping(value = "{idUsuario}/{mes}/{ano}")
	public ResponseEntity<List<ReceitaDTO>> findByMesAno(@PathVariable Long idUsuario, @PathVariable int mes, @PathVariable int ano){
		List<Receita> list = service.findByMesAno(idUsuario, mes, ano);
		List<ReceitaDTO> listDto = list.stream().map(receita -> new ReceitaDTO(receita)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
}
