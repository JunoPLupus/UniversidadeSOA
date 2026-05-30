package com.ifto.universidade.model;

import com.ifto.universidade.exception.EntidadeNaoEncontradaException;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import static com.ifto.universidade.util.GetObjUtil.getDisciplinaObj;
import static com.ifto.universidade.util.GetObjUtil.getProfessorObj;
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="lecionamento")
public class Lecionamento {

    @XmlElement(required = true)
    private Professor professor;

    @XmlElement(required = true)
    private Disciplina disciplina;

    @XmlElement(required = true)
    private String semestre;

    @XmlElement(required = true)
    private Turno turno;

    public static Lecionamento criar(String matriculaProf, String codDisciplina, String semestre, Turno turno) throws EntidadeNaoEncontradaException {
        return new Lecionamento(
                getProfessorObj(matriculaProf),
                getDisciplinaObj(codDisciplina),
                semestre,
                turno);
    }
    }

    public boolean isSemelhante(String valor) {
        return professor.getMatricula().equalsIgnoreCase(valor)
                || disciplina.getCodigo().equalsIgnoreCase(valor)
                || semestre.equalsIgnoreCase(valor)
                || turno.name().equalsIgnoreCase(valor);
    }
}
