package com.example.oseias.sverse.SQLite.model;

/**
 * Created by IUser on 18/02/2018.
 */

public class Aula {
    private Integer _id;
    private Integer idDaMateria;
    private String horaIn;
    private String horaOut;

    public Aula(){}

    public Aula(Integer _id, Integer idDaMateria, String horaIn, String horaOut) {
        this._id = _id;
        this.idDaMateria = idDaMateria;
        this.horaIn = horaIn;
        this.horaOut = horaOut;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer getIdDaMateria() {
        return idDaMateria;
    }

    public void setIdDaMateria(Integer idDaMateria) {
        this.idDaMateria = idDaMateria;
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
}
