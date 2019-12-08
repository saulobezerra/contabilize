package com.github.saulobezerra.contabilize.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.saulobezerra.contabilize.entities.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long>{

}
