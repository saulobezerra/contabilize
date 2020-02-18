package com.github.saulobezerra.contabilize.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.saulobezerra.contabilize.entities.dtos.LucroDTO;
import com.github.saulobezerra.contabilize.services.LucroService;

@RestController
@RequestMapping(value = "/lucros")
public class LucroResource {

	@Autowired
	private LucroService service;
	
	@CrossOrigin
	@GetMapping(value = "/atual/{id}")
	public ResponseEntity<LucroDTO> lucroAtual(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.lucroAtual(id));
	}
	
	@CrossOrigin
	@GetMapping(value = "/default/{id}")
	public ResponseEntity<List<LucroDTO>> lucroThreeMonthAgo(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.lucroThreeMonthAgo(id));
	}
	
}
