package com.example.oseias.sverse.SQLite.model;

/**
 * Created by IUser on 18/02/2018.
 */

public class CicloItem {
    private Integer _id;
    private Integer diaDaSemana;
    private Integer hora;
    private Integer minuto;
    private Integer nPomodoros;
    private Integer pomodoroTime;
    private Integer intervaloTime;
    private String observacao;
    private Integer idContainer;
    private Integer idCiclo;
    private Integer idUsuario;
    private boolean isConcluido;

    public CicloItem() {
    }

    public CicloItem(Integer _id, Integer diaDaSemana, Integer hora, Integer minuto, Integer nPomodoros, Integer pomodoroTime, Integer intervaloTime, String observacao, Integer idContainer, Integer idCiclo, Integer idUsuario) {
        this._id = _id;
        this.diaDaSemana = diaDaSemana;
        this.hora = hora;
        this.minuto = minuto;
        this.nPomodoros = nPomodoros;
        this.pomodoroTime = pomodoroTime;
        this.intervaloTime = intervaloTime;
        this.observacao = observacao;
        this.idContainer = idContainer;
        this.idCiclo = idCiclo;
        this.idUsuario = idUsuario;
        isConcluido = false;
    }

    public CicloItem(Integer diaDaSemana, Integer hora, Integer minuto, Integer nPomodoros, Integer pomodoroTime, Integer intervaloTime, String observacao, Integer idContainer, Integer idCiclo, Integer idUsuario) {
        this.diaDaSemana = diaDaSemana;
        this.hora = hora;
        this.minuto = minuto;
        this.nPomodoros = nPomodoros;
        this.pomodoroTime = pomodoroTime;
        this.intervaloTime = intervaloTime;
        this.observacao = observacao;
        this.idContainer = idContainer;
        this.idCiclo = idCiclo;
        this.idUsuario = idUsuario;
        isConcluido = false;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(Integer diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public Integer getHora() {
        return hora;
    }

    public void setHora(Integer hora) {
        this.hora = hora;
    }

    public Integer getMinuto() {
        return minuto;
    }

    public void setMinuto(Integer minuto) {
        this.minuto = minuto;
    }

    public Integer getnPomodoros() {
        return nPomodoros;
    }

    public void setnPomodoros(Integer nPomodoros) {
        this.nPomodoros = nPomodoros;
    }

    public Integer getPomodoroTime() {
        return pomodoroTime;
    }

    public void setPomodoroTime(Integer pomodoroTime) {
        this.pomodoroTime = pomodoroTime;
    }

    public Integer getIntervaloTime() {
        return intervaloTime;
    }

    public void setIntervaloTime(Integer intervaloTime) {
        this.intervaloTime = intervaloTime;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Integer getIdContainer() {
        return idContainer;
    }

    public void setIdContainer(Integer idContainer) {
        this.idContainer = idContainer;
    }

    public Integer getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(Integer idCiclo) {
        this.idCiclo = idCiclo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean isConcluido() {
        return isConcluido;
    }

    public void setConcluido(boolean concluido) {
        isConcluido = concluido;
    }
}
