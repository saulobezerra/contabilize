package com.github.saulobezerra.contabilize.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Funcao {
	
	public static String formataDataDDMMYYYY(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(data);
	}

}
