package com.github.saulobezerra.contabilize.entities.dtos;

import java.io.Serializable;

import com.github.saulobezerra.contabilize.entities.Despesa;
import com.github.saulobezerra.contabilize.entities.TipoDespesa;
import com.github.saulobezerra.contabilize.util.Funcao;

public class DespesaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Double qtde_insumo;
	private Double valor;
	private Double valorUnitario;
	private String local;
	private String descricao;
	private String data;
	
	private TipoDespesa tipo;
	
	public DespesaDTO() {
		
	}

	public DespesaDTO(Despesa despesa) {
		super();
		this.id = despesa.getId();
		this.qtde_insumo = despesa.getQtde_insumo();
		this.valor = despesa.getValor();
		this.valorUnitario = despesa.getValorUnitario();
		this.local = despesa.getLocal();
		this.descricao = despesa.getDescricao();
		this.data = Funcao.formataDataDDMMYYYY(despesa.getData());
		this.tipo = despesa.getTipo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getQtde_insumo() {
		return qtde_insumo;
	}

	public void setQtde_insumo(Double qtde_insumo) {
		this.qtde_insumo = qtde_insumo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public TipoDespesa getTipo() {
		return tipo;
	}

	public void setTipo(TipoDespesa tipo) {
		this.tipo = tipo;
	}
	
}
