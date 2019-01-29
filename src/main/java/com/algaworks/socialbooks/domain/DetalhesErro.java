package com.algaworks.socialbooks.domain;

import lombok.Data;

@Data
public class DetalhesErro {

	private String titulo;

	private Long status;

	private Long timestamp;

	private String mensagemDesenvolvedor;
}
