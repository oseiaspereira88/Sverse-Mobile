package com.example.oseias.sverse.SQLite.model;

/**
 * Created by IUser on 18/02/2018.
 */

public class Configuracao {
    //Variaveis de Configurações
    private Integer _id;
    private Integer valorConfig;

    //Variaveis Chaves de Acesso as configurações:
    public static final int IS_PRIMARY_EXIBITION = 0;
    public static final int IS_LOGIN_PERSISTENT = 1;              // 0-> FALSE, 1-> TRUE
    public static final int ID_LOGIN = 2;
    public static final int LAST_INDEX_FRAGMENT = 3;
    public static final int LAST_CONTAINERS_LIST_MODE = 4;     //0 _> GrideMode, e 1 _> ListMode

    public Configuracao(int idConfig, int valorConfig){
        this._id = idConfig;
        this.valorConfig = valorConfig;
    }

    public Configuracao(){}

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer getvalorConfig() {
        return valorConfig;
    }

    public void setvalorConfig(Integer valorConfig) {
        this.valorConfig = valorConfig;
    }
}
