package com.github.saulobezerra.contabilize.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.saulobezerra.contabilize.entities.TipoDespesa;
import com.github.saulobezerra.contabilize.services.TipoDespesaService;

@RestController
@RequestMapping(value = "/tiposDespesa")
public class TipoDespesaResource {
	
	@Autowired
	private TipoDespesaService service;
	
	@GetMapping
	public ResponseEntity<List<TipoDespesa>> findAll(){
		List<TipoDespesa> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TipoDespesa> findById(@PathVariable Long id) {
		TipoDespesa obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
