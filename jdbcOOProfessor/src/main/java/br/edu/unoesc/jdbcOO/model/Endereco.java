package br.edu.unoesc.jdbcOO.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

	private Long codigo;
	private String rua;
	private String bairro;
	
}