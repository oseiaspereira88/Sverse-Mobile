package com.example.oseias.sverse.OthersClass;

import java.util.Date;

public class SubItemArea {
    private String titulo;
    private String descricao;
    private String dataDeCriacao;

    public SubItemArea(String titulo, String descricao, String dataDeCriacao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(String dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }
}
