package com.example.oseias.sverse.SQLite.model;

/**
 * Created by IUser on 18/02/2018.
 */

public class Ciclo {
    private Integer _id;
    private String titulo;
    private Integer id_usuario;

    public Ciclo(){}

    public Ciclo(String titulo, Integer id_usuario) {
        this.titulo = titulo;
        this.id_usuario = id_usuario;
    }

    public Ciclo(Integer _id, String titulo, Integer id_usuario) {
        this._id = _id;
        this.titulo = titulo;
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

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
}
