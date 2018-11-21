package com.example.oseias.sverse.SQLite.model;

/**
 * Created by IUser on 18/02/2018.
 */

public class Aviso {
    private Integer _id;
    private String aviso;
    private Integer id_usuario;

    public Aviso(){}

    public Aviso(String aviso, Integer id_usuario) {
        this.aviso = aviso;
        this.id_usuario = id_usuario;
    }

    public Aviso(Integer _id, String aviso, Integer id_usuario) {
        this._id = _id;
        this.aviso = aviso;
        this.id_usuario = id_usuario;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getAviso() {
        return aviso;
    }

    public void setAviso(String aviso) {
        this.aviso = aviso;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
}
