package com.github.saulobezerra.contabilize.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@GetMapping(value = "/usuario/{id}/mes_ano/{mes}/{ano}")
	public ResponseEntity<LucroDTO> lucroAtualByMonthYear(@PathVariable Long id, @PathVariable int mes, @PathVariable int ano) {
		return ResponseEntity.ok().body(service.lucroAtualByMonthYear(id, mes, ano));
	}
	
	@GetMapping(value = "/usuario/{id}/ano/{ano}")
	public ResponseEntity<LucroDTO> lucroAtualByYear(@PathVariable Long id, @PathVariable int mes, @PathVariable int ano) {
		return ResponseEntity.ok().body(service.lucroAtualByYear(id, ano));
	}
	
	@GetMapping
	public ResponseEntity<List<LucroDTO>> lucroThreeMonthAgo() {
		UserSS user = UsuarioService.authenticated();
		if (user == null)
			throw new ResourceAccessException("Usuário não autorizado!");
		return ResponseEntity.ok().body(service.lucroDefault(user.getId()));
	}
	
}
