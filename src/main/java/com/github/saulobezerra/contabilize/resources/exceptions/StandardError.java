package com.github.saulobezerra.contabilize.resources.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String msg;
	private Long timestemp;
	
	public StandardError(Integer status, String msg, Long timestemp) {
		super();
		this.status = status;
		this.msg = msg;
		this.timestemp = timestemp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getTimestemp() {
		return timestemp;
	}

	public void setTimestemp(Long timestemp) {
		this.timestemp = timestemp;
	}
}
