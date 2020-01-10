package com.example.oseias.sverse.SQLite.model;

/**
 * Created by IUser on 18/02/2018.
 */

public class ItemDeEstudo {
    private Integer _id;
    private Integer diaDaSemana;
    private Integer hora;
    private Integer minuto;
    private Integer nPomodoros;
    private Integer pomodoroTime;
    private Integer intervaloTime;
    private Integer idContainer;
    private Integer idUsuario;
    private boolean isConcluido;

    public ItemDeEstudo(){}

    public ItemDeEstudo(Integer _id, Integer diaDaSemana, Integer hora, Integer minuto, Integer nPomodoros, Integer pomodoroTime, Integer intervaloTime, Integer idContainer, Integer idUsuario) {
        this._id = _id;
        this.diaDaSemana = diaDaSemana;
        this.hora = hora;
        this.minuto = minuto;
        this.nPomodoros = nPomodoros;
        this.pomodoroTime = pomodoroTime;
        this.intervaloTime = intervaloTime;
        this.idContainer = idContainer;
        this.idUsuario = idUsuario;
        isConcluido = false;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer getDia() {
        return diaDaSemana;
    }

    public void setDia(Integer diaDaSemana) {
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

    public Integer getIdContainer() {
        return idContainer;
    }

    public void setIdContainer(Integer idContainer) {
        this.idContainer = idContainer;
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
