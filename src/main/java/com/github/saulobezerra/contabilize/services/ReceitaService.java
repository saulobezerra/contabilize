package com.github.saulobezerra.contabilize.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.saulobezerra.contabilize.entities.Receita;
import com.github.saulobezerra.contabilize.repositories.ReceitaRepository;

@Service
public class ReceitaService {

	@Autowired
	private ReceitaRepository repository;
	
	public List<Receita> findAll() {
		return repository.findAll();
	}
	
	public Receita findById(Long id) {
		Optional<Receita> obj = repository.findById(id);
		return obj.get();
	}
}
