package com.github.saulobezerra.contabilize.entities.dtos;

import java.io.Serializable;

import com.github.saulobezerra.contabilize.entities.Receita;
import com.github.saulobezerra.contabilize.util.Funcao;

public class ReceitaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nomeCliente;
	private String dataReceita;
	private Double valor;
	private Boolean isPago;
	private String observacao;
	private Double qtdeProduto;

	private ProdutoDTO produto;
	
	public ReceitaDTO() {
		
	}

	public ReceitaDTO(Receita receita) {
		super();
		this.id = receita.getId();
		this.nomeCliente = receita.getNomeCliente();
		this.dataReceita = Funcao.formataDataDDMMYYYY(receita.getDataReceita());
		this.valor = receita.getValor();
		this.isPago = receita.getIsPago();
		this.observacao = receita.getObservacao();
		this.qtdeProduto = receita.getQtdeProduto();
		this.produto = new ProdutoDTO(receita.getProduto());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getDataReceita() {
		return dataReceita;
	}

	public void setDataReceita(String dataReceita) {
		this.dataReceita = dataReceita;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Boolean getIsPago() {
		return isPago;
	}

	public void setIsPago(Boolean isPago) {
		this.isPago = isPago;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Double getQtdeProduto() {
		return qtdeProduto;
	}

	public void setQtdeProduto(Double qtdeProduto) {
		this.qtdeProduto = qtdeProduto;
	}

	public ProdutoDTO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoDTO produto) {
		this.produto = produto;
	}
		
}
