package com.ifto.universidade.model;

import com.ifto.universidade.exception.CamposObrigatoriosException;
import com.ifto.universidade.exception.EntidadeNaoEncontradaException;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import static com.ifto.universidade.util.GetObjUtil.getDisciplinaObj;
import static com.ifto.universidade.util.GetObjUtil.getProfessorObj;
import static com.ifto.universidade.util.ValidacaoUtil.isStringInvalida;

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

    public static Lecionamento criar(String matriculaProf, String codDisciplina, String semestre, String turno) throws EntidadeNaoEncontradaException {
        if (isInvalido(matriculaProf, codDisciplina, semestre, turno)) throw new CamposObrigatoriosException("lecionamento");

        return new Lecionamento(
                getProfessorObj(matriculaProf),
                getDisciplinaObj(codDisciplina),
                semestre,
                Turno.fromString(turno));
    }

    public boolean isSemelhante(String valor) {
        return professor.getMatricula().equalsIgnoreCase(valor)
                || disciplina.getCodigo().equalsIgnoreCase(valor)
                || semestre.equalsIgnoreCase(valor)
                || turno.name().equalsIgnoreCase(valor);
    }

    private static boolean isInvalido(String matriculaProf, String codDisciplina, String semestre, String turno) {
        return isStringInvalida(matriculaProf)
                || isStringInvalida(codDisciplina)
                || isStringInvalida(semestre)
                || isStringInvalida(turno);
    }
}
