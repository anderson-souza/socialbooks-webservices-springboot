package com.algaworks.socialbooks.services;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

	public List<Comentario> listarTodosComentarios() {
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
		comentario.setUsuario(retornaUsuarioConectado());// Pega o usuário conectado no momento
		comentario.setLivro(livro);
		comentario.setData(new Date());

		return comentariosRepository.save(comentario);
	}

	public List<Comentario> listarComentariosDeLivro(Long livroId) {
		Livro livro = livrosService.buscar(livroId);

		return livro.getComentarios();
	}

	public void atualizar(Comentario comentario, Long idLivro) {
		verificaExistencia(comentario);
		comentario.setLivro(livrosService.buscar(idLivro));
		comentario.setUsuario(retornaUsuarioConectado());// Pega o usuário conectado no momento
		comentario.setData(new Date());
		comentariosRepository.save(comentario);
	}

	public void deletar(Long id) {
		try {
			comentariosRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ComentarioNaoEncontradoException("Comentário não foi encontrado");
		}

	}

	private void verificaExistencia(Comentario comentario) {
		buscar(comentario.getId());
	}

	private String retornaUsuarioConectado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

}
