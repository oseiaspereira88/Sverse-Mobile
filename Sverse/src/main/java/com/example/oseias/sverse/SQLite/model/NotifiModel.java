package com.example.oseias.sverse.SQLite.model;

/**
 * Created by IUser on 18/02/2018.
 */

public class NotifiModel {
    private Integer _id;
    private String titulo;
    private String texto;
    private Integer tipo;
    private String bg;
    private String data_de_criacao;

    public NotifiModel(){}

    public NotifiModel(String titulo, String texto, Integer tipo, String bg, String data_de_criacao) {
        this.titulo = titulo;
        this.texto = texto;
        this.tipo = tipo;
        this.bg = bg;
        this.data_de_criacao = data_de_criacao;
    }

    public NotifiModel(Integer _id, String titulo, String texto, Integer tipo, String bg, String data_de_criacao) {
        this._id = _id;
        this.titulo = titulo;
        this.texto = texto;
        this.tipo = tipo;
        this.bg = bg;
        this.data_de_criacao = data_de_criacao;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public String getData_de_criacao() {
        return data_de_criacao;
    }

    public void setData_de_criacao(String data_de_criacao) {
        this.data_de_criacao = data_de_criacao;
    }
}
