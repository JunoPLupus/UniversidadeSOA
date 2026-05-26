package com.ifto.universidade.util;

public class ValidacaoUtil {
    private ValidacaoUtil() {}

    public static boolean invalido(String valor) {
        return valor == null || valor.isBlank() || valor.equals("?");
    }
}
