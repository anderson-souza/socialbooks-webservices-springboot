package com.algaworks.socialbooks.services;

import com.algaworks.socialbooks.domain.Comentario;
import java.util.List;

public interface ComentariosService {

    List<Comentario> listarTodosComentarios();

    Comentario buscar(Long id);

    Comentario salvarComentario(Long livroId, Comentario comentario);

    List<Comentario> listarComentariosDeLivro(Long livroId);

    void atualizar(Comentario comentario, Long idLivro);

    void deletar(Long id);
}
