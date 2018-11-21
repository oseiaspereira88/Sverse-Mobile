package com.example.oseias.sverse.SQLite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.oseias.sverse.SQLite.model.Aviso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IUser on 18/02/2018.
 */

public class AvisoDAO {
    private DataBaseHelper dataBaseaseHelper;
    private SQLiteDatabase database;

    public AvisoDAO(Context ctx){
        dataBaseaseHelper = new DataBaseHelper(ctx);
    }

    private SQLiteDatabase getDatabase(){
        if(database == null){
            database = dataBaseaseHelper.getWritableDatabase();
        }
        return database;
    }

    private Aviso criarAviso(Cursor cursor){
        Aviso model = new Aviso(
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Avisos.AVISO)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Avisos.ID_USUARIO))
        );
        return  model;
    }

    public List<Aviso> listarAvisos(){
        Cursor cursor = getDatabase().query(DataBaseHelper.Avisos.TABELA,
                DataBaseHelper.Avisos.COLUNAS, null, null, null, null, null);
        List<Aviso> avisos = new ArrayList<>();
        while(cursor.moveToNext()){
            Aviso model = criarAviso(cursor);
            avisos.add(model);
        }
        cursor.close();

        return avisos;
    }

    public long salvarAviso(Aviso aviso){
        ContentValues valores = new ContentValues();
        valores.put(DataBaseHelper.Avisos.AVISO, aviso.getAviso());
        valores.put(DataBaseHelper.Avisos.ID_USUARIO, aviso.getId_usuario());

        if(aviso.get_id() != null){
            return getDatabase().update(DataBaseHelper.Avisos.TABELA, valores,"_id =?", new String[]{aviso.get_id().toString()});
        }
        return getDatabase().insert(DataBaseHelper.Avisos.TABELA, null, valores);
    }

    public boolean removerAviso(int id){
        return getDatabase().delete(DataBaseHelper.Avisos.TABELA, "_id =?",  new String[]{Integer.toString(id)}) > 0;
    }

    public Aviso buscarAvisoPorId(int id){
        Cursor cursor = getDatabase().query(DataBaseHelper.Avisos.TABELA,
                DataBaseHelper.Avisos.COLUNAS, "_id = ?",  new String[]{Integer.toString(id)}, null, null, null);
        if(cursor.moveToNext()){
            Aviso model = criarAviso(cursor);
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
