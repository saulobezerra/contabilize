package com.github.saulobezerra.contabilize.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.github.saulobezerra.contabilize.entities.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long>{

	@Query("select d from Receita d where d.usuario.id = ?1")
	List<Receita> findByUser(Long idUsuario);
}
