package com.github.saulobezerra.contabilize.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.saulobezerra.contabilize.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
