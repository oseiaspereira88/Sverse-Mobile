package com.example.oseias.sverse.SQLite.model;

/**
 * Created by IUser on 18/02/2018.
 */

public class Amigo {
    private Integer _id;
    private String nome;
    private boolean isOnline;
    private Integer id_usuario;

    public Amigo(){}

    public Amigo(Integer _id, String nome, boolean isOnline, Integer id_usuario) {
        this._id = _id;
        this.nome = nome;
        this.isOnline = isOnline;
        this.id_usuario = id_usuario;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
}
