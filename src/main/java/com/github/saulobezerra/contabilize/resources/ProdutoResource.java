package com.github.saulobezerra.contabilize.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.github.saulobezerra.contabilize.entities.Produto;
import com.github.saulobezerra.contabilize.entities.dtos.ProdutoDTO;
import com.github.saulobezerra.contabilize.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<ProdutoDTO>> findAll(){
		List<Produto> list = service.findAll();
		List<ProdutoDTO> listDtp = list.stream().map(prod -> new ProdutoDTO(prod)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDtp);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id) {
		Produto obj = service.findById(id);
		ProdutoDTO prodDto = new ProdutoDTO(obj);
		return ResponseEntity.ok().body(prodDto);
	}
	
	@PostMapping
	public ResponseEntity<ProdutoDTO> insert(@RequestBody Produto obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				buildAndExpand(obj.getId()).toUri();
		ProdutoDTO prodDto = new ProdutoDTO(obj);
		return ResponseEntity.created(uri).body(prodDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @RequestBody ProdutoDTO obj) {
		Produto prod = service.update(id, obj);
		obj = new ProdutoDTO(prod);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<ProdutoDTO>> findByUsuario() {
		List<Produto> list = service.findByUsuario();
		List<ProdutoDTO> listDto = list.stream().map(produto -> new ProdutoDTO(produto)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
}
