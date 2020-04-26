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

import com.github.saulobezerra.contabilize.entities.Usuario;
import com.github.saulobezerra.contabilize.entities.dtos.UsuarioDTO;
import com.github.saulobezerra.contabilize.security.UserSS;
import com.github.saulobezerra.contabilize.services.UsuarioService;
import com.github.saulobezerra.contabilize.services.exceptions.AuthorizationException;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<UsuarioDTO>> findAll(){
		List<Usuario> list = service.findAll();
		List<UsuarioDTO> listDto = list.stream().map(usuario -> new UsuarioDTO(usuario)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
		Usuario obj = service.findById(id);
		UsuarioDTO userDto = new UsuarioDTO(obj);
		return ResponseEntity.ok().body(userDto);
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> insert(@RequestBody Usuario obj) throws Exception {
		
		Usuario user = service.findByEmailUserName(obj.getEmail());
		if(user != null) {
			throw new Exception("Endereço de e-mail já utilizado");
		}
		
		user = service.findByEmailUserName(obj.getUserName());
		if(user != null) {
			throw new Exception("UserName já utilizado");
		}
		
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(obj.getId()).toUri();
		UsuarioDTO userDto = new UsuarioDTO(obj);
		return ResponseEntity.created(uri).body(userDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody Usuario obj) {
		obj = service.update(id, obj);
		UsuarioDTO userDto = new UsuarioDTO(obj);
		return ResponseEntity.ok().body(userDto);
	}
	
	@GetMapping(value = "/usuarioLogado")
	public ResponseEntity<UsuarioDTO> getUsuarioLogado() {
		UserSS user = UsuarioService.authenticated();
		if (user == null)
			throw new AuthorizationException("Usuário não autenticado!");
		Usuario obj = service.findById(user.getId());
		UsuarioDTO userDto = new UsuarioDTO(obj);
		return ResponseEntity.ok().body(userDto);
	}
}












