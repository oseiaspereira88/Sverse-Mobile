package com.example.oseias.sverse.SQLite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.oseias.sverse.SQLite.model.NotifiModel;

import java.util.ArrayList;

/**
 * Created by IUser on 18/02/2018.
 */

public class NotifiDAO {
    private DataBaseHelper dataBaseaseHelper;
    private SQLiteDatabase database;

    public NotifiDAO(Context ctx){
        dataBaseaseHelper = new DataBaseHelper(ctx);
    }

    private SQLiteDatabase getDatabase(){
        if(database == null){
            database = dataBaseaseHelper.getWritableDatabase();
        }
        return database;
    }

    private NotifiModel criarNotifi(Cursor cursor){

        NotifiModel model = new NotifiModel(
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Notifi._ID)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Notifi.TITULO)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Notifi.TEXTO)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Notifi.TIPO)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Notifi.BG)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Notifi.DATA_DE_CRIACAO)
        ));
        return  model;
    }

    public ArrayList<NotifiModel> returnAllNotificacoes(){
        ArrayList<NotifiModel> notifiModels = new ArrayList<>();
        Cursor cursor = getDatabase().query(
                DataBaseHelper.Notifi.TABELA,
                DataBaseHelper.Notifi.COLUNAS,
                null,
                null,
                null,
                null,
                null);
            while (cursor.moveToNext()) {
                NotifiModel model = criarNotifi(cursor);
                notifiModels.add(model);
            }
        cursor.close();
        return notifiModels;
    }

    public long salvarNotifi(NotifiModel notifiModel){
        ContentValues valores = new ContentValues();
        valores.put(DataBaseHelper.Notifi.TITULO, notifiModel.getTitulo());
        valores.put(DataBaseHelper.Notifi.TEXTO, notifiModel.getTexto());
        valores.put(DataBaseHelper.Notifi.TIPO, notifiModel.getTipo());
        valores.put(DataBaseHelper.Notifi.BG, notifiModel.getBg());
        valores.put(DataBaseHelper.Notifi.DATA_DE_CRIACAO, notifiModel.getData_de_criacao());

        if(notifiModel.get_id() != null){
            return getDatabase().update(DataBaseHelper.Notifi.TABELA, valores,"_id =?", new String[]{notifiModel.get_id().toString()});
        }
        return getDatabase().insert(DataBaseHelper.Notifi.TABELA, null, valores);
    }

    public boolean removerNota(int id){
        return getDatabase().delete(DataBaseHelper.Notas.TABELA, "_id =?",  new String[]{Integer.toString(id)}) > 0;
    }

    public NotifiModel buscarNotaPorId(int id){
        Cursor cursor = getDatabase().query(DataBaseHelper.Notifi.TABELA,
                DataBaseHelper.Notas.COLUNAS, "_id =?",  new String[]{Integer.toString(id)}, null, null, null);
        if(cursor.moveToNext()){
            NotifiModel model = criarNotifi(cursor);
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
