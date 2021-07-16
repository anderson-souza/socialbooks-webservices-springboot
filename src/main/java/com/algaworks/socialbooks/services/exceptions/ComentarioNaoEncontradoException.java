package com.algaworks.socialbooks.services.exceptions;

public class ComentarioNaoEncontradoException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1869300553614629710L;

    public ComentarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ComentarioNaoEncontradoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

}
