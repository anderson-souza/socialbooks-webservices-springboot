package com.algaworks.socialbooks.resources;

import com.algaworks.socialbooks.domain.Comentario;
import com.algaworks.socialbooks.services.ComentariosService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/livros")
@CrossOrigin
public class ComentariosResourceImpl implements ComentariosResource {

    @Autowired
    ComentariosService comentarioService;

    // ADICIONAR COMENTÁRIO
    @Override
    @PostMapping(value = "/{id}/comentarios")
    public ResponseEntity<Void> adicionarComentario(@PathVariable("id") Long livroId,
        @RequestBody Comentario comentario) {

        comentarioService.salvarComentario(livroId, comentario);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(uri).build();
    }

    @Override
    @GetMapping(value = "/{id}/comentarios")
    public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable("id") Long livroId) {
        List<Comentario> comentarios = comentarioService.listarComentariosDeLivro(livroId);

        return ResponseEntity.status(HttpStatus.OK).body(comentarios);
    }

    @Override
    @PutMapping(value = "/{idLivro}/comentarios/{idComentario}")
    public ResponseEntity<Void> atualizarComentario(@RequestBody Comentario comentario,
        @PathVariable("idComentario") Long idComentario, @PathVariable("idLivro") Long idLivro) {
        comentario.setId(idComentario);
        comentarioService.atualizar(comentario, idLivro);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping(value = "/{idLivro}/comentarios/{idComentario}")
    public ResponseEntity<Void> deletarComentario(@PathVariable("idComentario") Long idComentario) {
        comentarioService.deletar(idComentario);
        return ResponseEntity.noContent().build();
    }

}
