package com.example.oseias.sverse.SQLite.model;

/**
 * Created by IUser on 18/02/2018.
 */

public class Envio {
    private Integer _id;
    private Integer idEmissor;
    private Integer idDestino;
    private String tipo;
    private String conteudo;

    public Envio(){}

    public Envio(Integer _id, Integer idEmissor, Integer idDestino, String tipo, String conteudo) {
        this._id = _id;
        this.idEmissor = idEmissor;
        this.idDestino = idDestino;
        this.tipo = tipo;
        this.conteudo = conteudo;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer getIdEmissor() {
        return idEmissor;
    }

    public void setIdEmissor(Integer idEmissor) {
        this.idEmissor = idEmissor;
    }

    public Integer getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(Integer idDestino) {
        this.idDestino = idDestino;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}
