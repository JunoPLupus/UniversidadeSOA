package com.ifto.universidade.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import static com.ifto.universidade.util.ValidacaoUtil.isStringInvalida;

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
        return new Professor(nome, email, cpf, matricula, especialidade, titulacao);
    }

    @Override
    public boolean isSemelhante(String valor) {
        return this.getNome().contains(valor)
                || this.getEmail().contains(valor)
                || this.getCpf().contains(valor)
                || this.matricula.contains(valor)
                || this.especialidade.contains(valor)
                || this.titulacao.contains(valor);
    }

    @Override
    public boolean isInvalido() {
        return isStringInvalida(this.getNome())
                || isStringInvalida(this.getEmail())
                || isStringInvalida(this.getCpf())
                || isStringInvalida(this.matricula)
                || isStringInvalida(this.especialidade)
                || isStringInvalida(this.titulacao);
    }
}
