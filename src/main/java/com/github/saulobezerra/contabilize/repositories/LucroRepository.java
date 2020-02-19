package com.github.saulobezerra.contabilize.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.github.saulobezerra.contabilize.entities.Receita;

public interface LucroRepository extends JpaRepository<Receita, Long> {

	
	@Query("SELECT MONTH(r.dataReceita), SUM(r.valor) "
			+ "FROM Receita r where MONTH(r.dataReceita) = MONTH(CURRENT_DATE()) "
			+ "AND YEAR(r.dataReceita) = YEAR(CURRENT_DATE()) "
			+ "AND r.usuario.id = ?1 "
			+ "AND r.isPago = true")
	String totalReceitaCurrentMonth(Long id);
	
	@Query("SELECT MONTH(d.data), SUM(d.valor) "
			+ "FROM Despesa d where MONTH(d.data) = MONTH(CURRENT_DATE()) "
			+ "AND YEAR(d.data) = YEAR(CURRENT_DATE()) "
			+ "AND d.usuario.id = ?1")
	String totalDespesaCurrentMonth(Long id);
	
	@Query("select SUM(d.valor) from Despesa d where MONTH(d.data) = MONTH(CURRENT_DATE()) \r\n" + 
			"AND YEAR(d.data) = YEAR(CURRENT_DATE()) AND d.usuario.id = ?1")
	Double totalDespesa(Long id);
	
	@Query("select SUM(r.valor) from Receita r where MONTH(r.dataReceita) = MONTH(CURRENT_DATE()) \r\n" + 
			"AND YEAR(r.dataReceita) = YEAR(CURRENT_DATE()) AND r.usuario.id = ?1 AND r.isPago = true")
	Double totalReceita(Long id);
	
	@Query("select SUM(d.valor) from Despesa d where MONTH(d.data) = ?2 \r\n" + 
			"AND YEAR(d.data) = ?3 AND d.usuario.id = ?1")
	Double totalDespesaByMes(Long id, int mes, int ano);
	
	@Query("select SUM(r.valor) from Receita r where MONTH(r.dataReceita) = ?2 \r\n" + 
			"AND YEAR(r.dataReceita) = ?3 AND r.usuario.id = ?1 AND r.isPago = true")
	Double totalReceitaByMes(Long id, int mes, int ano);

	@Query("select SUM(d.valor) from Despesa d where YEAR(d.data) = ?2 AND d.usuario.id = ?1")
	Double totalDespesaByAno(Long id, int ano);

	@Query("select SUM(r.valor) from Receita r where YEAR(r.dataReceita) = ?2 AND r.usuario.id = ?1 AND r.isPago = true")
	Double totalReceitaByAno(Long id, int ano);
	
}
