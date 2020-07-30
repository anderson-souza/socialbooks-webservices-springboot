package com.algaworks.socialbooks.domain.DTO;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LivroDTO {

	private String nome;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date publicacao;
	private String editora;
	private String resumo;
	@JsonProperty("Coments")
	private List<ComentarioDTO> comentarios;

}
