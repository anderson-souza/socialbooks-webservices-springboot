package com.algaworks.socialbooks.services;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.socialbooks.domain.Comentario;
import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.ComentariosRepository;
import com.algaworks.socialbooks.services.exceptions.ComentarioNaoEncontradoException;

@Service
public class ComentariosService {

	@Autowired
	ComentariosRepository comentariosRepository;

	@Autowired
	LivrosService livrosService;

	public List<Comentario> listar() {
		return comentariosRepository.findAll();
	}

	public Comentario buscar(Long id) {

		Comentario comentario;

		try {
			comentario = comentariosRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new ComentarioNaoEncontradoException("Comentário não encontrado");
		}

		return comentario;
	}

	public Comentario salvarComentario(Long livroId, Comentario comentario) {
		Livro livro = livrosService.buscar(livroId);

		comentario.setLivro(livro);
		comentario.setData(new Date());

		return comentariosRepository.save(comentario);
	}

	public List<Comentario> listarComentarios(Long livroId) {
		Livro livro = livrosService.buscar(livroId);

		return livro.getComentarios();
	}

}
