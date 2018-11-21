package com.example.oseias.sverse.SQLite.model;

/**
 * Created by IUser on 18/02/2018.
 */

public class NotaModel {
    private Integer _id;
    private String titulo;
    private String nota;
    private String n_emoji;
    private String tag;
    private String dataAlarme;
    private String cor;
    private String data_de_criacao;
    private String data_de_atualizacao;
    private String data_de_completada;
    private Integer id_usuario;

    public NotaModel(){}

    public NotaModel(String titulo, String nota, String n_emoji, String tag, String dataAlarme, String cor, String data_de_criacao, String data_de_atualizacao, String data_de_completada, Integer id_usuario) {
        this.titulo = titulo;
        this.nota = nota;
        this.n_emoji = n_emoji;
        this.tag = tag;
        this.dataAlarme = dataAlarme;
        this.cor = cor;
        this.data_de_criacao = data_de_criacao;
        this.data_de_atualizacao = data_de_atualizacao;
        this.data_de_completada = data_de_completada;
        this.id_usuario = id_usuario;
    }

    public NotaModel(Integer _id, String titulo, String nota, String n_emoji, String tag, String dataAlarme, String cor, String data_de_criacao, String data_de_atualizacao, String data_de_completada, Integer id_usuario) {
        this._id = _id;
        this.titulo = titulo;
        this.nota = nota;
        this.n_emoji = n_emoji;
        this.tag = tag;
        this.dataAlarme = dataAlarme;
        this.cor = cor;
        this.data_de_criacao = data_de_criacao;
        this.data_de_atualizacao = data_de_atualizacao;
        this.data_de_completada = data_de_completada;
        this.id_usuario = id_usuario;
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

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getN_emoji() {return n_emoji;}

    public void setN_emoji(String n_emoji) {this.n_emoji = n_emoji;}

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDataAlarme() {
        return dataAlarme;
    }

    public void setDataAlarme(String dataAlarme) {
        this.dataAlarme = dataAlarme;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getData_de_criacao() {return data_de_criacao;}

    public void setData_de_criacao(String data_de_criacao) {this.data_de_criacao = data_de_criacao;}

    public String getData_de_atualizacao() {return data_de_atualizacao;}

    public void setData_de_atualizacao(String data_de_atualizacao) {this.data_de_atualizacao = data_de_atualizacao;}

    public String getData_de_completada() {return data_de_completada;}

    public void setData_de_completada(String data_de_completada) {this.data_de_completada = data_de_completada;}

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
}
