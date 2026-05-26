package com.ifto.universidade.dao;

import com.ifto.universidade.model.Professor;
import com.ifto.universidade.exception.EntidadeNaoEncontradaException;
import com.ifto.universidade.exception.EntidadeDuplicadaException;
import com.ifto.universidade.util.ValidacaoUtil;

import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    public static final List<Professor> professores;

    static {
        professores = new ArrayList<>();

        professores.add(Professor.criar(
                "Ana Lima",
                "ana.lima@ifto.edu.br",
                "111.111.111-11",
                "MAT001",
                "Matemática",
                "Doutora"
        ));

        professores.add(Professor.criar(
                "Carlos Souza",
                "carlos.souza@ifto.edu.br",
                "222.222.222-22",
                "INF002",
                "Ciência da Computação",
                "Mestre"
        ));

        professores.add(Professor.criar(
                "Fernanda Rocha",
                "fernanda.rocha@ifto.edu.br",
                "333.333.333-33",
                "INF003",
                "Engenharia de Software",
                "Doutora"
        ));
    }

    public static ProfessorDAO gerarProfessorDAO() {
        return new ProfessorDAO();
    }

    public static Professor getProfessor(String matricula) throws EntidadeNaoEncontradaException {
        return professores.stream()
                .filter(p -> p.getMatricula().equals(matricula))
                .findFirst()
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Professor com matrícula '" + matricula + "' não encontrado."));
    }

    public List<Professor> listarProfessores() {
        return professores;
    }

    public List<Professor> listarProfessores(String valor) {
        return professores.stream()
                .filter(professor -> professor.isSemelhante(valor))
                .toList();
    }

    public void cadastrarProfessor(Professor professor) throws EntidadeDuplicadaException {
        if (professor == null
                || ValidacaoUtil.invalido(professor.getNome())
                || ValidacaoUtil.invalido(professor.getEmail())
                || ValidacaoUtil.invalido(professor.getCpf())
                || ValidacaoUtil.invalido(professor.getMatricula())
                || ValidacaoUtil.invalido(professor.getEspecialidade())
                || ValidacaoUtil.invalido(professor.getTitulacao())) {
            throw new IllegalArgumentException("Todos os campos do professor são obrigatórios.");
        }
        boolean matriculaJaExiste = professores.stream()
                .anyMatch(p -> p.getMatricula().equalsIgnoreCase(professor.getMatricula()));
        if (matriculaJaExiste) {
            throw new EntidadeDuplicadaException(
                    "Já existe um professor com a matrícula '" + professor.getMatricula() + "'.");
        }
        professores.add(professor);
    }
}
