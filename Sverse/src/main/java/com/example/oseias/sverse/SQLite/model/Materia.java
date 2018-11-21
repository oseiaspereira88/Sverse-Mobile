package com.example.oseias.sverse.SQLite.model;

/**
 * Created by IUser on 18/02/2018.
 */

public class Materia {
    private Integer _id;
    private Integer idHorarioDeAula;
    private Integer IdHorarioDeEstudo;
    private String nome;
    private String descricao;
    private String dificuldade;
    private String desempenho;
    private Integer idDoProfessor;
    private Integer id_usuario;

    public Materia(){}

    public Materia(Integer _id, Integer idHorarioDeAula, Integer idHorarioDeEstudo, String nome, String descricao, String dificuldade, String desempenho, Integer idDoProfessor, Integer id_usuario) {
        this._id = _id;
        this.idHorarioDeAula = idHorarioDeAula;
        IdHorarioDeEstudo = idHorarioDeEstudo;
        this.nome = nome;
        this.descricao = descricao;
        this.dificuldade = dificuldade;
        this.desempenho = desempenho;
        this.idDoProfessor = idDoProfessor;
        this.id_usuario = id_usuario;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer getIdHorarioDeAula() {
        return idHorarioDeAula;
    }

    public void setIdHorarioDeAula(Integer idHorarioDeAula) {
        this.idHorarioDeAula = idHorarioDeAula;
    }

    public Integer getIdHorarioDeEstudo() {
        return IdHorarioDeEstudo;
    }

    public void setIdHorarioDeEstudo(Integer idHorarioDeEstudo) {
        IdHorarioDeEstudo = idHorarioDeEstudo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getDesempenho() {
        return desempenho;
    }

    public void setDesempenho(String desempenho) {
        this.desempenho = desempenho;
    }

    public Integer getIdDoProfessor() {
        return idDoProfessor;
    }

    public void setIdDoProfessor(Integer idDoProfessor) {
        this.idDoProfessor = idDoProfessor;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
}
