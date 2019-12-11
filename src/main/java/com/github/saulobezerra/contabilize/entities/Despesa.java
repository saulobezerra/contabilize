package com.github.saulobezerra.contabilize.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Despesa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double qtde_insumo;
	private Double valor;
	private String local;
	private String observacao;
	private Date data;

	@ManyToOne
	@JoinColumn(name = "tipo_id")
	private TipoDespesa tipo;
	
	public Despesa() {
		
	}

	public Despesa(Long id, Double qtde_insumo, String local, String observacao, Double valor, TipoDespesa tipo) {
		super();
		this.id = id;
		this.qtde_insumo = qtde_insumo;
		this.valor = valor;
		this.local = local;
		this.observacao = observacao;
		this.tipo = tipo;
		this.data = new Date();
	}
	
	public TipoDespesa getTipo() {
		return tipo;
	}

	public void setTipo(TipoDespesa tipo) {
		this.tipo = tipo;
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


	public String getLocal() {
		return local;
	}


	public void setLocal(String local) {
		this.local = local;
	}


	public String getObservacao() {
		return observacao;
	}


	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Despesa other = (Despesa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
