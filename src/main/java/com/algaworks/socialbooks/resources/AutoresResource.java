package com.algaworks.socialbooks.resources;

import com.algaworks.socialbooks.domain.Autor;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AutoresResource {

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<List<Autor>> listar();

    // POST
    @PostMapping
    ResponseEntity<Void> salvar(@Valid @RequestBody Autor autor);

    // GET
    @GetMapping(value = "/{id}")
    ResponseEntity<Autor> buscar(@PathVariable Long id);

    // DELETE
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> deletar(@PathVariable Long id) throws Exception;

    // UPDATE
    @PutMapping(value = "/{id}")
    ResponseEntity<Void> atualizar(@RequestBody Autor autor, @PathVariable("id") Long id);
}
