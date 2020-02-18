package com.github.saulobezerra.contabilize.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.saulobezerra.contabilize.entities.dtos.LucroDTO;
import com.github.saulobezerra.contabilize.repositories.LucroRepository;

@Service
public class LucroService {
	
	@Autowired
	private LucroRepository repository;
	
	
    private int ano = Calendar.getInstance().get(Calendar.YEAR);
    private int mes = Calendar.getInstance().get(Calendar.MONTH) + 1;
    
    private int getMes() {
    	return this.mes;
    }
    
    private int getAno() {
    	return this.ano;
    }
    
	public LucroDTO lucroAtual(Long id) {
		
		Double totalReceita = repository.totalReceita(id);
		Double totalDespesa = repository.totalDespesa(id);
		
		LucroDTO lucro = new LucroDTO(getMes(), getAno(), totalReceita, totalDespesa); // 0 -> Mes atual
		
		return lucro;
	}

	public List<LucroDTO> lucroThreeMonthAgo(Long id) {	
		
		int mes = getMes();
		int ano = getAno();
		
		Double totalReceita = 0.0;
		Double totalDespesa = 0.0;
		List<LucroDTO> listLucro = new ArrayList<>();
		
		for (int i = 0; i < 4; i++) { // São três meses atrás
			
			totalDespesa = repository.totalDespesaByMes(id, mes, ano);
			totalReceita = repository.totalReceitaByMes(id, mes, ano);
			
			if(totalDespesa == null)
				totalDespesa = 0.0;
			if(totalReceita == null)
				totalReceita = 0.0;
			
			LucroDTO lucro = new LucroDTO(mes, ano, totalReceita, totalDespesa);
			listLucro.add(lucro);
			
			mes = mes - 1; 
			if (mes == 0) {
				mes = 12;
				ano = ano - 1;
			}
			
		}
		
		return listLucro;
		
	}
}
