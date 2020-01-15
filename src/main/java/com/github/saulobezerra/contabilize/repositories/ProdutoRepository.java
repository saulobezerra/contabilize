package com.github.saulobezerra.contabilize.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.github.saulobezerra.contabilize.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	@Query("select p from Produto p where p.usuario.id = ?1")
	List<Produto> findByUser(Long idUsuario);
	
}
