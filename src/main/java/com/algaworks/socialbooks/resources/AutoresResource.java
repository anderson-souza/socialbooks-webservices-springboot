package com.algaworks.socialbooks.resources;

import com.algaworks.socialbooks.domain.Autor;
import com.algaworks.socialbooks.services.AutoresService;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping("/autores")
@CrossOrigin
public class AutoresResource {

    @Autowired
    AutoresService autoresService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Autor>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(autoresService.listar());
    }

    // POST
    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody Autor autor) {
        autoresService.salvar(autor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(autor.getId())
            .toUri();
        return ResponseEntity.created(uri).build();
    }

    // GET
    @GetMapping(value = "/{id}")
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
