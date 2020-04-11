package com.github.saulobezerra.contabilize.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.saulobezerra.contabilize.entities.Usuario;
import com.github.saulobezerra.contabilize.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public List<Usuario> findAll() {
		return repository.findAll();
	}
	
	public Usuario findById(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		if(!obj.isPresent()) {
			throw new RuntimeException("Usuario não encontrado");
		}
		
		return obj.get();
	}

	public Usuario insert(Usuario obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public Usuario update(Long id, Usuario obj) {
		Usuario usuario = repository.getOne(id);
		updateData(usuario, obj);
		return repository.save(usuario);
	}

	private void updateData(Usuario usuario, Usuario obj) {
		usuario.setEmail(obj.getEmail()); // TODO: Verificar se ja existe e-mail cadastrado
		usuario.setNome(obj.getNome()); 
		usuario.setUserName(obj.getUserName()); // TODO: Verificar se ja existe o userName cadastrado
	}

	public Usuario findByEmailUserName(String emailUserName) {
		return repository.findByEmailUserName(emailUserName);
	}
}















