package com.ifto.universidade.dao;

import com.ifto.universidade.model.Disciplina;
import com.ifto.universidade.exception.EntidadeNaoEncontradaException;
import com.ifto.universidade.exception.EntidadeDuplicadaException;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {
    public static final List<Disciplina> disciplinas;

    static {
        disciplinas = new ArrayList<>();

        disciplinas.add(Disciplina.criar(
                "CC001",
                "Programação Orientada a Objetos",
                80
        ));

        disciplinas.add(Disciplina.criar(
                "CC002",
                "Banco de Dados",
                60
        ));

        disciplinas.add(Disciplina.criar(
                "MAT001",
                "Cálculo I",
                80
        ));
    }

    public static DisciplinaDAO gerarDisciplinaDAO() {
        return new DisciplinaDAO();
    }

    public static Disciplina getDisciplina(String codigo) throws EntidadeNaoEncontradaException {
        return disciplinas.stream()
                .filter(d -> d.getCodigo().equals(codigo))
                .findFirst()
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Disciplina com código '" + codigo + "' não encontrada."));
    }

    public List<Disciplina> listarDisciplinas() {
        return disciplinas;
    }

    public List<Disciplina> listarDisciplinas(String valor) {
        return disciplinas.stream()
                .filter(disciplina -> disciplina.isSemelhante(valor))
                .toList();
    }

    public List<Disciplina> listarDisciplinas(int cargaHoraria) {
        return disciplinas.stream()
                .filter(disciplina -> disciplina.isEqualCH(cargaHoraria))
                .toList();
    }

    public void cadastrarDisciplina(Disciplina disciplina) throws EntidadeDuplicadaException {
        boolean codigoJaExiste = disciplinas.stream()
                .anyMatch(d -> d.getCodigo().equalsIgnoreCase(disciplina.getCodigo()));
        if (codigoJaExiste) {
            throw new EntidadeDuplicadaException(
                    "Já existe uma disciplina com o código '" + disciplina.getCodigo() + "'.");
        }
        disciplinas.add(disciplina);
    }
}
