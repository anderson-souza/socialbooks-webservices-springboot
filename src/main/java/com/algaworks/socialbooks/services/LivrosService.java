package com.algaworks.socialbooks.services;

import com.algaworks.socialbooks.domain.Livro;
import java.util.List;

public interface LivrosService {

    List<Livro> listar();

    Livro buscar(Long id);

    Livro salvar(Livro livro);

    void deletar(Long id);

    void atualizar(Livro livro);
}
