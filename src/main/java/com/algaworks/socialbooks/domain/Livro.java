package com.algaworks.socialbooks.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Entity
@Data
public class Livro {

	@JsonInclude(Include.NON_NULL)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonInclude(Include.NON_NULL)
	@NotEmpty(message = "O campo nome não pode ser vazio")
	private String nome;

	@JsonInclude(Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Campo publicação é obrigatório")
	private Date publicacao;

	@JsonInclude(Include.NON_NULL)
	@NotNull(message = "Campo editora é obrigatório")
	private String editora;

	@JsonInclude(Include.NON_NULL)
	@NotNull(message = "O resumo é obrigatório")
	@Size(max = 1500, message = "O resumo não pode conter mais de 1500 caracteres.")
	private String resumo;

	@JsonInclude(Include.NON_EMPTY)
	@OneToMany(mappedBy = "livro")
	private List<Comentario> comentarios;

	@ManyToOne()
	@JoinColumn(name = "AUTOR_ID") // Cria uma coluna na tabela chamada de AUTOR_ID
	@JsonInclude(Include.NON_NULL)
	private Autor autor;

}
