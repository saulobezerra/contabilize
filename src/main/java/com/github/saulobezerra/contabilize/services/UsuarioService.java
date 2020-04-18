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

	public Usuario insert(Usuario obj) {
		// TODO: Fazer validações dos atributos
		obj.setNome(pe.encode(obj.getSenha()));
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















