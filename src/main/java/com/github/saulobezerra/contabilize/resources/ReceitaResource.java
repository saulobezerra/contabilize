package com.github.saulobezerra.contabilize.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.saulobezerra.contabilize.entities.Receita;
import com.github.saulobezerra.contabilize.services.ReceitaService;

@RestController
@RequestMapping(value = "/receitas")
public class ReceitaResource {
	
	@Autowired
	private ReceitaService service;
	
	@GetMapping
	public ResponseEntity<List<Receita>> findAll(){
		List<Receita> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Receita> findById(@PathVariable Long id) {
		Receita obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
