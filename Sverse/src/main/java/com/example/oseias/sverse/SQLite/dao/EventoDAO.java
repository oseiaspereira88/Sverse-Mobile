package com.example.oseias.sverse.SQLite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.oseias.sverse.SQLite.model.Evento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IUser on 18/02/2018.
 */

public class EventoDAO {
    private DataBaseHelper dataBaseaseHelper;
    private SQLiteDatabase database;

    public EventoDAO(Context ctx){
        dataBaseaseHelper = new DataBaseHelper(ctx);
    }

    private SQLiteDatabase getDatabase(){
        if(database == null){
            database = dataBaseaseHelper.getWritableDatabase();
        }
        return database;
    }

    private Evento criarEvento(Cursor cursor){
        Evento model = new Evento(
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Eventos.ID_MATERIA)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Eventos.TITULO)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Eventos.ASSUNTO)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Eventos.DESCRICAO)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Eventos.DATA_DE_EVENTO)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Eventos.ID_USUARIO))
        );
        return  model;
    }

    public List<Evento> listarEventos(){
        Cursor cursor = getDatabase().query(DataBaseHelper.Eventos.TABELA,
                DataBaseHelper.Eventos.COLUNAS, null, null, null, null, null);
        List<Evento> atividades = new ArrayList<>();
        while(cursor.moveToNext()){
            Evento model = criarEvento(cursor);
            atividades.add(model);
        }
        cursor.close();

        return atividades;
    }

    public long salvarEvento(Evento evento){
        ContentValues valores = new ContentValues();
        valores.put(DataBaseHelper.Eventos.ID_MATERIA, evento.getIdDaMateria());
        valores.put(DataBaseHelper.Eventos.TITULO, evento.getTitulo());
        valores.put(DataBaseHelper.Eventos.ASSUNTO, evento.getAssunto());
        valores.put(DataBaseHelper.Eventos.DESCRICAO, evento.getDescricao());
        valores.put(DataBaseHelper.Eventos.DATA_DE_EVENTO, evento.getDataDeEvento());
        valores.put(DataBaseHelper.Eventos.ID_USUARIO, evento.getId_usuario());

        if(evento.get_id() != null){
            return getDatabase().update(DataBaseHelper.Eventos.TABELA, valores,"_id =?", new String[]{evento.get_id().toString()});
        }
        return getDatabase().insert(DataBaseHelper.Eventos.TABELA, null, valores);
    }

    public boolean removerEvento(int id){
        return getDatabase().delete(DataBaseHelper.Eventos.TABELA, "_id =?",  new String[]{Integer.toString(id)}) > 0;
    }

    public Evento buscarEventoPorId(int id){
        Cursor cursor = getDatabase().query(DataBaseHelper.Eventos.TABELA,
                DataBaseHelper.Eventos.COLUNAS, "_id = ?",  new String[]{Integer.toString(id)}, null, null, null);
        if(cursor.moveToNext()){
            Evento model = criarEvento(cursor);
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
