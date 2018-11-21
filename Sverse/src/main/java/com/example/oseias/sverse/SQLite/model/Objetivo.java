package com.example.oseias.sverse.SQLite.model;

/**
 * Created by IUser on 18/02/2018.
 */

public class Objetivo {
    private Integer _id;
    private String objetivo;
    private String progresso;
    private boolean isAtivo;
    private String espectativa;
    private Integer id_usuario;

    public Objetivo(){}

    public Objetivo(Integer _id, String objetivo, String progresso, boolean isAtivo, String espectativa, Integer id_usuario) {
        this._id = _id;
        this.objetivo = objetivo;
        this.progresso = progresso;
        this.isAtivo = isAtivo;
        this.espectativa = espectativa;
        this.id_usuario = id_usuario;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getProgresso() {
        return progresso;
    }

    public void setProgresso(String progresso) {
        this.progresso = progresso;
    }

    public boolean isAtivo() {
        return isAtivo;
    }

    public void setAtivo(boolean ativo) {
        isAtivo = ativo;
    }

    public String getEspectativa() {
        return espectativa;
    }

    public void setEspectativa(String espectativa) {
        this.espectativa = espectativa;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
}

