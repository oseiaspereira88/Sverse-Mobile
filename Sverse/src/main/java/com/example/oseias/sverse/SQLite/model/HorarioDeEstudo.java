package com.example.oseias.sverse.SQLite.model;

/**
 * Created by IUser on 18/02/2018.
 */

public class HorarioDeEstudo {
    private Integer _id;
    private Integer idUser;

    public HorarioDeEstudo(){}

    public HorarioDeEstudo(Integer _id, Integer idUser) {
        this._id = _id;
        this.idUser = idUser;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
}
