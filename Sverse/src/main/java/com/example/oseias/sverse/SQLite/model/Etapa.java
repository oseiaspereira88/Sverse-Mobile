package com.example.oseias.sverse.SQLite.model;

/**
 * Created by IUser on 18/02/2018.
 */

public class Etapa {
    private Integer _id;
    private Integer idDoTrabalho;
    private String titulo;
    private Integer nTopicos;
    private String descricao;
    private String estado;
    private String progresso;

    public Etapa(){}


    public Etapa(Integer _id, Integer idDoTrabalho, String titulo, Integer nTopicos, String descricao, String estado, String progresso) {
        this._id = _id;
        this.idDoTrabalho = idDoTrabalho;
        this.titulo = titulo;
        this.nTopicos = nTopicos;
        this.descricao = descricao;
        this.estado = estado;
        this.progresso = progresso;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer getIdDoTrabalho() {
        return idDoTrabalho;
    }

    public void setIdDoTrabalho(Integer idDoTrabalho) {
        this.idDoTrabalho = idDoTrabalho;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getnTopicos() {
        return nTopicos;
    }

    public void setnTopicos(Integer nTopicos) {
        this.nTopicos = nTopicos;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getProgresso() {
        return progresso;
    }

    public void setProgresso(String progresso) {
        this.progresso = progresso;
    }
}
