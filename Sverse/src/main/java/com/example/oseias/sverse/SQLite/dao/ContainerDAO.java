package com.example.oseias.sverse.SQLite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.oseias.sverse.SQLite.model.Atividade;
import com.example.oseias.sverse.SQLite.model.Container;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IUser on 18/02/2018.
 */

public class ContainerDAO {
    private DataBaseHelper dataBaseaseHelper;
    private SQLiteDatabase database;

    public ContainerDAO(Context ctx){
        dataBaseaseHelper = new DataBaseHelper(ctx);
    }

    private SQLiteDatabase getDatabase(){
        if(database == null){
            database = dataBaseaseHelper.getWritableDatabase();
        }
        return database;
    }

    private Container montarContainer(Cursor cursor){
        Container model = new Container(
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Containers._ID)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Containers.NOME)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Containers.TYPE)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Containers.DESCRICAO)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Containers.N_PARTICIPANTES)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Containers.N_NOTIFICACOES)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Containers.IMAGE_BG)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Containers.IMAGE_CONTAINER)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Containers.DIFICULDADE)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Containers.IMPORTANCIA)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Containers.ID_PROFESSOR)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Containers.CODE_PARTICIPACAO)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Containers.DATA_DE_CRIACAO))
        );
        return  model;
    }

    public ArrayList<Container> listarContainers(){
        Cursor cursor = getDatabase().query(DataBaseHelper.Containers.TABELA,
                DataBaseHelper.Containers.COLUNAS, null, null, null, null, null);
        ArrayList<Container> containers = new ArrayList<>();
        while(cursor.moveToNext()){
            Container container = montarContainer(cursor);
            containers.add(container);
        }
        cursor.close();
        return containers;
    }

    public long salvarContainer(Container container){
        ContentValues valores = new ContentValues();
        valores.put(DataBaseHelper.Containers._ID, container.get_id());
        valores.put(DataBaseHelper.Containers.NOME, container.getName());
        valores.put(DataBaseHelper.Containers.TYPE, container.getType());
        valores.put(DataBaseHelper.Containers.DESCRICAO, container.getDecricao());
        valores.put(DataBaseHelper.Containers.N_PARTICIPANTES, container.getnParticipantes());
        valores.put(DataBaseHelper.Containers.N_NOTIFICACOES, container.getnNotifications());
        valores.put(DataBaseHelper.Containers.IMAGE_BG, container.getImageBg());
        valores.put(DataBaseHelper.Containers.IMAGE_CONTAINER, container.getImageContainer());
        valores.put(DataBaseHelper.Containers.DIFICULDADE, container.getDificuldade());
        valores.put(DataBaseHelper.Containers.IMPORTANCIA, container.getImportancia());
        valores.put(DataBaseHelper.Containers.ID_PROFESSOR, container.getIdProfessor());
        valores.put(DataBaseHelper.Containers.CODE_PARTICIPACAO, container.getCodeParticipacao());
        valores.put(DataBaseHelper.Containers.DATA_DE_CRIACAO, container.getData_de_criacao());

        if(container.get_id() != null){
            return getDatabase().update(DataBaseHelper.Containers.TABELA, valores,"_id =?", new String[]{container.get_id().toString()});
        }
        return getDatabase().insert(DataBaseHelper.Containers.TABELA, null, valores);
    }

    public boolean removerContainer(int id){
        return getDatabase().delete(DataBaseHelper.Containers.TABELA, "_id =?",  new String[]{Integer.toString(id)}) > 0;
    }

    public Container buscarContainerPorId(int id){
        Cursor cursor = getDatabase().query(DataBaseHelper.Containers.TABELA,
                DataBaseHelper.Containers.COLUNAS, "_id =?",  new String[]{Integer.toString(id)}, null, null, null);
        if(cursor.moveToNext()){
            Container container = montarContainer(cursor);
            cursor.close();
            return  container;
        }
        return null;
    }

    public void fecharConexao(){
        database.close();
        database = null;
    }
}
