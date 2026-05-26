package com.ifto.universidade.model;

import jakarta.xml.bind.annotation.XmlEnumValue;

public enum Turno {
    @XmlEnumValue("Manhã") MANHA,
    @XmlEnumValue("Noite") NOITE
}
