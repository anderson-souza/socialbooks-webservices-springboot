package com.algaworks.socialbooks.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Entity
@Data
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "O campo nome não pode ser vazio")
	private String nome;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonInclude(Include.NON_NULL)
	@NotNull(message = "O campo nascimento é obrigatório")
	private Date nascimento;

	@JsonInclude(Include.NON_NULL)
	@NotNull(message = "Campo nacionalidade é obrigatório")
	private String nacionalidade;

	@OneToMany(mappedBy = "autor") // Um determinado autor pode ter vários lviros
	@JsonIgnore // Evita criação ciclica
	private List<Livro> livros;

}
