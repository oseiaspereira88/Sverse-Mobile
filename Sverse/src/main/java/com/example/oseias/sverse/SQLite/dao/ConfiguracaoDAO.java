package com.example.oseias.sverse.SQLite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.oseias.sverse.SQLite.model.Configuracao;

import java.util.ArrayList;

/**
 * Created by IUser on 18/02/2018.
 */

public class ConfiguracaoDAO {
    private DataBaseHelper dataBaseaseHelper;
    private SQLiteDatabase database;

    public ConfiguracaoDAO(Context ctx){
        dataBaseaseHelper = new DataBaseHelper(ctx);
    }

    private SQLiteDatabase getDatabase(){
        if(database == null){
            database = dataBaseaseHelper.getWritableDatabase();
        }
        return database;
    }

    private Configuracao montarConfig(Cursor cursor){
        Configuracao config = new Configuracao(
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.ConfiguracoesGerais._ID)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.ConfiguracoesGerais.VALOR_CONFIG))
        );
        return  config;
    }

    public ArrayList<Configuracao> listarConfigs(){
        Cursor cursor = getDatabase().query(DataBaseHelper.ConfiguracoesGerais.TABELA,
                DataBaseHelper.ConfiguracoesGerais.COLUNAS, null, null, null, null, null);
        ArrayList<Configuracao> configuracoes = new ArrayList<>();
        while(cursor.moveToNext()){
            Configuracao config = montarConfig(cursor);
            configuracoes.add(config);
        }
        cursor.close();

        return configuracoes;
    }

    public long addConfig(Configuracao configuracao){
        ContentValues valores = new ContentValues();
        valores.put(DataBaseHelper.ConfiguracoesGerais._ID, configuracao.get_id());
        valores.put(DataBaseHelper.ConfiguracoesGerais.VALOR_CONFIG, configuracao.getvalorConfig());
        return getDatabase().insert(DataBaseHelper.ConfiguracoesGerais.TABELA, null, valores);
    }

    public long atualizarConfig(Configuracao configuracao){
        ContentValues valores = new ContentValues();
        valores.put(DataBaseHelper.ConfiguracoesGerais._ID, configuracao.get_id());
        valores.put(DataBaseHelper.ConfiguracoesGerais.VALOR_CONFIG, configuracao.getvalorConfig());
        return getDatabase().update(DataBaseHelper.ConfiguracoesGerais.TABELA, valores,"_id =?", new String[]{configuracao.get_id().toString()});

    }



    public Configuracao buscarConfig(int idConfig){
        Cursor cursor = getDatabase().query(DataBaseHelper.ConfiguracoesGerais.TABELA,
                DataBaseHelper.ConfiguracoesGerais.COLUNAS, "_id =?",  new String[]{"" + idConfig}, null, null, null);
        if(cursor.moveToNext()){
            Configuracao model = montarConfig(cursor);
            cursor.close();
            return  model;
        }
        return null;
    }

    public void fecharConexao(){
        database.close();
        database = null;
    }
}
