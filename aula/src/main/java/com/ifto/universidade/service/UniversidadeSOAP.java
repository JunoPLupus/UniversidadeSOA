package com.ifto.universidade.service;

import com.ifto.universidade.model.Disciplina;
import com.ifto.universidade.model.Lecionamento;
import com.ifto.universidade.model.Professor;
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
    public List<Disciplina> listarDisciplinasPorNome(String valor) {
        return gerarDisciplinaDAO().listarDisciplinas(valor);
    }

    @WebResult(name = "disciplina")
    public List<Disciplina> listarDisciplinasPorCH(int valor) {
        return gerarDisciplinaDAO().listarDisciplinas(valor);
    }

    public void cadastrarDisciplina(@WebParam(name = "disciplina") Disciplina disciplina) {
        gerarDisciplinaDAO().cadastrarDisciplina(disciplina);
    }
    //#endregion

    //#region Professores
    @WebResult(name = "professor")
    public List<Professor> listarTodosProfessores() {
        return gerarProfessorDAO().listarProfessores();
    }

    @WebResult(name = "professor")
    public List<Professor> listarProfessoresFiltradoDinamico(String valor) {
        return gerarProfessorDAO().listarProfessores(valor);
    }

    public void cadastrarProfessor(@WebParam(name = "professor") Professor professor) {
        gerarProfessorDAO().cadastrarProfessor(professor);
    }
    //#endregion

    //#region Lecionamentos
    @WebResult(name = "lecionamento")
    public List<Lecionamento> listarTodosLecionamentos(){
        return gerarLecionamentoDAO().listarLecionamentos();
    }

    public void cadastrarLecionamento(
            @WebParam(name = "matriculaProfessor") String matriculaProf,
            @WebParam(name = "codigoDisciplina") String codDisciplina,
            @WebParam(name = "semestre") String semestre,
            @WebParam(name = "turno") String turno) {
        gerarLecionamentoDAO().cadastrarLecionamento(matriculaProf, codDisciplina, semestre, turno);
    }
    //#endregion

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/universidade", new UniversidadeSOAP());
        System.out.println("Serviço inicializado com sucesso!");
    }
}
