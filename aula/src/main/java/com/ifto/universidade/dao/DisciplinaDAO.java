package com.ifto.universidade.dao;

import com.ifto.universidade.model.Disciplina;

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

    public static Disciplina getDisciplina(String codigo) {
        return disciplinas.stream()
                .filter(d -> d.getCodigo().equals(codigo))
                .findFirst().orElseThrow();
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

    public void cadastrarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }
}
