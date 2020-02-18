package com.github.saulobezerra.contabilize.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.github.saulobezerra.contabilize.entities.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long>{

	@Query("select d from Despesa d where d.usuario.id = ?1")
	List<Despesa> findByUser(Long idUsuario);
	
	@Query("select d from Despesa d where MONTH(d.data) = MONTH(CURRENT_DATE())\r\n" + 
			"AND YEAR(d.data) = YEAR(CURRENT_DATE()) AND d.usuario.id = ?1")
	List<Despesa> findByUserAndCurrentMonth(Long idUsuario);
	
	@Query("select d from Despesa d where month(d.data) = ?2 and year(d.data) = ?3 and d.usuario.id = ?1")
	List<Despesa> findByMesAno(Long idUsuario, int mes, int ano);
}
