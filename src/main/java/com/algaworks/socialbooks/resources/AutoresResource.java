package com.algaworks.socialbooks.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.socialbooks.domain.Autor;
import com.algaworks.socialbooks.domain.DTO.AutorDTO;
import com.algaworks.socialbooks.services.AutoresService;

@RestController
@RequestMapping("/autores")
@CrossOrigin
public class AutoresResource {

	@Autowired
	AutoresService autoresService;

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Autor>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(autoresService.listar());
	}

	@RequestMapping(value = "/dto", method = RequestMethod.GET)
	public ResponseEntity<List<AutorDTO>> listarDto() {
		return ResponseEntity.status(HttpStatus.OK).body(autoresService.listarDto());
	}

	// POST
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Autor autor) {
		autoresService.salvar(autor);
		final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autor.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	// GET
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Autor> buscar(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(autoresService.buscar(id));
	}

	// DELETE
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		autoresService.deletar(id);

		return ResponseEntity.noContent().build();
	}

	// UPDATE
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody Autor autor, @PathVariable("id") Long id) {
		autor.setId(id);
		autoresService.atualizar(autor);
		return ResponseEntity.noContent().build();
	}

}
