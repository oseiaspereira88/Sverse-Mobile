package com.example.oseias.sverse.SQLite.model;

/**
 * Created by IUser on 18/02/2018.
 */

public class Atividade {
    private Integer _id;
    private Integer id_materia;
    private String atividade;
    private String assunto;
    private String descricao;
    private String dificudade;
    private String estado;
    private String data_de_criacao;
    private String data_de_completada;
    private String data_de_entrega;
    private Integer id_usuario;

    public Atividade(){}

    public Atividade(Integer id_materia, String atividade, String assunto, String descricao, String dificudade, String estado, String data_de_criacao, String data_de_completada, String data_de_entrega, Integer id_usuario) {
        this.id_materia = id_materia;
        this.atividade = atividade;
        this.assunto = assunto;
        this.descricao = descricao;
        this.dificudade = dificudade;
        this.estado = estado;
        this.data_de_criacao = data_de_criacao;
        this.data_de_completada = data_de_completada;
        this.data_de_entrega = data_de_entrega;
        this.id_usuario = id_usuario;
    }

    public Atividade(Integer _id, Integer id_materia, String atividade, String assunto, String descricao, String dificudade, String estado, String data_de_criacao, String data_de_completada, String data_de_entrega, Integer id_usuario) {
        this._id = _id;
        this.id_materia = id_materia;
        this.atividade = atividade;
        this.assunto = assunto;
        this.descricao = descricao;
        this.dificudade = dificudade;
        this.estado = estado;
        this.data_de_criacao = data_de_criacao;
        this.data_de_completada = data_de_completada;
        this.data_de_entrega = data_de_entrega;
        this.id_usuario = id_usuario;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer getId_materia() {
        return id_materia;
    }

    public void setId_materia(Integer id_materia) {
        this.id_materia = id_materia;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDificudade() {
        return dificudade;
    }

    public void setDificudade(String dificudades) {
        this.dificudade = dificudades;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getData_de_criacao() {
        return data_de_criacao;
    }

    public void setData_de_criacao(String data_de_criacao) {
        this.data_de_criacao = data_de_criacao;
    }

    public String getData_de_completada() {
        return data_de_completada;
    }

    public void setData_de_completada(String data_de_completada) {
        this.data_de_completada = data_de_completada;
    }

    public String getData_de_entrega() {
        return data_de_entrega;
    }

    public void setData_de_entrega(String data_de_entrega) {
        this.data_de_entrega = data_de_entrega;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
}
