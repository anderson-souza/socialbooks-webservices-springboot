package com.algaworks.socialbooks.services;

import com.algaworks.socialbooks.domain.Autor;
import com.algaworks.socialbooks.repository.AutoresRepository;
import com.algaworks.socialbooks.services.exceptions.AutorExistenteException;
import com.algaworks.socialbooks.services.exceptions.AutorNaoEncontradoException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AutoresServiceImpl implements AutoresService {

    @Autowired
    private AutoresRepository autoresRepository;

    @Override
    public List<Autor> listar() {
        return autoresRepository.findAll();
    }

    @Override
    public Autor salvar(Autor autor) {
        if (autor.getId() != null) {
            Autor autorDatabase = buscar(autor.getId());

            if (autorDatabase != null) {
                throw new AutorExistenteException("O autor já existe");
            }
        }
        return autoresRepository.save(autor);
    }

    @Override
    public Autor buscar(Long id) {
        return autoresRepository.findById(id)
            .orElseThrow(() -> new AutorNaoEncontradoException("O autor não pode ser encontrado"));
    }

    @Override
    public void deletar(Long id) throws Exception {
        try {
            autoresRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new AutorNaoEncontradoException("O autor não foi encontrado");
        } catch (Exception e) {
            throw new Exception("Houve um erro ao excluir o autor");
        }
    }

    @Override
    public void atualizar(Autor autor) {
        verificarExistencia(autor);
        autoresRepository.save(autor);
    }

    private void verificarExistencia(Autor autor) {
        buscar(autor.getId());
    }

}
