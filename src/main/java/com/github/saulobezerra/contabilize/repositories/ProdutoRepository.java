package com.github.saulobezerra.contabilize.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.saulobezerra.contabilize.entities.Produto;
import com.github.saulobezerra.contabilize.entities.Usuario;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	List<Produto> findByUsuario(Usuario usuario);
}
