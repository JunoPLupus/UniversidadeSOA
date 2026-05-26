package com.ifto.universidade.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="professor")
public non-sealed class Professor extends Pessoa {
    private String matricula;
    private String especialidade;
    private String titulacao;

    private Professor(String nome, String email, String cpf, String matricula, String especialidade, String titulacao) {
        super(nome, email, cpf);
        this.matricula = matricula;
        this.especialidade = especialidade;
        this.titulacao = titulacao;
    }

    public static Professor criar(String nome, String email, String cpf, String matricula, String especialidade, String titulacao) {
        return new Professor(nome, email, cpf, matricula, especialidade, titulacao);
    }

    public boolean isSemelhante(String valor) {
        return this.getNome().contains(valor)
                || this.getMatricula().contains(valor)
                || this.especialidade.contains(valor)
                || this.titulacao.contains(valor)
                || this.matricula.contains(valor);
    }
}
