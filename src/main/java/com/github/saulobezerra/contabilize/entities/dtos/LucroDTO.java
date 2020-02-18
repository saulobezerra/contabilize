package com.github.saulobezerra.contabilize.entities.dtos;

import java.io.Serializable;

public class LucroDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer mes;
	private Integer ano;
	private Double vLucro;
	private Double totalReceita;
	private Double totalDespesa;
	
	public LucroDTO() {
		
	}

	public LucroDTO(Integer mes, Integer ano, Double totalReceita, Double totalDespesa) {
		super();
		this.mes = mes;
		this.ano = ano;
		this.vLucro = totalReceita - totalDespesa;
		this.totalReceita = totalReceita;
		this.totalDespesa = totalDespesa;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Double getvLucro() {
		return vLucro;
	}

	public void setvLucro(Double vLucro) {
		this.vLucro = vLucro;
	}

	public Double getTotalReceita() {
		return totalReceita;
	}

	public void setTotalReceita(Double totalReceita) {
		this.totalReceita = totalReceita;
	}

	public Double getTotalDespesa() {
		return totalDespesa;
	}

	public void setTotalDespesa(Double totalDespesa) {
		this.totalDespesa = totalDespesa;
	}

}
