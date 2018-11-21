package com.example.oseias.sverse.SQLite.model;

/**
 * Created by IUser on 18/02/2018.
 */

public class Perfil {
    private Integer _id;
    private Float indiceDeDesempenho;
    private String tagDePerfil;
    private Integer id_usuario;

    public Perfil(){}

    public Perfil(Integer _id, Float indiceDeDesempenho, String tagDePerfil, Integer id_usuario) {
        this._id = _id;
        this.indiceDeDesempenho = indiceDeDesempenho;
        this.tagDePerfil = tagDePerfil;
        this.id_usuario = id_usuario;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Float getIndiceDeDesempenho() {
        return indiceDeDesempenho;
    }

    public void setIndiceDeDesempenho(Float indiceDeDesempenho) {
        this.indiceDeDesempenho = indiceDeDesempenho;
    }

    public String getTagDePerfil() {
        return tagDePerfil;
    }

    public void setTagDePerfil(String tagDePerfil) {
        this.tagDePerfil = tagDePerfil;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
}
