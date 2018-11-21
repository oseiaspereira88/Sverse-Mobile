package com.example.oseias.sverse.SQLite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.oseias.sverse.SQLite.model.NotaModel;

import java.util.ArrayList;

/**
 * Created by IUser on 18/02/2018.
 */

public class NotaDAO {
    private DataBaseHelper dataBaseaseHelper;
    private SQLiteDatabase database;

    public NotaDAO(Context ctx){
        dataBaseaseHelper = new DataBaseHelper(ctx);
    }

    private SQLiteDatabase getDatabase(){
        if(database == null){
            database = dataBaseaseHelper.getWritableDatabase();
        }
        return database;
    }

    private NotaModel criarNota(Cursor cursor){

        NotaModel model = new NotaModel(
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Notas.TITULO)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Notas.NOTA)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Notas.N_EMOJI)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Notas.TAG)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Notas.DATA_ALARME)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Notas.COR)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Notas.DATA_DE_CRIACAO)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Notas.DATA_DE_ATUALIZACAO)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Notas.DATA_DE_COMPLETADA)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Notas.ID_USUARIO))
        );
        return  model;
    }

    private NotaModel montarNotaComId(Cursor cursor){

        NotaModel model = new NotaModel(
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Notas._ID)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Notas.TITULO)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Notas.NOTA)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Notas.N_EMOJI)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Notas.TAG)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Notas.DATA_ALARME)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Notas.COR)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Notas.DATA_DE_CRIACAO)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Notas.DATA_DE_ATUALIZACAO)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Notas.DATA_DE_COMPLETADA)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Notas.ID_USUARIO))
        );
        return  model;
    }

    public ArrayList<NotaModel> returnAllNotas(){
        ArrayList<NotaModel> notaModels = new ArrayList<>();
        Cursor cursor = getDatabase().query(
                DataBaseHelper.Notas.TABELA,
                DataBaseHelper.Notas.COLUNAS_COM_ID,
                null,
                null,
                null,
                null,
                null);
            while (cursor.moveToNext()) {
                NotaModel model = montarNotaComId(cursor);
                notaModels.add(model);
            }
        cursor.close();
        return notaModels;
    }

    public long salvarNota(NotaModel notaModel){
        ContentValues valores = new ContentValues();
        valores.put(DataBaseHelper.Notas.TITULO, notaModel.getTitulo());
        valores.put(DataBaseHelper.Notas.NOTA, notaModel.getNota());
        valores.put(DataBaseHelper.Notas.N_EMOJI, notaModel.getN_emoji());
        valores.put(DataBaseHelper.Notas.TAG, notaModel.getTag());
        valores.put(DataBaseHelper.Notas.DATA_ALARME, notaModel.getDataAlarme());
        valores.put(DataBaseHelper.Notas.COR, notaModel.getCor());
        valores.put(DataBaseHelper.Notas.DATA_DE_CRIACAO, notaModel.getData_de_criacao());
        valores.put(DataBaseHelper.Notas.DATA_DE_ATUALIZACAO, notaModel.getData_de_atualizacao());
        valores.put(DataBaseHelper.Notas.DATA_DE_ATUALIZACAO, notaModel.getData_de_completada());
        valores.put(DataBaseHelper.Notas.ID_USUARIO, notaModel.getId_usuario());

        if(notaModel.get_id() != null){
            return getDatabase().update(DataBaseHelper.Notas.TABELA, valores,"_id =?", new String[]{notaModel.get_id().toString()});
        }
        return getDatabase().insert(DataBaseHelper.Notas.TABELA, null, valores);
    }

    public boolean removerNota(int id){
        return getDatabase().delete(DataBaseHelper.Notas.TABELA, "_id =?",  new String[]{Integer.toString(id)}) > 0;
    }

    public NotaModel buscarNotaPorId(int id){
        Cursor cursor = getDatabase().query(DataBaseHelper.Notas.TABELA,
                DataBaseHelper.Notas.COLUNAS_COM_ID, "_id =?",  new String[]{Integer.toString(id)}, null, null, null);
        if(cursor.moveToNext()){
            NotaModel model = criarNota(cursor);
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
