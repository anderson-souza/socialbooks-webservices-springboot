package com.algaworks.socialbooks.domain.DTO;

import com.algaworks.socialbooks.domain.Livro;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.modelmapper.Converter;

import java.util.Date;
import java.util.List;

@Data
public class AutorDTO {

    String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    Date nascimento;
    String nacionalidade;
    List<LivroDTO> livros;

    public static Converter<List<Livro>, List<LivroDTO>> conversorLivros() {
        return context -> GenericDTOConverter.mapList(context.getSource(), LivroDTO.class);
    }
}
