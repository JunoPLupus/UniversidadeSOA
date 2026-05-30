package com.ifto.universidade.exception;

public class CamposObrigatoriosException extends RuntimeException {
    public CamposObrigatoriosException(String nomeEntidade) {
        super("Todos os campos de '" + nomeEntidade + "' são obrigatórios.");
    }
}
