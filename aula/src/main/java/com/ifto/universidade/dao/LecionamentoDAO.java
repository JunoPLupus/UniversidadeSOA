package com.ifto.universidade.dao;

import com.ifto.universidade.model.Lecionamento;
import com.ifto.universidade.exception.EntidadeNaoEncontradaException;

import java.util.ArrayList;
import java.util.List;

import static com.ifto.universidade.model.Turno.*;

public class LecionamentoDAO {
    public static final List<Lecionamento> lecionamentos;

    static {
        lecionamentos = new ArrayList<>();
        try {
            lecionamentos.add(Lecionamento.criar(
                    "INF002",
                    "CC001",
                    "2025.1",
                    MANHA));
            lecionamentos.add(Lecionamento.criar(
                    "INF003",
                    "CC001",
                    "2025.2",
                    NOITE));
            lecionamentos.add(Lecionamento.criar(
                    "INF002",
                    "CC002",
                    "2025.2",
                    MANHA));
            lecionamentos.add(Lecionamento.criar(
                    "INF003",
                    "CC002",
                    "2025.2",
                    NOITE));
            lecionamentos.add(Lecionamento.criar(
                    "MAT001",
                    "MAT001",
                    "2026.1",
                    MANHA));
            lecionamentos.add(Lecionamento.criar(
                    "MAT001",
                    "MAT001",
                    "2026.1",
                    NOITE));
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
                matriculaProf,
                codDisciplina,
                semestre,
                turno
        );
        lecionamentos.add(novoLecionamento);
    }
}
