package com.github.saulobezerra.contabilize.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.github.saulobezerra.contabilize.entities.Despesa;
import com.github.saulobezerra.contabilize.entities.TipoDespesa;
import com.github.saulobezerra.contabilize.entities.Produto;
import com.github.saulobezerra.contabilize.entities.Receita;
import com.github.saulobezerra.contabilize.entities.Usuario;
import com.github.saulobezerra.contabilize.repositories.DespesaRepository;
import com.github.saulobezerra.contabilize.repositories.TipoDespesaRepository;
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
	
	@Autowired
	private TipoDespesaRepository insumoRepository;
	
	@Autowired
	private DespesaRepository despesaRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Usuario u1 = new Usuario(null, "saulo", "saulo@email", "sauloalves", "123456");
		usuarioRepository.saveAll(Arrays.asList(u1));
		
		Produto p1 = new Produto(null, "Bolo fofo", 8.00);
		Produto p2 = new Produto(null, "Bolo baeta", 10.00);
		produtoRepository.saveAll(Arrays.asList(p1, p2));
		
		Receita r1 = new Receita(null, "Severino", true, "", p1, 1.0, u1);
		Receita r2 = new Receita(null, "Severino", true, "", p2, 2.0, u1);
		receitaRepository.saveAll(Arrays.asList(r1, r2));
		
		TipoDespesa i1 = new TipoDespesa(null, "Material");
		TipoDespesa i2 = new TipoDespesa(null, "Gás");
		TipoDespesa i3 = new TipoDespesa(null, "Energia");
		TipoDespesa i4 = new TipoDespesa(null, "Água");
		TipoDespesa i5 = new TipoDespesa(null, "Telefone");
		TipoDespesa i6 = new TipoDespesa(null, "Equipamento");
		TipoDespesa i7 = new TipoDespesa(null, "Capital");
		insumoRepository.saveAll(Arrays.asList(i1, i2, i3, i4, i5, i6, i7));
		
		Despesa d1 = new Despesa(null, 10.00, "Bem Mais", "", 43.00, i1);
		Despesa d2 = new Despesa(null, 1.00, "Todo Dia", "", 23.00, i2);
		despesaRepository.saveAll(Arrays.asList(d1,d2));
		
	}
	
}
