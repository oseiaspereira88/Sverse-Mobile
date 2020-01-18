package com.example.oseias.sverse.SQLite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.oseias.sverse.SQLite.model.CicloItem;

import java.util.ArrayList;

/**
 * Created by Oséias Pereira on 18/01/2020.
 */

public class CicloItemDAO {
    private DataBaseHelper dataBaseaseHelper;
    private SQLiteDatabase database;

    public CicloItemDAO(Context ctx){
        dataBaseaseHelper = new DataBaseHelper(ctx);
    }

    private SQLiteDatabase getDatabase(){
        if(database == null){
            database = dataBaseaseHelper.getWritableDatabase();
        }
        return database;
    }

    private CicloItem criarCicloItem(Cursor cursor){
        CicloItem model = new CicloItem(
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.CicloItens.DIA_DA_SEMANA)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.CicloItens.HORA)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.CicloItens.MINUTO)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.CicloItens.N_POMODOROS)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.CicloItens.POMODORO_TIME)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.CicloItens.INTERVALO_TIME)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.CicloItens.OBSERVACAO)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.CicloItens.ID_CONTAINER)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.CicloItens.ID_CICLO)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.CicloItens.ID_USUARIO))
        );
        return  model;
    }

    private CicloItem montarCicloItemComId(Cursor cursor){
        CicloItem model = new CicloItem(
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.CicloItens._ID)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.CicloItens.DIA_DA_SEMANA)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.CicloItens.HORA)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.CicloItens.MINUTO)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.CicloItens.N_POMODOROS)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.CicloItens.POMODORO_TIME)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.CicloItens.INTERVALO_TIME)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.CicloItens.OBSERVACAO)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.CicloItens.ID_CONTAINER)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.CicloItens.ID_CICLO)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.CicloItens.ID_USUARIO))
        );
        return  model;
    }

    public ArrayList<CicloItem> returnAllCicloItens(){
        ArrayList<CicloItem> items = new ArrayList<>();
        Cursor cursor = getDatabase().query(
                DataBaseHelper.CicloItens.TABELA,
                DataBaseHelper.CicloItens.COLUNAS_COM_ID,
                null,
                null,
                null,
                null,
                null);
            while (cursor.moveToNext()) {
                CicloItem model = montarCicloItemComId(cursor);
                items.add(model);
            }
        cursor.close();
        return items;
    }

    public long salvarCicloItem(CicloItem item){
        ContentValues valores = new ContentValues();
        valores.put(DataBaseHelper.CicloItens.DIA_DA_SEMANA, item.getDiaDaSemana());
        valores.put(DataBaseHelper.CicloItens.ID_USUARIO, item.getIdUsuario());

        if(item.get_id() != null){
            return getDatabase().update(DataBaseHelper.CicloItens.TABELA, valores,"_id =?", new String[]{item.get_id().toString()});
        }
        return getDatabase().insert(DataBaseHelper.CicloItens.TABELA, null, valores);
    }

    public boolean removerCicloItem(int id){
        return getDatabase().delete(DataBaseHelper.CicloItens.TABELA, "_id =?",  new String[]{Integer.toString(id)}) > 0;
    }

    public CicloItem buscarCicloItemPorId(int id){
        Cursor cursor = getDatabase().query(DataBaseHelper.CicloItens.TABELA,
                DataBaseHelper.CicloItens.COLUNAS_COM_ID, "_id =?",  new String[]{Integer.toString(id)}, null, null, null);
        if(cursor.moveToNext()){
            CicloItem model = criarCicloItem(cursor);
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