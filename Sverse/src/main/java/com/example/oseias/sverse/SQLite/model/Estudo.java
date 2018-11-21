package com.example.oseias.sverse.SQLite.model;

/**
 * Created by IUser on 18/02/2018.
 */

public class Estudo {
    private Integer _id;
    private String idDaMateria;
    private String assunto;
    private String importancia;
    private String horaIn;
    private String horaOut;
    private Integer id_usuario;

    public Estudo(){}

    public Estudo(Integer _id, String idDaMateria, String assunto, String importancia, String horaIn, String horaOut, Integer id_usuario) {
        this._id = _id;
        this.idDaMateria = idDaMateria;
        this.assunto = assunto;
        this.importancia = importancia;
        this.horaIn = horaIn;
        this.horaOut = horaOut;
        this.id_usuario = id_usuario;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getIdDaMateria() {
        return idDaMateria;
    }

    public void setIdDaMateria(String idDaMateria) {
        this.idDaMateria = idDaMateria;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getImportancia() {
        return importancia;
    }

    public void setImportancia(String importancia) {
        this.importancia = importancia;
    }

    public String getHoraIn() {
        return horaIn;
    }

    public void setHoraIn(String horaIn) {
        this.horaIn = horaIn;
    }

    public String getHoraOut() {
        return horaOut;
    }

    public void setHoraOut(String horaOut) {
        this.horaOut = horaOut;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
}
