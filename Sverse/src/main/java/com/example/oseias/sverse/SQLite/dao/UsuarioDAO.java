package com.example.oseias.sverse.SQLite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.oseias.sverse.SQLite.model.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IUser on 18/02/2018.
 */

public class UsuarioDAO {
    private DataBaseHelper dataBaseaseHelper;
    private SQLiteDatabase database;
    private Context ctx;


    public UsuarioDAO(Context ctx) {
        dataBaseaseHelper = new DataBaseHelper(ctx);
        this.ctx = ctx;
    }

    private SQLiteDatabase getDatabase() {

        //Cria um bd se não houver um
        if (database == null) {
            database = dataBaseaseHelper.getWritableDatabase();
        }
        return database;
    }

    private Usuario criarUsuario(Cursor cursor) {
        Usuario model = new Usuario(
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Usuarios.NOME)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Usuarios.LOGIN)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Usuarios.SENHA))
        );
        return model;
    }

    private Usuario criarUsuarioComId(Cursor cursor) {
        Usuario model = new Usuario(
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Usuarios._ID)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Usuarios.NOME)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Usuarios.LOGIN)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Usuarios.SENHA))
        );
        return model;
    }

    public List<Usuario> listarUsuarios() {
        Cursor cursor = getDatabase().query(DataBaseHelper.Usuarios.TABELA,
                DataBaseHelper.Usuarios.COLUNAS_COM_ID, null, null, null, null, null);
        List<Usuario> usuarios = new ArrayList<>();
        while (cursor.moveToNext()) {
            Usuario model = criarUsuarioComId(cursor);
            usuarios.add(model);
        }
        cursor.close();

        return usuarios;
    }

    public long salvarNovoUsuario(Usuario usuario) {
        ContentValues valores = new ContentValues();
        valores.put(DataBaseHelper.Usuarios.NOME, usuario.getNome());
        valores.put(DataBaseHelper.Usuarios.LOGIN, usuario.getLogin());
        valores.put(DataBaseHelper.Usuarios.SENHA, usuario.getSenha());

        if (usuario.get_id() != null) {
            return getDatabase().update(DataBaseHelper.Usuarios.TABELA, valores, "_id = ?", new String[]{usuario.get_id().toString()});
        }
        return getDatabase().insert(DataBaseHelper.Usuarios.TABELA, null, valores);
    }

    public boolean removerUsuario(int id) {
        return getDatabase().delete(DataBaseHelper.Usuarios.TABELA, "_id = ?", new String[]{Integer.toString(id)}) > 0;
    }

    public Usuario buscarUsuarioPorId(int id) {
        Cursor cursor = getDatabase().query(DataBaseHelper.Usuarios.TABELA,
                DataBaseHelper.Usuarios.COLUNAS_COM_ID, "_id = ?", new String[]{Integer.toString(id)}, null, null, null);
        if (cursor.moveToNext()) {
            Usuario model = criarUsuario(cursor);
            cursor.close();
            return model;
        }
        return null;
    }

    public boolean logar(String login, String senha) {
        Cursor cursor = getDatabase().query(DataBaseHelper.Usuarios.TABELA, null, "LOGIN = ? AND SENHA = ?", new String[]{login, senha}, null, null, null);

        if (cursor.moveToFirst()) {
            return true;
        }
        return false;
    }

    public Usuario retornUserPorLogin(String login, String senha) {
        Usuario model = new Usuario();
        Cursor cursor = getDatabase().query(
        DataBaseHelper.Usuarios.TABELA,
        null, "LOGIN = ? AND SENHA = ?",
        new String[]{login, senha},
        null, null, null);

        if (cursor.moveToFirst()) {
            Cursor cursor2 = getDatabase().query(
            DataBaseHelper.Usuarios.TABELA,
            DataBaseHelper.Usuarios.COLUNAS_COM_ID,
            "LOGIN = ? AND SENHA = ?",
            new String[]{login, senha},
            null, null, null);

            if (cursor2.moveToNext()) {
                model = criarUsuarioComId(cursor);
                cursor2.close();
            }
        }
        return model;
    }

    public void fecharConexao() {
        database.close();
        database = null;
    }


}
