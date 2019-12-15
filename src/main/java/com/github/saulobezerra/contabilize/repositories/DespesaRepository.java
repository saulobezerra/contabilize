package com.github.saulobezerra.contabilize.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.github.saulobezerra.contabilize.entities.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long>{

	@Query("select d from Despesa d where d.usuario.id = ?1")
	List<Despesa> findByUser(Long idUsuario);
}
