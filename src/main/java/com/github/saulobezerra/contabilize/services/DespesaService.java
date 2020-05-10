package com.github.saulobezerra.contabilize.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.saulobezerra.contabilize.entities.Despesa;
import com.github.saulobezerra.contabilize.entities.TipoDespesa;
import com.github.saulobezerra.contabilize.entities.Usuario;
import com.github.saulobezerra.contabilize.entities.dtos.DespesaDTO;
import com.github.saulobezerra.contabilize.repositories.DespesaRepository;
import com.github.saulobezerra.contabilize.repositories.TipoDespesaRepository;
import com.github.saulobezerra.contabilize.repositories.UsuarioRepository;
import com.github.saulobezerra.contabilize.security.UserSS;
import com.github.saulobezerra.contabilize.services.exceptions.AuthorizationException;

@Service
public class DespesaService {

	@Autowired
	private DespesaRepository repository;
	
	@Autowired
	private TipoDespesaRepository tipoDespesarepository;

	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	public List<Despesa> findAll() {
		return repository.findAll();
	}
	
	public Despesa findById(Long id) {
		Optional<Despesa> obj = repository.findById(id);
		if(!obj.isPresent()) {
			throw new RuntimeException("Despesa não encontrado");
		}
		
		return obj.get();
	}

	public Despesa insert(Despesa obj) {
		// TODO: Inserir validações
		Optional<Usuario> usuario = usuarioRepository.findById(obj.getUsuario().getId());
		Optional <TipoDespesa> tipoDespesa = tipoDespesarepository.findById( obj.getTipo().getId() );
		
		if(!usuario.isPresent()) {
			throw new RuntimeException("Usuário não encontrado");
		}
		if(!tipoDespesa.isPresent()) {
			throw new RuntimeException("Tipo de despesa inexistente");
		}
		
		obj.setTipo(tipoDespesa.get());
		obj.setUsuario(usuario.get());
		
		if(obj.getData() == null) {
			obj.setData(new Date());
		}else {			
			obj.setData(obj.getData());
		}
		
		obj.setValor(obj.getQtde_insumo() * obj.getValorUnitario());
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public Despesa update(Long id, DespesaDTO obj) {
		Despesa despesa = repository.getOne(id);
		update(despesa, obj);
		return repository.save(despesa);
	}

	private void update(Despesa despesa, DespesaDTO obj) {
		
		Optional <TipoDespesa> tipoDespesa = tipoDespesarepository.findById( obj.getTipo().getId() );
		
		if(!tipoDespesa.isPresent()) {
			throw new RuntimeException("Tipo de despesa inexistente");
		}
		
		despesa.setTipo(tipoDespesa.get());
		
		despesa.setLocal(obj.getLocal());
		despesa.setDescricao(obj.getDescricao());
		despesa.setQtde_insumo(obj.getQtde_insumo());
		despesa.setValorUnitario(obj.getValorUnitario());
		despesa.setValor(obj.getQtde_insumo() * obj.getValorUnitario());
	}
	
	public List<Despesa> findByMesAno(int mes, int ano) {
		UserSS user = UsuarioService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		return repository.findByMesAno(user.getId(), mes, ano);
	}
	
	public List<Despesa> findByUsuario() {
		UserSS user = UsuarioService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		return repository.findByUserAndCurrentMonth(user.getId());	}
}
