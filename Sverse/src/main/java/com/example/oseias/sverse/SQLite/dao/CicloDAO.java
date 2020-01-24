package com.example.oseias.sverse.SQLite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.oseias.sverse.SQLite.model.Ciclo;

import java.util.ArrayList;

/**
 * Created by IUser on 18/01/2020.
 */

public class CicloDAO {
    private DataBaseHelper dataBaseaseHelper;
    private SQLiteDatabase database;

    public CicloDAO(Context ctx){
        dataBaseaseHelper = new DataBaseHelper(ctx);
    }

    private SQLiteDatabase getDatabase(){
        if(database == null){
            database = dataBaseaseHelper.getWritableDatabase();
        }
        return database;
    }

    private Ciclo criarCiclo(Cursor cursor){
        Ciclo model = new Ciclo(
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Ciclos.TITULO)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Ciclos.COR)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Ciclos.ID_USUARIO))
        );
        return  model;
    }

    private Ciclo montarCicloComId(Cursor cursor){
        Ciclo model = new Ciclo(
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Ciclos._ID)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Ciclos.TITULO)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Ciclos.COR)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Ciclos.ID_USUARIO))
        );
        return  model;
    }

    public ArrayList<Ciclo> returnAllCiclos(){
        ArrayList<Ciclo> ciclos = new ArrayList<>();
        Cursor cursor = getDatabase().query(
                DataBaseHelper.Ciclos.TABELA,
                DataBaseHelper.Ciclos.COLUNAS_COM_ID,
                null,
                null,
                null,
                null,
                null);
            while (cursor.moveToNext()) {
                Ciclo model = montarCicloComId(cursor);
                ciclos.add(model);
            }
        cursor.close();
        return ciclos;
    }

    public long salvarCiclo(Ciclo ciclo){
        ContentValues valores = new ContentValues();
        valores.put(DataBaseHelper.Ciclos.TITULO, ciclo.getTitulo());
        valores.put(DataBaseHelper.Ciclos.COR, ciclo.getCor());
        valores.put(DataBaseHelper.Ciclos.ID_USUARIO, ciclo.getId_usuario());

        if(ciclo.get_id() != null){
            return getDatabase().update(DataBaseHelper.Ciclos.TABELA, valores,"_id =?", new String[]{ciclo.get_id().toString()});
        }
        return getDatabase().insert(DataBaseHelper.Ciclos.TABELA, null, valores);
    }

    public boolean removerCiclo(int id){
        return getDatabase().delete(DataBaseHelper.Ciclos.TABELA, "_id =?",  new String[]{Integer.toString(id)}) > 0;
    }

    public Ciclo buscarCicloPorId(int id){
        Cursor cursor = getDatabase().query(DataBaseHelper.Ciclos.TABELA,
                DataBaseHelper.Ciclos.COLUNAS_COM_ID, "_id =?",  new String[]{Integer.toString(id)}, null, null, null);
        if(cursor.moveToNext()){
            Ciclo model = criarCiclo(cursor);
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
