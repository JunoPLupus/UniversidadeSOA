package com.ifto.universidade.model;

import jakarta.xml.bind.annotation.XmlEnumValue;

public enum Turno {
    @XmlEnumValue("Manhã") MANHA,
    @XmlEnumValue("Noite") NOITE;

    public static Turno fromString(String valor) {
        try {
            return valueOf(valor.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Turno inválido: '" + valor + "'. Use MANHA ou NOITE.");
        }
    }
}
