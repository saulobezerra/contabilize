package com.github.saulobezerra.contabilize.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.github.saulobezerra.contabilize.entities.Produto;
import com.github.saulobezerra.contabilize.entities.Receita;
import com.github.saulobezerra.contabilize.entities.Usuario;
import com.github.saulobezerra.contabilize.repositories.ProdutoRepository;
import com.github.saulobezerra.contabilize.repositories.ReceitaRepository;
import com.github.saulobezerra.contabilize.repositories.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ReceitaRepository receitaRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Usuario u1 = new Usuario(null, "saulo", "saulo@email", "sauloalves", "123456");
		usuarioRepository.saveAll(Arrays.asList(u1));
		
		Produto p1 = new Produto(null, "Bolo fofo", 8.00);
		Produto p2 = new Produto(null, "Bolo baeta", 10.00);
		produtoRepository.saveAll(Arrays.asList(p1, p2));
		
		Receita r1 = new Receita(null, "Severino", 1.0*p1.getValor(), true, "", p1, 1.0);
		Receita r2 = new Receita(null, "Severino", 2.0*p2.getValor(), true, "", p2, 2.0);
		receitaRepository.saveAll(Arrays.asList(r1, r2));
		
	}
	
}
