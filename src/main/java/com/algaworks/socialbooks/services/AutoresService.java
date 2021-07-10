package com.algaworks.socialbooks.services;

import com.algaworks.socialbooks.domain.Autor;
import java.util.List;

public interface AutoresService {

    List<Autor> listar();

    Autor salvar(Autor autor);

    Autor buscar(Long id);

    void deletar(Long id) throws Exception;

    void atualizar(Autor autor);
}
