package com.github.saulobezerra.contabilize.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.saulobezerra.contabilize.entities.Produto;
import com.github.saulobezerra.contabilize.entities.Receita;
import com.github.saulobezerra.contabilize.entities.Usuario;
import com.github.saulobezerra.contabilize.entities.dtos.ProdutoDTO;
import com.github.saulobezerra.contabilize.repositories.ProdutoRepository;
import com.github.saulobezerra.contabilize.repositories.UsuarioRepository;
import com.github.saulobezerra.contabilize.security.UserSS;
import com.github.saulobezerra.contabilize.services.exceptions.AuthorizationException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public List<Produto> findAll() {
		return repository.findAll();
	}
	
	public Produto findById(Long id) {
		Optional<Produto> obj = repository.findById(id);
		if(!obj.isPresent()) {
			throw new RuntimeException("Produto não encontrado");
		}
		
		return obj.get();
	}

	public Produto insert(Produto obj) {
		Optional<Usuario> usuario = usuarioRepository.findById(obj.getUsuario().getId());
		if(!usuario.isPresent()) {
			throw new RuntimeException("Usuario não encontrado");
		}
		
		obj.setUsuario(usuario.get());
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public Produto update(Long id, ProdutoDTO obj) {
		Produto prod = repository.getOne(id);
		update(prod, obj);
		return repository.save(prod);
	}

	private void update(Produto prod, ProdutoDTO obj) {
		prod.setNome(obj.getNome());
		prod.setValor(obj.getValor());
	}
	
	public List<Produto> findByUsuario() {
		UserSS user = UsuarioService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		Usuario usuario = usuarioService.findById(user.getId());
		return repository.findByUsuario(usuario);
	}
}
