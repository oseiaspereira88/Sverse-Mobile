package com.example.oseias.sverse.SQLite.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by IUser on 18/02/2018.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String BANCO_DE_DADOS = "Sverse_BD001";
    private static final int VERSAO = 1;

    public DataBaseHelper(Context context) {
        super(context, BANCO_DE_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {

        //Criando Tabela de Usuarios
        bd.execSQL("create table usuarios(" +
                "_id integer primary key autoincrement," +
                "nome text not null," +
                "login text not null," +
                "senha text not null)");

        //Criando Tabela de Containers
        bd.execSQL("create table containers(" +
                "_id integer primary key autoincrement," +
                "nome text not null," +
                "type text not null," +
                "descricao text not null," +
                "n_participantes integer not null," +
                "n_notificacoes integer not null," +
                "image_bg integer not null," +
                "image_container integer not null," +
                "dificuldade text not null," +
                "importancia text not null," +
                "id_professor integer not null," +
                "code_participacao text not null," +
                "data_de_criacao datetime default current_timestamp)");

        //Criando Tabela de Notas
        //Obs: data_de_criacao datetime default() current_timestamp
        bd.execSQL("create table notas(" +
                "_id integer primary key autoincrement," +
                "titulo text," +
                "nota text not null," +
                "n_emoji integer," +
                "tag text," +
                "data_de_alarme datetime," +
                "cor text," +
                "data_de_criacao datetime default current_timestamp," +
                "data_de_atualizacao datetime," +
                "data_de_completada datetime," +
                "id_usuario integer not null)");

        //Criando Tabela de Ciclos
        //Obs: data_de_criacao datetime default() current_timestamp
        bd.execSQL("create table ciclos(" +
                "_id integer primary key autoincrement," +
                "titulo text," +
                "cor integer," +
                "id_usuario integer not null)");

        //Criando Tabela de CicloItens
        //Obs: data_de_criacao datetime default() current_timestamp
        bd.execSQL("create table ciclo_itens(" +
                "_id integer primary key autoincrement," +
                "dia_da_semana integer not null," +
                "minuto integer not null," +
                "n_pomodoros integer not null," +
                "pomodoro_time integer not null," +
                "intervalo_time integer not null," +
                "observacao text," +
                "id_container integer not null," +
                "id_ciclo integer not null," +
                "id_usuario integer not null)");

        //Criando Tabela de Atividades
        //Obs: data_de_criacao datetime default() current_timestamp
        bd.execSQL("create table atividades(" +
                "_id integer primary key autoincrement," +
                "id_materia integer," +
                "atividade text not null," +
                "assunto text," +
                "descricao text," +
                "dificudades text," +
                "estado text," +
                "data_de_criacao datetime default current_timestamp," +
                "data_de_completada datetime," +
                "data_de_entrega datetime," +
                "id_usuario integer not null)");

        //Criando Tabela de Avisos
        bd.execSQL("create table avisos(" +
                "_id integer primary key autoincrement," +
                "nota aviso not null," +
                "id_usuario integer not null)");

        //Criando Tabela de Notificações
        //Obs: data_de_criacao datetime default() current_timestamp
        bd.execSQL("create table notificacoes(" +
                "_id integer primary key autoincrement," +
                "titulo text not null," +
                "texto text not null," +
                "tipo integer not null," +
                "background text not null," +
                "data_de_criacao datetime default current_timestamp)");

        //Criando Tabela de Eventos
        bd.execSQL("create table eventos(" +
                "_id integer primary key autoincrement," +
                "id_materia integer," +
                "titulo text," +
                "assunto text," +
                "descricao text," +
                "data_de_evento datetime not null," +
                "id_usuario integer not null)");

        //Criando Tabela de ConfiguracoesGerais
        //Obs: data_de_criacao datetime default() current_timestamp
        bd.execSQL("create table configuracoes_gerais(" +
                "_id integer primary key," +
                "valor_config integer)");

        //Add o nosso primeiro User
        bd.execSQL("insert into usuarios(nome, login, senha) values " +
                "('Usuario Teste', 'os',  '12')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public static class Usuarios{
        public static final String TABELA = "usuarios";
        public static final String _ID = "_id";
        public static final String NOME = "nome";
        public static final String LOGIN = "login";
        public static final String SENHA = "senha";
        public static final String[] COLUNAS = new String[]{
        NOME, LOGIN, SENHA
        };
        public static final String[] COLUNAS_COM_ID = new String[]{
                _ID, NOME, LOGIN, SENHA
        };
    }

    public static class Containers{
        public static final String TABELA = "containers";
        public static final String _ID = "_id";
        public static final String NOME = "nome";
        public static final String TYPE = "type";
        public static final String DESCRICAO = "descricao";
        public static final String N_PARTICIPANTES = "n_participantes";
        public static final String N_NOTIFICACOES = "n_notificacoes";
        public static final String IMAGE_BG = "image_bg";
        public static final String IMAGE_CONTAINER = "image_container";
        public static final String DIFICULDADE = "dificuldade";
        public static final String IMPORTANCIA = "importancia";
        public static final String ID_PROFESSOR = "id_professor";
        public static final String CODE_PARTICIPACAO = "code_participacao";
        public static final String DATA_DE_CRIACAO = "data_de_criacao";
        public static final String[] COLUNAS = new String[]{
                _ID,
                NOME,
                TYPE,
                DESCRICAO,
                N_PARTICIPANTES,
                N_NOTIFICACOES,
                IMAGE_BG,
                IMAGE_CONTAINER,
                DIFICULDADE,
                IMPORTANCIA,
                ID_PROFESSOR,
                CODE_PARTICIPACAO,
                DATA_DE_CRIACAO
        };
    }

    public static class Notas{

        public static final String TABELA = "notas";
        public static final String _ID = "_id";
        public static final String TITULO = "titulo";
        public static final String NOTA = "nota";
        public static final String N_EMOJI = "n_emoji";
        public static final String TAG = "tag";
        public static final String DATA_ALARME = "data_de_alarme";
        public static final String COR = "cor";
        public static final String DATA_DE_CRIACAO = "data_de_criacao";
        public static final String DATA_DE_ATUALIZACAO = "data_de_atualizacao";
        public static final String DATA_DE_COMPLETADA = "data_de_completada";
        public static final String ID_USUARIO = "id_usuario";

        public static final String[] COLUNAS = new String[]{
                TITULO, NOTA, N_EMOJI, TAG, DATA_ALARME, COR,
                DATA_DE_CRIACAO, DATA_DE_ATUALIZACAO, DATA_DE_COMPLETADA, ID_USUARIO
        };

        public static final String[] COLUNAS_COM_ID = new String[]{
                _ID, TITULO, NOTA, N_EMOJI, TAG, DATA_ALARME, COR,
                DATA_DE_CRIACAO, DATA_DE_ATUALIZACAO, DATA_DE_COMPLETADA, ID_USUARIO
        };
    }

    public static class Ciclos{

        public static final String TABELA = "ciclos";
        public static final String _ID = "_id";
        public static final String TITULO = "titulo";
        public static final String COR = "cor";
        public static final String ID_USUARIO = "id_usuario";

        public static final String[] COLUNAS = new String[]{
                TITULO, COR, ID_USUARIO
        };

        public static final String[] COLUNAS_COM_ID = new String[]{
                _ID, TITULO, COR, ID_USUARIO
        };
    }

    public static class CicloItens{

        public static final String TABELA = "ciclo_itens";
        public static final String _ID = "_id";
        public static final String DIA_DA_SEMANA = "dia_da_semana";
        public static final String HORA = "hora";
        public static final String MINUTO = "minuto";
        public static final String N_POMODOROS = "n_pomodoros";
        public static final String POMODORO_TIME = "pomodoro_time";
        public static final String INTERVALO_TIME = "intervalo_time";
        public static final String OBSERVACAO = "observacao";
        public static final String ID_CONTAINER = "id_container";
        public static final String ID_CICLO = "id_ciclo";
        public static final String ID_USUARIO = "id_usuario";

        public static final String[] COLUNAS = new String[]{
                DIA_DA_SEMANA, HORA, MINUTO, N_POMODOROS, INTERVALO_TIME, OBSERVACAO, ID_CONTAINER, ID_CICLO, ID_USUARIO
        };

        public static final String[] COLUNAS_COM_ID = new String[]{
                _ID, DIA_DA_SEMANA, HORA, MINUTO, N_POMODOROS, INTERVALO_TIME, OBSERVACAO, ID_CONTAINER, ID_CICLO, ID_USUARIO
        };
    }

    public static class Atividades{
        public static final String TABELA = "atividades";
        public static final String _ID = "_id";
        public static final String ID_MATERIA = "id_materia";
        public static final String ATIVIDADE = "atividade";
        public static final String ASSUNTO = "assunto";
        public static final String DESCRICAO = "descricao";
        public static final String DIFICULDADE = "dificudades";
        public static final String ESTADO = "estado";
        public static final String DATA_DE_CRICAO = "data_de_criacao";
        public static final String DATA_DE_COMPLETADA = "data_de_completada";
        public static final String DATA_DE_ENTREGA = "data_de_entrega";
        public static final String ID_USUARIO = "id_usuario";


        public static final String[] COLUNAS = new String[]{
                _ID, ID_MATERIA, ATIVIDADE, ASSUNTO, DESCRICAO, DIFICULDADE, ESTADO,DATA_DE_CRICAO ,
                DATA_DE_COMPLETADA, DATA_DE_ENTREGA, ID_USUARIO
        };
    }

    public static class Avisos{

        public static final String TABELA = "avisos";
        public static final String _ID = "_id";
        public static final String AVISO = "aviso";
        public static final String ID_USUARIO = "id_usuario";

        public static final String[] COLUNAS = new String[]{
                _ID, AVISO, ID_USUARIO
        };
    }

    public static class Notifi{

        public static final String TABELA = "notificacoes";
        public static final String _ID = "_id";
        public static final String TITULO = "titulo";
        public static final String TEXTO = "texto";
        public static final String TIPO = "tipo";
        public static final String BG = "background";
        public static final String DATA_DE_CRIACAO = "data_de_criacao";;

        public static final String[] COLUNAS = new String[]{
                _ID, TITULO, TEXTO, TIPO, BG, DATA_DE_CRIACAO
        };
    }

    public static class Eventos{

        public static final String TABELA = "eventos";
        public static final String _ID = "_id";
        public static final String ID_MATERIA = "id_materia";
        public static final String TITULO = "titulo";
        public static final String ASSUNTO = "assunto";
        public static final String DESCRICAO = "descricao";
        public static final String DATA_DE_EVENTO = "data_de_evento";
        public static final String ID_USUARIO = "id_usuario";


        public static final String[] COLUNAS = new String[]{
                _ID, ID_MATERIA, TITULO, ASSUNTO, DESCRICAO, DATA_DE_EVENTO, ID_USUARIO
        };
    }

    public static class ConfiguracoesGerais{

        public static final String TABELA = "configuracoes_gerais";
        public static final String _ID = "_id";
        public static final String VALOR_CONFIG = "valor_config";


        public static final String[] COLUNAS = new String[]{
                _ID, VALOR_CONFIG
        };
    }
}
