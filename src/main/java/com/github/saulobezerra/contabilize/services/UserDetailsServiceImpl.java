package com.github.saulobezerra.contabilize.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.saulobezerra.contabilize.entities.Usuario;
import com.github.saulobezerra.contabilize.repositories.UsuarioRepository;
import com.github.saulobezerra.contabilize.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario user = repo.findByEmailUserName(login);
		if (user == null) {
			throw new UsernameNotFoundException(login);
		}
		return new UserSS(user.getId(), user.getEmail(), user.getSenha());
	}
}