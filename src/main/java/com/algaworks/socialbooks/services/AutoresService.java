package com.algaworks.socialbooks.services;

import com.algaworks.socialbooks.domain.Autor;
import com.algaworks.socialbooks.repository.AutoresRepository;
import com.algaworks.socialbooks.services.exceptions.AutorExistenteException;
import com.algaworks.socialbooks.services.exceptions.AutorNaoEncontradoException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AutoresService {

    @Autowired
    private AutoresRepository autoresRepository;

    public List<Autor> listar() {
        return autoresRepository.findAll();
    }

    public Autor salvar(Autor autor) {
        if (autor.getId() != null) {
            Optional<Autor> a = autoresRepository.findById(autor.getId());

            if (a.isPresent()) {
                throw new AutorExistenteException("O autor já existe");
            }
        }
        return autoresRepository.save(autor);
    }

    public Autor buscar(Long id) {
        Autor autor;
        try {
            autor = autoresRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new AutorNaoEncontradoException("O autor não pode ser encontrado");
        }
        return autor;
    }

    public void deletar(Long id) {
        try {
            autoresRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new AutorNaoEncontradoException("O autor não foi encontrado krai");
        }
    }

    public void atualizar(Autor autor) {
        verificarExistencia(autor);
        autoresRepository.save(autor);
    }

    private void verificarExistencia(Autor autor) {
        buscar(autor.getId());
    }

}
