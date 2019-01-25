package com.algaworks.socialbooks.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonInclude(Include.NON_NULL)
	@NotEmpty(message = "O comentário não pode ser vazio")
	@Size(max = 1500, message = "O comentário não pode conter mais de 1500 caracteres")
	@JsonProperty("comentario") // Essa anotação permite mudar o nome do atributo utilizado no Json
	private String texto;

	@JsonInclude(Include.NON_NULL)
	private String usuario;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonInclude(Include.NON_NULL)
	private Date data;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIVRO_ID")
	@JsonIgnore // Utilizar ManyT One para evitar dependencia circular e travar
	private Livro livro;

	public Long getId() {
		return id;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
