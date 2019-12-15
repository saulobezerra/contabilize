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
		return obj.get();
	}

	public Receita insert(Receita obj) {
		// TODO: Colocar validações
		Optional<Produto> prod = produtoRepository.findById(obj.getProduto().getId());
		Optional<Usuario> usuario = usuarioRepository.findById(obj.getUsuario().getId());;
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
		receita.setNomeCliente(obj.getNomeCliente());
		receita.setObservacao(obj.getObservacao());
		Produto prod = produtoRepository.findById(obj.getProduto().getId()).get();
		receita.setProduto(prod);
		receita.setQtdeProduto(obj.getQtdeProduto());
		receita.setValor(obj.getQtdeProduto() * prod.getValor());
		receita.setDataReceita(obj.getDataReceita());
		receita.setIsPago(obj.getIsPago());
	}
}
