package com.example.oseias.sverse.SQLite.model;

import com.example.oseias.sverse.OthersClass.SubItemArea;

import java.util.ArrayList;

/**
 * Created by IUser on 18/02/2018.
 */

public class Objetivo {
    private Integer _id;
    private String titulo;
    private String descricao;
    private Integer indexOrdem;
    private Integer progressoPorCento;
    private String estado;
    private ArrayList<SubItemArea> etapas;

    public Objetivo() {
    }

    public Objetivo(Integer _id, String titulo, String descricao, Integer indexOrdem, Integer progressoPorCento, String estado) {
        this._id = _id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.indexOrdem = indexOrdem;
        this.progressoPorCento = progressoPorCento;
        this.estado = estado;
    }

    public Objetivo(Integer _id, String titulo, String descricao, Integer indexOrdem, Integer progressoPorCento, String estado, ArrayList<SubItemArea> etapas) {
        this._id = _id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.indexOrdem = indexOrdem;
        this.progressoPorCento = progressoPorCento;
        this.estado = estado;
        this.etapas = etapas;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIndexOrdem() {
        return indexOrdem;
    }

    public void setIndexOrdem(Integer indexOrdem) {
        this.indexOrdem = indexOrdem;
    }

    public Integer getProgressoPorCento() {
        return progressoPorCento;
    }

    public void setProgressoPorCento(Integer progressoPorCento) {
        this.progressoPorCento = progressoPorCento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<SubItemArea> getEtapas() {
        return etapas;
    }

    public void setEtapas(ArrayList<SubItemArea> etapas) {
        this.etapas = etapas;
    }
}