package com.example.oseias.sverse.SQLite.model;

/**
 * Created by IUser on 18/02/2018.
 */

public class Professor {
    private Integer _id;
    private Integer id_materia;
    private String nome;
    private String email;
    private Integer id_usuario;

    public Professor(){}

    public Professor(Integer _id, Integer id_materia, String nome, String email, Integer id_usuario) {
        this._id = _id;
        this.id_materia = id_materia;
        this.nome = nome;
        this.email = email;
        this.id_usuario = id_usuario;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer getId_materia() {
        return id_materia;
    }

    public void setId_materia(Integer id_materia) {
        this.id_materia = id_materia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
}
