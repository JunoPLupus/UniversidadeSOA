package com.ifto.universidade.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="lecionamento")
public class Lecionamento {
    private Professor professor;
    private Disciplina disciplina;
    private String semestre;
    private Turno turno;

    public static Lecionamento criar(Professor professor, Disciplina disciplina, String semestre, Turno turno) {
        return new Lecionamento(professor, disciplina, semestre, turno);
    }
}
