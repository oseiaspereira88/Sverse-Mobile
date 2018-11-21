package com.example.oseias.sverse.SQLite.model;

/**
 * Created by IUser on 18/02/2018.
 */

public class DiaDeAula {
    private Integer _id;
    private String diaDaSemana;

    public DiaDeAula(){}

    public DiaDeAula(Integer _id, String diaDaSemana) {
        this._id = _id;
        this.diaDaSemana = diaDaSemana;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }
}
