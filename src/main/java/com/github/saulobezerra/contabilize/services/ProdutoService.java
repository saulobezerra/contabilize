package com.github.saulobezerra.contabilize.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.saulobezerra.contabilize.entities.Produto;
import com.github.saulobezerra.contabilize.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	public List<Produto> findAll() {
		return repository.findAll();
	}
	
	public Produto findById(Long id) {
		Optional<Produto> obj = repository.findById(id);
		return obj.get();
	}

	public Produto insert(Produto obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public Produto update(Long id, Produto obj) {
		Produto prod = repository.getOne(id);
		update(prod, obj);
		return repository.save(prod);
	}

	private void update(Produto prod, Produto obj) {
		prod.setNome(obj.getNome());
		prod.setValor(obj.getValor());
	}
}
