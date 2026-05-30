package com.ifto.universidade.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidacaoUtil {
    public static boolean isStringInvalida(String valor) {
        return valor == null || valor.isBlank() || valor.trim().equals("?");
    }
}
