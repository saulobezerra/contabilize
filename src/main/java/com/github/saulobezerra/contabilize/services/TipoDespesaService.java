package com.github.saulobezerra.contabilize.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.saulobezerra.contabilize.entities.TipoDespesa;
import com.github.saulobezerra.contabilize.repositories.TipoDespesaRepository;

@Service
public class TipoDespesaService {

	@Autowired
	private TipoDespesaRepository repository;
	
	public List<TipoDespesa> findAll() {
		return repository.findAll();
	}
	
	public TipoDespesa findById(Long id) {
		Optional<TipoDespesa> obj = repository.findById(id);
		return obj.get();
	}
}
