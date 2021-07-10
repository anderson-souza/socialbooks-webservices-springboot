package com.algaworks.socialbooks.services;

import com.algaworks.socialbooks.domain.Comentario;
import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.ComentariosRepository;
import com.algaworks.socialbooks.services.exceptions.ComentarioNaoEncontradoException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ComentariosServiceImpl implements ComentariosService {

    @Autowired
    ComentariosRepository comentariosRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    LivrosService livrosService;

    @Override
    public List<Comentario> listarTodosComentarios() {
        return comentariosRepository.findAll();
    }

    @Override
    public Comentario buscar(Long id) {
        return comentariosRepository.findById(id)
            .orElseThrow(() -> new ComentarioNaoEncontradoException("Comentário não encontrado"));
    }

    @Override
    public Comentario salvarComentario(Long livroId, Comentario comentario) {
        Livro livro = livrosService.buscar(livroId);
        comentario.setUsuario(authenticationService
            .retornaNomeUsuarioConectado());// Pega o usuário conectado no momento
        comentario.setLivro(livro);
        comentario.setData(new Date());

        return comentariosRepository.save(comentario);
    }

    @Override
    public List<Comentario> listarComentariosDeLivro(Long livroId) {
        return livrosService.buscar(livroId).getComentarios();
    }

    @Override
    public void atualizar(Comentario comentario, Long idLivro) {
        verificaExistencia(comentario);
        comentario.setLivro(livrosService.buscar(idLivro));
        comentario.setUsuario(authenticationService.retornaNomeUsuarioConectado());
        comentario.setData(new Date());
        comentariosRepository.save(comentario);
    }

    @Override
    public void deletar(Long id) {
        try {
            comentariosRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ComentarioNaoEncontradoException("Comentário não foi encontrado");
        }

    }

    private void verificaExistencia(Comentario comentario) {
        buscar(comentario.getId());
    }

}
