package com.github.saulobezerra.contabilize.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.saulobezerra.contabilize.entities.Produto;
import com.github.saulobezerra.contabilize.entities.Receita;
import com.github.saulobezerra.contabilize.entities.Usuario;
import com.github.saulobezerra.contabilize.repositories.ProdutoRepository;
import com.github.saulobezerra.contabilize.repositories.ReceitaRepository;
import com.github.saulobezerra.contabilize.repositories.UsuarioRepository;

@Service
public class ReceitaService {

	@Autowired
	private ReceitaRepository repository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Receita> findAll() {
		return repository.findAll();
	}
	
	public List<Receita> findByUser(Long id) {
		return repository.findByUser(id);
	}
	
	public Receita findById(Long id) {
		Optional<Receita> obj = repository.findById(id);
		if(!obj.isPresent()) {
			throw new RuntimeException("Receita não encontrado");
		}
		
		return obj.get();
	}

	public Receita insert(Receita obj) {
		// TODO: Colocar validações
		Optional<Produto> prod = produtoRepository.findById(obj.getProduto().getId());
		Optional<Usuario> usuario = usuarioRepository.findById(obj.getUsuario().getId());
		
		if(!usuario.isPresent()) {
			throw new RuntimeException("Usuário não encontrado");
		}
		if(!prod.isPresent()) {
			throw new RuntimeException("Produto não encontrado");
		}
		
		obj.setProduto(prod.get());
		obj.setUsuario(usuario.get());
		obj.setValor(obj.getQtdeProduto() * obj.getProduto().getValor());
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public Receita update(Long id, Receita obj) {
		Receita receita = repository.getOne(id);
		update(receita, obj);
		return repository.save(receita);
	}

	private void update(Receita receita, Receita obj) {
		Optional<Produto> prod = produtoRepository.findById(obj.getProduto().getId());
		
		if(!prod.isPresent()) {
			throw new RuntimeException("Produto não encontrado");
		}
		
		receita.setNomeCliente(obj.getNomeCliente());
		receita.setObservacao(obj.getObservacao());
		receita.setProduto(prod.get());
		receita.setQtdeProduto(obj.getQtdeProduto());
		receita.setValor(obj.getQtdeProduto() * prod.get().getValor());
		receita.setDataReceita(obj.getDataReceita());
		receita.setIsPago(obj.getIsPago());
	}

	public List<Receita> findByUserAndCurrentMonth(Long idUsuario) {
		return repository.findByUserAndCurrentMonth(idUsuario);
	}
	
	public List<Receita> findByMesAno(Long idUsuario, int mes, int ano) {
		return repository.findByMesAno(idUsuario, mes, ano);
	}
}
