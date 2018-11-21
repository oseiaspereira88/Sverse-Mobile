package com.example.oseias.sverse.SQLite.model;

/**
 * Created by IUser on 18/02/2018.
 */

public class EnvolvidoComEtapa {
    private Integer idDoTrabalho;
    private Integer idUsuario;

    public EnvolvidoComEtapa(){}

    public EnvolvidoComEtapa(Integer idDoTrabalho, Integer idUsuario) {
        this.idDoTrabalho = idDoTrabalho;
        this.idUsuario = idUsuario;
    }

    public Integer getIdDoTrabalho() {
        return idDoTrabalho;
    }

    public void setIdDoTrabalho(Integer idDoTrabalho) {
        this.idDoTrabalho = idDoTrabalho;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
