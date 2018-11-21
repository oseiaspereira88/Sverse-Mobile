package com.example.oseias.sverse.SQLite.model;

/**
 * Created by IUser on 18/02/2018.
 */

public class Evento {
    private Integer _id;
    private Integer id_da_materia;
    private String titulo;
    private String assunto;
    private String descricao;
    private String data_de_evento;
    private Integer id_usuario;

    public Evento(){}

    public Evento(Integer id_da_materia, String titulo, String assunto, String descricao, String data_de_evento, Integer id_usuario) {

        this.id_da_materia = id_da_materia;
        this.titulo = titulo;
        this.assunto = assunto;
        this.descricao = descricao;
        this.data_de_evento = data_de_evento;
        this.id_usuario = id_usuario;
    }

    public Evento(Integer _id, Integer id_da_materia, String titulo, String assunto, String descricao, String data_de_evento, Integer id_usuario) {
        this._id = _id;
        this.id_da_materia = id_da_materia;
        this.titulo = titulo;
        this.assunto = assunto;
        this.descricao = descricao;
        this.data_de_evento = data_de_evento;
        this.id_usuario = id_usuario;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer getIdDaMateria() {
        return id_da_materia;
    }

    public void setIdDaMateria(Integer id_da_materia) {
        this.id_da_materia = id_da_materia;
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

    public String getDataDeEvento() {
        return data_de_evento;
    }

    public void setDataDeEvento(String data_de_evento) {
        this.data_de_evento = data_de_evento;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
}
