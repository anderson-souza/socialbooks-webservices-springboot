package com.algaworks.socialbooks.domain.DTO;

import java.util.List;

import lombok.Data;

@Data
public class LivroDTO {

	private String nome;
	private String dataPublicacao;
	private String editora;
	private String resumo;
	private List<ComentarioDTO> comentarios;

}
