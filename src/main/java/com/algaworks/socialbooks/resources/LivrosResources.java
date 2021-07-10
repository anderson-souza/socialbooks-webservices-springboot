package com.algaworks.socialbooks.resources;

import com.algaworks.socialbooks.domain.Livro;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface LivrosResources {

    // LISTAR
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<Livro>> listar();

    // SALVAR
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Void> salvar(@Valid @RequestBody Livro livro);

    // BUSCAR
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<?> buscar(@PathVariable("id") Long id);

    // DELETAR
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Void> deletar(@PathVariable("id") Long id);

    // ATUALIZAR
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable("id") Long id);
}
