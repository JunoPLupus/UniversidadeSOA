package com.ifto.universidade.service;

import com.ifto.universidade.exception.EntidadeNaoEncontradaException;
import com.ifto.universidade.exception.UsuarioNaoAutorizadoException;
import com.ifto.universidade.model.Disciplina;
import com.ifto.universidade.model.Lecionamento;
import com.ifto.universidade.model.Professor;
import com.ifto.universidade.model.Usuario;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.ws.Endpoint;

import java.util.List;

import static com.ifto.universidade.dao.DisciplinaDAO.gerarDisciplinaDAO;
import static com.ifto.universidade.dao.LecionamentoDAO.gerarLecionamentoDAO;
import static com.ifto.universidade.dao.ProfessorDAO.gerarProfessorDAO;

@WebService
public class UniversidadeSOAP {

    private void autenticar(Usuario usuario) throws UsuarioNaoAutorizadoException {
        if (usuario == null
                || !usuario.getLogin().equals("soa")
                || !usuario.getSenha().equals("soa")) {
            throw new UsuarioNaoAutorizadoException("Usuário ou senha inválidos.");
        }
    }

    private boolean invalido(String valor) {
        return valor == null || valor.isBlank() || valor.equals("?");
    }

    //#region Disciplinas
    @WebResult(name = "disciplina")
    public List<Disciplina> listarTodasDisciplinas() {
        return gerarDisciplinaDAO().listarDisciplinas();
    }

    @WebResult(name = "disciplina")
    public List<Disciplina> listarDisciplinasPorNome(@WebParam(name = "valor") String valor) {
        return gerarDisciplinaDAO().listarDisciplinas(valor);
    }

    @WebResult(name = "disciplina")
    public List<Disciplina> listarDisciplinasPorCH(@WebParam(name = "cargaHoraria") int valor) {
        return gerarDisciplinaDAO().listarDisciplinas(valor);
    }

    public void cadastrarDisciplina(
            @WebParam(name = "disciplina") Disciplina disciplina,
            @WebParam(name = "usuario", header = true) Usuario usuario)
            throws UsuarioNaoAutorizadoException {
        autenticar(usuario);
        if (disciplina == null
                || invalido(disciplina.getCodigo())
                || invalido(disciplina.getNome())
                || disciplina.getCargaHoraria() <= 0) {
            throw new IllegalArgumentException("Todos os campos da disciplina são obrigatórios.");
        }
        gerarDisciplinaDAO().cadastrarDisciplina(disciplina);
    }
    //#endregion

    //#region Professores
    @WebResult(name = "professor")
    public List<Professor> listarTodosProfessores() {
        return gerarProfessorDAO().listarProfessores();
    }

    @WebResult(name = "professor")
    public List<Professor> listarProfessoresFiltradoDinamico(@WebParam(name = "valor") String valor) {
        return gerarProfessorDAO().listarProfessores(valor);
    }

    public void cadastrarProfessor(
            @WebParam(name = "professor") Professor professor,
            @WebParam(name = "usuario", header = true) Usuario usuario)
            throws UsuarioNaoAutorizadoException {
        autenticar(usuario);
        if (professor == null
                || invalido(professor.getNome())
                || invalido(professor.getEmail())
                || invalido(professor.getCpf())
                || invalido(professor.getMatricula())
                || invalido(professor.getEspecialidade())
                || invalido(professor.getTitulacao())) {
            throw new IllegalArgumentException("Todos os campos do professor são obrigatórios.");
        }
        gerarProfessorDAO().cadastrarProfessor(professor);
    }
    //#endregion

    //#region Lecionamentos
    @WebResult(name = "lecionamento")
    public List<Lecionamento> listarTodosLecionamentos() {
        return gerarLecionamentoDAO().listarLecionamentos();
    }

    @WebResult(name = "lecionamento")
    public List<Lecionamento> listarLecionamentosFiltradoDinamico(@WebParam(name = "valor") String valor) {
        return gerarLecionamentoDAO().listarLecionamentos(valor);
    }

    public void cadastrarLecionamento(
            @WebParam(name = "matriculaProfessor") String matriculaProf,
            @WebParam(name = "codigoDisciplina") String codDisciplina,
            @WebParam(name = "semestre") String semestre,
            @WebParam(name = "turno") String turno,
            @WebParam(name = "usuario", header = true) Usuario usuario)
            throws UsuarioNaoAutorizadoException, EntidadeNaoEncontradaException {
        autenticar(usuario);
        if (invalido(matriculaProf) || invalido(codDisciplina)
                || invalido(semestre) || invalido(turno)) {
            throw new IllegalArgumentException("Todos os campos do lecionamento são obrigatórios.");
        }
        gerarLecionamentoDAO().cadastrarLecionamento(matriculaProf, codDisciplina, semestre, turno);
    }
    //#endregion

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/universidade", new UniversidadeSOAP());
        System.out.println("Serviço inicializado com sucesso!");
    }
}
