package com.ifto.universidade.model;

import com.ifto.universidade.util.ValidacaoUtil;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="disciplina")
public class Disciplina {
    private String codigo;
    private String nome;
    private int cargaHoraria;


    public static Disciplina criar(String codigo, String nome, int cargaHoraria) {
        if (ValidacaoUtil.invalido(codigo) || ValidacaoUtil.invalido(nome) || cargaHoraria <= 0) {
            throw new IllegalArgumentException("Todos os campos da disciplina são obrigatórios.");
        }
        return new Disciplina(codigo, nome, cargaHoraria);
    }

    public boolean isSemelhante(String valor) {
        return this.nome.contains(valor);
    }

    public boolean isEqualCH(int valor) {
        return this.cargaHoraria ==  valor;
    }
}
