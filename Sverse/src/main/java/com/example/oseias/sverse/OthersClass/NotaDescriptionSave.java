package com.example.oseias.sverse.OthersClass;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Random;

/**
 * Created by Oseias on 19/11/2017.
 */

public class NotaDescriptionSave {

    //Variaveis Básicas
    private String texto;
    private String titulo;
    private String numEmoji;
    private String tag;
    private String alarme;
    private String cor;
    private String dataDeCriacao;
    private String dataDeAtualizacao;
    ///////////////////////////////

    // Especificando a nota
    private boolean haveTitle;
    private boolean haveNumEmoji;
    private boolean haveTag;
    private boolean haveAlarme;
    ///////////////////////////

    //Others Variaveis
    private String[] coresPadoes;


    //Construtores/////////////////////////////////////////////////////////////////
    public NotaDescriptionSave(String texto, String titulo, String numEmoji, String tag, String cor, String dataDeCriacao, String dataDeAtualizacao, String alarme) {
        this.texto = texto;
        this.titulo = titulo;
        this.numEmoji = numEmoji;
        this.tag = tag;
        this.cor = cor;
        this.dataDeCriacao = dataDeCriacao;
        this.dataDeAtualizacao = dataDeAtualizacao;
        this.alarme = alarme;

        coresPadoes = new String[]{"1","2","3","4","5","6"};

        if (cor.equals("null")) {
            Random random = new Random();
            this.cor = coresPadoes[random.nextInt(coresPadoes.length)];
        } else {
            this.cor = cor;
        }

        if (titulo.equals("null")) {
            haveTitle = false;
        } else {
            haveTitle = true;
        }

        if (numEmoji.equals("null")) {
            haveNumEmoji = false;
        } else {
            haveNumEmoji = true;
        }

        if (tag.equals("null")) {
            haveTag = false;
        } else {
            haveTag = true;
        }

        if (alarme.equals("null") || alarme.equals("null\n") || alarme.equals("null \n")){
            haveAlarme = false;
        } else {
            haveAlarme = true;
        }

    }
    /////////////////////////////////////////////////////////////////////////////

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getNumEmoji() {
        return numEmoji;
    }

    public void setNumEmoji(String numEmoji) {
        this.numEmoji = numEmoji;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAlarme() {
        return alarme;
    }

    public void setAlarme(String lembrete) {
        this.alarme = lembrete;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(String dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getDataDeAtualizacao() {
        return dataDeAtualizacao;
    }

    public void setDataDeAtualizacao(String dataDeAtualizacao) {
        this.dataDeAtualizacao = dataDeAtualizacao;
    }

    //////////////////////////////////////////////////////////////////////////////////////

    public boolean isHaveTitle() {
        return haveTitle;
    }

    public void setHaveTitle(boolean haveTitle) {
        this.haveTitle = haveTitle;
    }

    public boolean isHaveNumEmoji() {
        return haveNumEmoji;
    }

    public void setHaveNumEmoji(boolean haveNumEmoji) {
        this.haveNumEmoji = haveNumEmoji;
    }

    public boolean isHaveTag() {
        return haveTag;
    }

    public void setHaveTag(boolean haveTag) {
        this.haveTag = haveTag;
    }

    public boolean isHaveAlarme() { return haveAlarme;}

    public void setHaveAlarme(boolean haveAlarme) {
        this.haveAlarme = haveAlarme;
    }
}