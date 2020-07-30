package com.algaworks.socialbooks.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.socialbooks.domain.Comentario;
import com.algaworks.socialbooks.services.ComentariosService;

@RestController
@RequestMapping("/livros")
@CrossOrigin
public class ComentariosResource {

	@Autowired
	ComentariosService comentarioService;

	// ADICIONAR COMENTÁRIO
	@RequestMapping(value = "/{id}/comentarios", method = RequestMethod.POST)
	public ResponseEntity<Void> adicionarComentario(@PathVariable("id") Long livroId,
			@RequestBody Comentario comentario) {

		comentarioService.salvarComentario(livroId, comentario);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}/comentarios", method = RequestMethod.GET)
	public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable("id") Long livroId) {
		List<Comentario> comentarios = comentarioService.listarComentariosDeLivro(livroId);

		return ResponseEntity.status(HttpStatus.OK).body(comentarios);
	}

	@PutMapping(value = "/{idLivro}/comentarios/{idComentario}")
	public ResponseEntity<Void> atualizarComentario(@RequestBody Comentario comentario,
			@PathVariable("idComentario") Long idComentario, @PathVariable("idLivro") Long idLivro) {
		comentario.setId(idComentario);
		comentarioService.atualizar(comentario, idLivro);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{idLivro}/comentarios/{idComentario}")
	public ResponseEntity<Void> deletarComentario(@PathVariable("idComentario") Long idComentario) {
		comentarioService.deletar(idComentario);
		return ResponseEntity.noContent().build();
	}

}
