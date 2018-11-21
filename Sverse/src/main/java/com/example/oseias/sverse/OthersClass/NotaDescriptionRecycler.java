package com.example.oseias.sverse.OthersClass;

public class NotaDescriptionRecycler {
    private boolean have_new_nota;
    private NotaDescriptionSave notaInRecycler;

    public NotaDescriptionRecycler() {

    }

    public boolean haveNewNota(){
        /*if(notaInRecycler == null || notaInRecycler.getText().equals("") || notaInRecycler.getText() == null){
            have_new_nota = false;
        } else{
            have_new_nota = true;
        }*/
        return have_new_nota;
    }

    public void deletNotaInRecycler(){
        notaInRecycler = null;
        have_new_nota = false;
    }

    public void addNotaDescriptionSave(NotaDescriptionSave notaDescriptionSave){
        notaInRecycler = notaDescriptionSave;
    }

    public NotaDescriptionSave getNotaInRecycler(){
        return notaInRecycler;
    }
}
