package com.ifto.universidade.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Professor.class})
public abstract sealed class Pessoa permits Professor {

    @XmlElement(required = true)
    private String nome;

    @XmlElement(required = true)
    private String email;

    @XmlElement(required = true)
    private String cpf;

    abstract boolean isSemelhante(String valor);
}
