package com.example.oseias.sverse.SQLite.model;

/**
 * Created by IUser on 18/02/2018.
 */

public class Topico {
    private Integer _id;
    private Integer idDaEtapa;
    private String titulo;
    private String estado;

    public Topico(){}


    public Topico(Integer _id, Integer idDaEtapa, String titulo, String estado) {
        this._id = _id;
        this.idDaEtapa = idDaEtapa;
        this.titulo = titulo;
        this.estado = estado;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer getIdDaEtapa() {
        return idDaEtapa;
    }

    public void setIdDaEtapa(Integer idDaEtapa) {
        this.idDaEtapa = idDaEtapa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
