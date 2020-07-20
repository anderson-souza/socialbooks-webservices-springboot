package com.algaworks.socialbooks.domain.DTO;

import lombok.Data;

@Data
public class LivroDTO {

	private String nome;
	private String dataPublicacao;
	private String editora;
	private String resumo;
	private String comentarios;

}
