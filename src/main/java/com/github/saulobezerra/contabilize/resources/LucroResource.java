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
	@GetMapping(value = "/usuario/{id}/mes_ano/{mes}/{ano}")
	public ResponseEntity<LucroDTO> lucroAtualByMonthYear(@PathVariable Long id, @PathVariable int mes, @PathVariable int ano) {
		return ResponseEntity.ok().body(service.lucroAtualByMonthYear(id, mes, ano));
	}
	
	@CrossOrigin
	@GetMapping(value = "/usuario/{id}/ano/{ano}")
	public ResponseEntity<LucroDTO> lucroAtualByYear(@PathVariable Long id, @PathVariable int mes, @PathVariable int ano) {
		return ResponseEntity.ok().body(service.lucroAtualByYear(id, ano));
	}
	
	@CrossOrigin
	@GetMapping(value = "/usuario/{id}/default")
	public ResponseEntity<List<LucroDTO>> lucroThreeMonthAgo(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.lucroDefault(id));
	}
	
}
