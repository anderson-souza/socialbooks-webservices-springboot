package com.algaworks.socialbooks.resources;

import com.algaworks.socialbooks.domain.Comentario;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ComentariosResource {

    // ADICIONAR COMENTÁRIO
    @PostMapping(value = "/{id}/comentarios")
    ResponseEntity<Void> adicionarComentario(@PathVariable("id") Long livroId,
        @RequestBody Comentario comentario);

    @GetMapping(value = "/{id}/comentarios")
    ResponseEntity<List<Comentario>> listarComentarios(@PathVariable("id") Long livroId);

    @PutMapping(value = "/{idLivro}/comentarios/{idComentario}")
    ResponseEntity<Void> atualizarComentario(@RequestBody Comentario comentario,
        @PathVariable("idComentario") Long idComentario, @PathVariable("idLivro") Long idLivro);

    @DeleteMapping(value = "/{idLivro}/comentarios/{idComentario}")
    ResponseEntity<Void> deletarComentario(@PathVariable("idComentario") Long idComentario);
}
