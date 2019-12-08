package com.github.saulobezerra.contabilize.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.saulobezerra.contabilize.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
