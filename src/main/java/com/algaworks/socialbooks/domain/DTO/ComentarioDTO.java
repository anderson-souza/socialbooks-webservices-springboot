package com.algaworks.socialbooks.domain.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class ComentarioDTO {

	private String texto;
	private String usuario;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private String data;

}
