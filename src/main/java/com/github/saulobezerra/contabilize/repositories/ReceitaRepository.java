package com.github.saulobezerra.contabilize.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.github.saulobezerra.contabilize.entities.Receita;
import com.github.saulobezerra.contabilize.entities.Usuario;

public interface ReceitaRepository extends JpaRepository<Receita, Long>{

	@Query("select r from Receita r where r.usuario.id = ?1")
	List<Receita> findByUser(Long idUsuario);
	
	@Query("select r from Receita r where MONTH(r.dataReceita) = MONTH(CURRENT_DATE())\r\n" + 
			"AND YEAR(r.dataReceita) = YEAR(CURRENT_DATE()) AND r.usuario.id = ?1")
	List<Receita> findByUserAndCurrentMonth(Long idUsuario);
	
	@Query("select r from Receita r where month(r.dataReceita) = ?2 and year(r.dataReceita) = ?3 and r.usuario.id = ?1")
	List<Receita> findByMesAno(Long idUsuario, int mes, int ano);
	
	List<Receita> findByUsuarioOrderByIdDesc(Usuario usuario);
}
