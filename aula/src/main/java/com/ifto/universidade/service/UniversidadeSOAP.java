package com.ifto.universidade.service;

import com.ifto.universidade.exception.EntidadeDuplicadaException;
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

    //#region Disciplinas
    @WebResult(name = "disciplina")
    public List<Disciplina> listarTodasDisciplinas() {
        return gerarDisciplinaDAO().listarDisciplinas();
    }

    @WebResult(name = "disciplina")
    public List<Disciplina> listarDisciplinasPorNome(@WebParam(name = "nome") String nome) {
        return gerarDisciplinaDAO().listarDisciplinas(nome);
    }

    @WebResult(name = "disciplina")
    public List<Disciplina> listarDisciplinasPorCH(@WebParam(name = "cargaHoraria") int cargaHoraria) {
        return gerarDisciplinaDAO().listarDisciplinas(cargaHoraria);
    }

    public void cadastrarDisciplina(
            @WebParam(name = "disciplina") Disciplina disciplina,
            @WebParam(name = "usuario", header = true) Usuario usuario)
            throws UsuarioNaoAutorizadoException, EntidadeDuplicadaException {
        autenticar(usuario);
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
            throws UsuarioNaoAutorizadoException, EntidadeDuplicadaException {
        autenticar(usuario);
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
        gerarLecionamentoDAO().cadastrarLecionamento(matriculaProf, codDisciplina, semestre, turno);
    }
    //#endregion

    //#region Métodos auxiliares
    private void autenticar(Usuario usuario) throws UsuarioNaoAutorizadoException {
        if (usuario == null
                || !usuario.getLogin().equals("soa")
                || !usuario.getSenha().equals("soa")) {
            throw new UsuarioNaoAutorizadoException("Usuário ou senha inválidos.");
        }
    }
    //#endregion

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/universidade", new UniversidadeSOAP());
        System.out.println("Serviço inicializado com sucesso!");
    }
}
