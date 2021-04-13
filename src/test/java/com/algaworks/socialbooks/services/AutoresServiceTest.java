package com.algaworks.socialbooks.services;

import static org.assertj.core.api.Assertions.assertThat;

import com.algaworks.socialbooks.domain.Autor;
import com.algaworks.socialbooks.repository.AutoresRepository;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AutoresServiceTest {

    @Autowired
    AutoresRepository autoresRepository;

    @Autowired
    AutoresService autoresService;

    Autor autor;

    @BeforeEach
    void setUp() {
        autor = new Autor();
        autor.setId(1L);
        autor.setNome("Anderson");
        autor.setNascimento(new Date());
        autor.setNacionalidade("Brasileiro");
    }

    @Test
    void listar() {
    }

    @Test
    void listarDto() {
    }

    @Test
    void listarSimpleDTO() {
    }

    @Test
    void salvar() {
        salvarAutor();
        assertThat(autoresRepository.findAll()).isNotNull().hasSize(1);
    }


    @Test
    void buscar() {
        salvarAutor();
        Autor autor = autoresService.buscar(1L);
        assertThat(autor).isNotNull();
    }

    @Test
    void deletar() {
        salvarAutor();
        autoresService.deletar(1L);
        assertThat(autoresRepository.findAll()).isEmpty();
    }

    @Test
    void atualizar_expect_exception() {
        salvarAutor();
        Autor autor = autoresService.buscar(1L);
        autor.setNome(null);
        Assertions.assertThrows(Exception.class, () -> autoresService.atualizar(autor));

    }

    private void salvarAutor() {
        autoresRepository.save(autor);
    }
}