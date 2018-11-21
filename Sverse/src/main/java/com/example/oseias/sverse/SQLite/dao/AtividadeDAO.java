package com.example.oseias.sverse.SQLite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.oseias.sverse.SQLite.model.Atividade;

import java.util.ArrayList;

/**
 * Created by IUser on 18/02/2018.
 */

public class AtividadeDAO {
    private DataBaseHelper dataBaseaseHelper;
    private SQLiteDatabase database;

    public AtividadeDAO(Context ctx){
        dataBaseaseHelper = new DataBaseHelper(ctx);
    }

    private SQLiteDatabase getDatabase(){
        if(database == null){
            database = dataBaseaseHelper.getWritableDatabase();
        }
        return database;
    }

    private Atividade criarAtividade(Cursor cursor){
        Atividade model = new Atividade(
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Atividades.ID_MATERIA)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Atividades.ATIVIDADE)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Atividades.ASSUNTO)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Atividades.DESCRICAO)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Atividades.DIFICULDADE)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Atividades.ESTADO)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Atividades.DATA_DE_CRICAO)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Atividades.DATA_DE_COMPLETADA)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Atividades.DATA_DE_ENTREGA)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Atividades.ID_USUARIO))
        );
        return  model;
    }

    public ArrayList<Atividade> listarAtividades(){
        Cursor cursor = getDatabase().query(DataBaseHelper.Atividades.TABELA,
                DataBaseHelper.Atividades.COLUNAS, null, null, null, null, null);
        ArrayList<Atividade> atividades = new ArrayList<>();
        while(cursor.moveToNext()){
            Atividade model = criarAtividade(cursor);
            atividades.add(model);
        }
        cursor.close();

        return atividades;
    }

    public long salvarAtividade(Atividade atividade){
        ContentValues valores = new ContentValues();
        valores.put(DataBaseHelper.Atividades.ID_MATERIA, atividade.getId_materia());
        valores.put(DataBaseHelper.Atividades.ATIVIDADE, atividade.getAtividade());
        valores.put(DataBaseHelper.Atividades.ASSUNTO, atividade.getAssunto());
        valores.put(DataBaseHelper.Atividades.DESCRICAO, atividade.getDescricao());
        valores.put(DataBaseHelper.Atividades.DIFICULDADE, atividade.getDificudade());
        valores.put(DataBaseHelper.Atividades.ESTADO, atividade.getEstado());
        valores.put(DataBaseHelper.Atividades.DATA_DE_CRICAO, atividade.getData_de_criacao());
        valores.put(DataBaseHelper.Atividades.DATA_DE_COMPLETADA, atividade.getData_de_completada());
        valores.put(DataBaseHelper.Atividades.DATA_DE_ENTREGA, atividade.getData_de_entrega());
        valores.put(DataBaseHelper.Atividades.ID_USUARIO, atividade.getId_usuario());

        if(atividade.get_id() != null){
            return getDatabase().update(DataBaseHelper.Atividades.TABELA, valores,"_id =?", new String[]{atividade.get_id().toString()});
        }
        return getDatabase().insert(DataBaseHelper.Atividades.TABELA, null, valores);
    }

    public boolean removerAtividade(int id){
        return getDatabase().delete(DataBaseHelper.Atividades.TABELA, "_id =?",  new String[]{Integer.toString(id)}) > 0;
    }

    public Atividade buscarAtividadePorId(int id){
        Cursor cursor = getDatabase().query(DataBaseHelper.Atividades.TABELA,
                DataBaseHelper.Atividades.COLUNAS, "_id = ?",  new String[]{Integer.toString(id)}, null, null, null);
        if(cursor.moveToNext()){
            Atividade model = criarAtividade(cursor);
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
