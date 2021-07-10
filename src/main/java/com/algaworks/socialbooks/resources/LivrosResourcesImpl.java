package com.algaworks.socialbooks.resources;

import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.services.LivrosService;
import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
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
public class LivrosResourcesImpl implements LivrosResources {

    @Autowired
    private LivrosService livrosService;

    // LISTAR
    @Override
    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Livro>> listar() {

        return ResponseEntity.status(HttpStatus.OK).body(livrosService.listar());
    }

    // SALVAR
    @Override
    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody Livro livro) {
        livrosService.salvar(livro);
        // Pega o ID da requisição atual e cria um URI baseando-se neste ID
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(livro.getId())
            .toUri();

        return ResponseEntity.created(uri)
            .build(); // Retorna o status 201 com a URI e o ID do livro criado
    }

    // BUSCAR
    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
        Livro livro = livrosService.buscar(id);

        CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);

        return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(livro);
    }

    // DELETAR
    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {

        livrosService.deletar(id);

        return ResponseEntity.noContent().build(); // Sem conteudo para retornar
    }

    // ATUALIZAR
    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable("id") Long id) {
        livro.setId(id);
        livrosService.atualizar(livro);
        return ResponseEntity.noContent().build();
    }

}
