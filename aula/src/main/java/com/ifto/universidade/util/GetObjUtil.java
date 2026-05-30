package com.ifto.universidade.util;

import com.ifto.universidade.exception.EntidadeNaoEncontradaException;
import com.ifto.universidade.model.Disciplina;
import com.ifto.universidade.model.Professor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.ifto.universidade.dao.DisciplinaDAO.disciplinas;
import static com.ifto.universidade.dao.ProfessorDAO.professores;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GetObjUtil {

    public static Professor getProfessorObj(String matricula) throws EntidadeNaoEncontradaException {
        return professores.stream()
                .filter(p -> p.getMatricula().equals(matricula))
                .findFirst()
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Professor com matrícula '" + matricula + "' não encontrado."));
    }

    public static Disciplina getDisciplinaObj(String codigo) throws EntidadeNaoEncontradaException {
        return disciplinas.stream()
                .filter(d -> d.getCodigo().equals(codigo))
                .findFirst()
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Disciplina com código '" + codigo + "' não encontrada."));
    }
}
