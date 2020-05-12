package com.github.saulobezerra.contabilize.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.saulobezerra.contabilize.entities.Usuario;
import com.github.saulobezerra.contabilize.repositories.UsuarioRepository;
import com.github.saulobezerra.contabilize.security.UserSS;
import com.github.saulobezerra.contabilize.services.exceptions.AuthorizationException;
import com.github.saulobezerra.contabilize.services.exceptions.ObjectConflictedException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public List<Usuario> findAll() {
		return repository.findAll();
	}
	
	public Usuario findById(Long id) {
		
		UserSS user = authenticated();
		if (user==null || !id.equals(user.getId()))
			throw new AuthorizationException("Acesso Negado.");
		
		Optional<Usuario> obj = repository.findById(id);
		if(!obj.isPresent()) {
			throw new RuntimeException("Usuario não encontrado");
		}
		
		return obj.get();
	}
	
	public Usuario findByEmail(String email) {
		Usuario obj = repository.findByEmail(email);
		return obj;
	}
	
	public Usuario findByUserName(String userName) {
		Usuario obj = repository.findByUserName(userName);
		return obj;
	}

	public Usuario insert(Usuario obj) {
		Usuario user = repository.findByEmail(obj.getEmail());
		if(user != null) {
			throw new ObjectConflictedException("Endereço de e-mail já utilizado.");
		}
		
		user = repository.findByUserName(obj.getUserName());
		if(user != null) {
			throw new ObjectConflictedException("UserName não disponível.");
		}
		
		obj.setSenha(pe.encode(obj.getSenha()));
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public Usuario update(Long id, Usuario obj) {
		Usuario user = repository.findByEmail(obj.getEmail());
		Usuario usuario = findById(id);
		
		if(user != null && !usuario.getEmail().equals(user.getEmail())) {
			throw new ObjectConflictedException("Esse email não pode ser utilizado!");
		}
		user = repository.findByUserName(obj.getUserName());
		
		if(user != null && !usuario.getUserName().equals(user.getUserName())) {
			throw new ObjectConflictedException("Esse User Name não pode ser utilizado!");
		}
		
		updateData(usuario, obj);
		return repository.save(usuario);
	}

	private void updateData(Usuario usuario, Usuario obj) {
		usuario.setEmail(obj.getEmail());
		usuario.setNome(obj.getNome()); 
		usuario.setUserName(obj.getUserName());
	}

	public void validaUsuario (Usuario user, String senha) throws Exception {
		if(user == null) {
			throw new Exception("Usuário não encontrado");
		}
		
		if( !(pe.matches(senha, user.getSenha())) ) {
			throw new Exception("Erro na autenticação");
		}
	}
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}















