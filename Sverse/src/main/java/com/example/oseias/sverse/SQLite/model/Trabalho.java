package com.example.oseias.sverse.SQLite.model;

/**
 * Created by IUser on 18/02/2018.
 */

public class Trabalho {
    private Integer _id;
    private Integer idMateria;
    private String titulo;
    private String assunto;
    private String descricao;
    private String estado;
    private String progresso;

    public Trabalho(){}

    public Trabalho(Integer _id, Integer idMateria, String titulo, String assunto, String descricao, String estado, String progresso) {
        this._id = _id;
        this.idMateria = idMateria;
        this.titulo = titulo;
        this.assunto = assunto;
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

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
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
