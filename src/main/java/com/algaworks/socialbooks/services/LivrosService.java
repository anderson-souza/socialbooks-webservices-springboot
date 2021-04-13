package com.algaworks.socialbooks.services;

import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.LivrosRepository;
import com.algaworks.socialbooks.services.exceptions.LivroNaoEncontradoException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class LivrosService {

    @Autowired
    private LivrosRepository livrosRepository;

    public List<Livro> listar() {
        return livrosRepository.findAll();
    }

    public Livro buscar(Long id) {

        Livro livro;

        try {
            livro = livrosRepository.findById(id).get();
        } catch (Exception e) {
            throw new LivroNaoEncontradoException("O livro não foi encontrado");
        }

        return livro;
    }

    public Livro salvar(Livro livro) {
        livro.setId(
            null); // Utilizado para garantir que será criado uma nova instancia e não atualizar
        // uma outra

        return livrosRepository.save(livro);
    }

    public void deletar(Long id) {
        try {
            livrosRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new LivroNaoEncontradoException("O livro não pôde ser encontrado");
        }
    }

    public void atualizar(Livro livro) {
        verificarExistencia(livro);
        livrosRepository.save(livro);
    }

    private void verificarExistencia(Livro livro) {
        buscar(livro.getId());
    }

}
