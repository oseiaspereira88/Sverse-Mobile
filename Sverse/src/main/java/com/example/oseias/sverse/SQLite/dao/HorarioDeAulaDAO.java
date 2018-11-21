package com.example.oseias.sverse.SQLite.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by IUser on 18/02/2018.
 */

public class HorarioDeAulaDAO {
    private DataBaseHelper dataBaseaseHelper;
    private SQLiteDatabase database;

    public HorarioDeAulaDAO(Context ctx){
        dataBaseaseHelper = new DataBaseHelper(ctx);
    }

    private SQLiteDatabase getDatabase(){
        if(database == null){
            database = dataBaseaseHelper.getWritableDatabase();
        }
        return database;
    }
/*
    private Atividade criarTarefa(Cursor cursor){
        Atividade model = new Atividade(
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Tarefas._ID)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Tarefas.TAREFA)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Tarefas.DATA_DE_CRIACAO)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Tarefas.DATA_DE_COMPLETADA)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Tarefas.DATA_DE_ENTREGA)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Tarefas.ID_USUARIO))
        );
        return  model;
    }

    public List<Atividade> listarTarefas(){
        Cursor cursor = getDatabase().query(DataBaseHelper.Tarefas.TABELA,
                DataBaseHelper.Tarefas.COLUNAS, null, null, null, null, null);
        List<Atividade> atividades = new ArrayList<>();
        while(cursor.moveToNext()){
            Atividade model = criarTarefa(cursor);
            atividades.add(model);
        }
        cursor.close();

        return atividades;
    }

    public long salvarTarefa(Atividade atividade){
        ContentValues valores = new ContentValues();
        valores.put(DataBaseHelper.Tarefas.TAREFA, atividade.getTarefa());
        valores.put(DataBaseHelper.Tarefas.DATA_DE_CRIACAO, atividade.getData_de_criacao());
        valores.put(DataBaseHelper.Tarefas.DATA_DE_COMPLETADA, atividade.getData_de_completada());
        valores.put(DataBaseHelper.Tarefas.DATA_DE_ENTREGA, atividade.getData_de_entrega());
        valores.put(DataBaseHelper.Tarefas.ID_USUARIO, atividade.getId_usuario());

        if(atividade.get_id() != null){
            return getDatabase().update(DataBaseHelper.Tarefas.TABELA, valores,"_id =?", new String[]{atividade.get_id().toString()});
        }
        return getDatabase().insert(DataBaseHelper.Tarefas.TABELA, null, valores);
    }

    public boolean removerTarefa(int id){
        return getDatabase().delete(DataBaseHelper.Tarefas.TABELA, "_id =?",  new String[]{Integer.toString(id)}) > 0;
    }

    public Atividade buscarTarefaPorId(int id){
        Cursor cursor = getDatabase().query(DataBaseHelper.Tarefas.TABELA,
                DataBaseHelper.Tarefas.COLUNAS, "_id = ?",  new String[]{Integer.toString(id)}, null, null, null);
        if(cursor.moveToNext()){
            Atividade model = criarTarefa(cursor);
            cursor.close();
            return  model;
        }
        return null;
    }

    public void fecharConexao(){
        database.close();
        database = null;
    }*/
}
