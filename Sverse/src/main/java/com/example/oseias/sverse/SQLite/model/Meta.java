package com.example.oseias.sverse.SQLite.model;

/**
 * Created by IUser on 18/02/2018.
 */

public class Meta {
    private Integer _id;
    private Integer idObjetivo;
    private String meta;
    private String dataIn;
    private String dataOut;
    private String estado;
    private Integer id_usuario;

    public Meta(){}

    public Meta(Integer _id, Integer idObjetivo, String meta, String dataIn, String dataOut, String estado, Integer id_usuario) {
        this._id = _id;
        this.idObjetivo = idObjetivo;
        this.meta = meta;
        this.dataIn = dataIn;
        this.dataOut = dataOut;
        this.estado = estado;
        this.id_usuario = id_usuario;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer getIdObjetivo() {
        return idObjetivo;
    }

    public void setIdObjetivo(Integer idObjetivo) {
        this.idObjetivo = idObjetivo;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getDataIn() {
        return dataIn;
    }

    public void setDataIn(String dataIn) {
        this.dataIn = dataIn;
    }

    public String getDataOut() {
        return dataOut;
    }

    public void setDataOut(String dataOut) {
        this.dataOut = dataOut;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
}
