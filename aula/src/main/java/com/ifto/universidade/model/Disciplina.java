package com.ifto.universidade.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import static com.ifto.universidade.util.ValidacaoUtil.isStringInvalida;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="disciplina")
public class Disciplina {

    @XmlElement(required = true)
    private String codigo;

    @XmlElement(required = true)
    private String nome;

    @XmlElement(required = true)
    private int cargaHoraria;

    public static Disciplina criar(String codigo, String nome, int cargaHoraria) {
        return new Disciplina(codigo, nome, cargaHoraria);
    }

    public boolean isSemelhante(String valor) {
        return this.nome.contains(valor);
    }

    public boolean isEqualCH(int valor) {
        return this.cargaHoraria ==  valor;
    }

    public boolean isInvalido() {
        return isStringInvalida(this.codigo)
                || isStringInvalida(this.nome)
                || this.cargaHoraria <= 0;
    }
}
