package com.ifto.universidade.exception;

public class EntidadeNaoEncontradaException extends Exception {
    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
    public EntidadeNaoEncontradaException() {
        super("Entidade não encontrada!");
    }
}
