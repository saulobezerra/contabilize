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
		if(obj.isEmpty()) {
			throw new RuntimeException("Tipo de despesa não encontrado");
		}
		
		return obj.get();
	}

	public TipoDespesa insert(TipoDespesa obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public TipoDespesa update(Long id, TipoDespesa obj) {
		TipoDespesa tipoDespesa = repository.getOne(id);
		updateData(tipoDespesa, obj);
		return repository.save(tipoDespesa);
	}

	private void updateData(TipoDespesa tipoDespesa, TipoDespesa obj) {
		tipoDespesa.setDescricao(obj.getDescricao());
	}
}
