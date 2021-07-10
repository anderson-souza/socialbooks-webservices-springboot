package com.algaworks.socialbooks.services;

import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.LivrosRepository;
import com.algaworks.socialbooks.services.exceptions.LivroNaoEncontradoException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class LivrosServiceImpl implements LivrosService {

    @Autowired
    private LivrosRepository livrosRepository;

    @Override
    public List<Livro> listar() {
        return livrosRepository.findAll();
    }

    @Override
    public Livro buscar(Long id) {
        return livrosRepository.findById(id)
            .orElseThrow(() -> new LivroNaoEncontradoException("O livro não foi encontrado"));
    }

    @Override
    public Livro salvar(Livro livro) {
        livro.setId(null);
        return livrosRepository.save(livro);
    }

    @Override
    public void deletar(Long id) {
        try {
            livrosRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new LivroNaoEncontradoException("O livro não pôde ser encontrado");
        }
    }

    @Override
    public void atualizar(Livro livro) {
        verificarExistencia(livro);
        livrosRepository.save(livro);
    }

    private void verificarExistencia(Livro livro) {
        buscar(livro.getId());
    }

}
