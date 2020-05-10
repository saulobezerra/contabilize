package com.github.saulobezerra.contabilize.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import com.github.saulobezerra.contabilize.entities.dtos.LucroDTO;
import com.github.saulobezerra.contabilize.security.UserSS;
import com.github.saulobezerra.contabilize.services.LucroService;
import com.github.saulobezerra.contabilize.services.UsuarioService;

@RestController
@RequestMapping(value = "/lucros")
public class LucroResource {

	@Autowired
	private LucroService service;
	
	@GetMapping(value = "/periodo") //periodo?mes=4&ano=2020
	public ResponseEntity<LucroDTO> lucroAtualByMonthYear(
			@RequestParam(value = "mes") int mes, 
			@RequestParam(value = "ano") int ano) {
		UserSS user = UsuarioService.authenticated();
		if (user == null)
			throw new ResourceAccessException("Usuário não autorizado!");
		return ResponseEntity.ok().body(service.lucroAtualByMonthYear(user.getId(), mes, ano));
	}
	
	@GetMapping(value = "/porAno") //periodo?ano=2020
	public ResponseEntity<LucroDTO> lucroAtualByYear(@RequestParam(value = "ano") int ano) {
		UserSS user = UsuarioService.authenticated();
		if (user == null)
			throw new ResourceAccessException("Usuário não autorizado!");
		return ResponseEntity.ok().body(service.lucroAtualByYear(user.getId(), ano));
	}
	
	@GetMapping
	public ResponseEntity<List<LucroDTO>> lucroThreeMonthAgo() {
		UserSS user = UsuarioService.authenticated();
		if (user == null)
			throw new ResourceAccessException("Usuário não autorizado!");
		return ResponseEntity.ok().body(service.lucroDefault(user.getId()));
	}
	
}
