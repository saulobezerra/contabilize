package com.github.saulobezerra.contabilize.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.saulobezerra.contabilize.entities.Despesa;
import com.github.saulobezerra.contabilize.entities.TipoDespesa;
import com.github.saulobezerra.contabilize.repositories.DespesaRepository;
import com.github.saulobezerra.contabilize.repositories.TipoDespesaRepository;

@Service
public class DespesaService {

	@Autowired
	private DespesaRepository repository;
	
	@Autowired
	private TipoDespesaRepository tipoDespesarepository;
	
	public List<Despesa> findAll() {
		return repository.findAll();
	}
	
	public List<Despesa> findByUser(Long id) {
		return repository.findByUser(id);
	}
	
	public Despesa findById(Long id) {
		Optional<Despesa> obj = repository.findById(id);
		return obj.get();
	}

	public Despesa insert(Despesa obj) {
		// TODO: Inserir validações
		obj.setTipo(tipoDespesarepository.findById( obj.getTipo().getId() ).get());
		obj.setValor(obj.getQtde_insumo() * obj.getValorUnitario());
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public Despesa update(Long id, Despesa obj) {
		Despesa despesa = repository.getOne(id);
		update(despesa, obj);
		return repository.save(despesa);
	}

	private void update(Despesa despesa, Despesa obj) {
		despesa.setLocal(obj.getLocal());
		despesa.setDescricao(obj.getDescricao());
		TipoDespesa tpDespesa = tipoDespesarepository.findById( obj.getTipo().getId() ).get();
		despesa.setTipo(tpDespesa);
		despesa.setQtde_insumo(obj.getQtde_insumo());
		despesa.setValorUnitario(obj.getValorUnitario());
		despesa.setValor(obj.getQtde_insumo() * obj.getValorUnitario());
	}
}
