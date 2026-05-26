package com.ifto.universidade.dao;

import com.ifto.universidade.model.Lecionamento;
import com.ifto.universidade.model.Turno;
import com.ifto.universidade.exception.EntidadeNaoEncontradaException;

import java.util.ArrayList;
import java.util.List;

import static com.ifto.universidade.dao.DisciplinaDAO.getDisciplina;
import static com.ifto.universidade.dao.ProfessorDAO.getProfessor;
import static com.ifto.universidade.model.Turno.*;

public class LecionamentoDAO {
    public static final List<Lecionamento> lecionamentos;

    static {
        lecionamentos = new ArrayList<>();
        try {
            lecionamentos.add(Lecionamento.criar(getProfessor("INF002"), getDisciplina("CC001"), "2025.1", MANHA));
            lecionamentos.add(Lecionamento.criar(getProfessor("INF003"), getDisciplina("CC001"), "2025.2", NOITE));
            lecionamentos.add(Lecionamento.criar(getProfessor("INF002"), getDisciplina("CC002"), "2025.2", MANHA));
            lecionamentos.add(Lecionamento.criar(getProfessor("INF003"), getDisciplina("CC002"), "2025.2", NOITE));
            lecionamentos.add(Lecionamento.criar(getProfessor("MAT001"), getDisciplina("MAT001"), "2026.1", MANHA));
            lecionamentos.add(Lecionamento.criar(getProfessor("MAT001"), getDisciplina("MAT001"), "2026.1", NOITE));
        } catch (EntidadeNaoEncontradaException e) {
            throw new RuntimeException("Erro ao inicializar lecionamentos: " + e.getMessage(), e);
        }
    }

    public static LecionamentoDAO gerarLecionamentoDAO() {
        return new LecionamentoDAO();
    }

    public List<Lecionamento> listarLecionamentos() {
        return lecionamentos;
    }

    public List<Lecionamento> listarLecionamentos(String valor) {
        return lecionamentos.stream()
                .filter(l -> l.isSemelhante(valor))
                .toList();
    }

    public void cadastrarLecionamento(String matriculaProf, String codDisciplina, String semestre, String turno) throws EntidadeNaoEncontradaException {
        Lecionamento novoLecionamento = Lecionamento.criar(
                getProfessor(matriculaProf),
                getDisciplina(codDisciplina),
                semestre,
                Turno.valueOf(turno.toUpperCase())
        );
        lecionamentos.add(novoLecionamento);
    }
}
