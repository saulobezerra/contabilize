package com.github.saulobezerra.contabilize.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.github.saulobezerra.contabilize.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Query("select u from Usuario u where u.email = ?1 or u.userName = ?1")
	Usuario findByEmailUserName(String emailUserName);

}
