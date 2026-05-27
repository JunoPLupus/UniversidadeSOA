package com.ifto.universidade.model;

import com.ifto.universidade.util.ValidacaoUtil;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="professor")
public non-sealed class Professor extends Pessoa {

    @XmlElement(required = true)
    private String matricula;

    @XmlElement(required = true)
    private String especialidade;

    @XmlElement(required = true)
    private String titulacao;

    private Professor(String nome, String email, String cpf, String matricula, String especialidade, String titulacao) {
        super(nome, email, cpf);
        this.matricula = matricula;
        this.especialidade = especialidade;
        this.titulacao = titulacao;
    }

    public static Professor criar(String nome, String email, String cpf, String matricula, String especialidade, String titulacao) {
        if (ValidacaoUtil.invalido(nome) || ValidacaoUtil.invalido(email) || ValidacaoUtil.invalido(cpf)
                || ValidacaoUtil.invalido(matricula) || ValidacaoUtil.invalido(especialidade) || ValidacaoUtil.invalido(titulacao)) {
            throw new IllegalArgumentException("Todos os campos do professor são obrigatórios.");
        }
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
