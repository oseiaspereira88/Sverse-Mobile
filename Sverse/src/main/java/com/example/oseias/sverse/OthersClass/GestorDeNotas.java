package com.example.oseias.sverse.OthersClass;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oseias.sverse.SQLite.dao.AtividadeDAO;
import com.example.oseias.sverse.SQLite.dao.AvisoDAO;
import com.example.oseias.sverse.SQLite.dao.EventoDAO;
import com.example.oseias.sverse.SQLite.dao.NotaDAO;
import com.example.oseias.sverse.SQLite.model.Atividade;
import com.example.oseias.sverse.SQLite.model.Aviso;
import com.example.oseias.sverse.SQLite.model.Evento;
import com.example.oseias.sverse.SQLite.model.NotaModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.versaplications.prodesenvelopment.sverse.R;

/**
 * Created by Oseias on 17/01/2018.
 */

public class GestorDeNotas {
    //id do usuário:::
    private static final String FILE_NAME_NUM_USERS = "numUsers.bup";
    private static final String FILE_NAME_NUM_USER = "numUser.bup";
    private static final String USUARIOS_CADASTRADOS = "usuariosCadastrados.bup";
    private int numUsers = 0;
    private int numUser = 0;

    //Variavéis de backup de usuários:::
    private static final String FILE_NAME_BACKUP_DO_BLOCO_DE_NOTAS = "backupDoBlocoDeNotas";
    private static final String FILES_EXTENTION = ".bup";
    private String backup;

    //Vars para o BD
    public NotaDAO notaDAO;
    public AtividadeDAO atividadeDAO;
    public AvisoDAO avisoDAO;
    public EventoDAO eventoDAO;

    //Booleans

    private boolean isOk = false;


    ///////////////////////////////////////////////////////////

    ArrayList<NotaDescriptionSave> blocoDeNotasDescriptionSave;
    private List<NotaModel> blocoDeNotasModel;
    private List<Atividade> blocoDeAtividadesModel;
    private List<Aviso> blocoDeAvisosModel;
    private List<Evento> blocoDeEventosModel;
    ArrayList<CardView> blocoDeNotas;

    Context ctx;


    public GestorDeNotas(Context ctx) {
        this.ctx = ctx;
        inicializarClasses();
    }

    public void inicializarClasses() {
        //View dos Layoouts
        blocoDeNotasDescriptionSave = new ArrayList<>();
        blocoDeNotas = new ArrayList<>();
        blocoDeNotasModel = new ArrayList<>();

        //InicializaNdo BD
        notaDAO = new NotaDAO(ctx);
        atividadeDAO = new AtividadeDAO(ctx);
        avisoDAO = new AvisoDAO(ctx);
        eventoDAO = new EventoDAO(ctx);
    }

    public void lerNotasDoBanco() {

        notaDAO.returnAllNotas();

        if(notaDAO.returnAllNotas().size() != 0) {

            Toast.makeText(ctx, notaDAO.returnAllNotas().size() + " notas foram lidas do banco.", Toast.LENGTH_LONG).show();

            blocoDeNotasModel = notaDAO.returnAllNotas();


        } else{
            Toast.makeText(ctx, "Nenhuma nota foi lida do banco.", Toast.LENGTH_LONG).show();
        }

        /*
        if(!atividadeDAO.listarAtividades().isEmpty()) {
            blocoDeAtividadesModel = atividadeDAO.listarAtividades();
        } else{

        }if(!avisoDAO.listarAvisos().isEmpty()){
            blocoDeAvisosModel = avisoDAO.listarAvisos();
        } else{

        } if(!eventoDAO.listarEventos().isEmpty()) {
            blocoDeEventosModel = eventoDAO.listarEventos();
        } else{

        }*/
    }

    public int selectColorAndBorter(int numCollor) {
        int numDrawable;
        switch (numCollor) {
            case 1:
                numDrawable = R.drawable.border1;
                break;
            case 2:
                numDrawable = R.drawable.border2;
                break;
            case 3:
                numDrawable = R.drawable.border3;
                break;
            case 4:
                numDrawable = R.drawable.border4;
                break;
            case 5:
                numDrawable = R.drawable.border5;
                break;
            case 6:
                numDrawable = R.drawable.border6;
                break;
            default:
                numDrawable = 0;
                break;
        }
        return numDrawable;
    }

    public String[] converterNulls(int id) {
        String n[] = new String[8];

        for (int i = 0; i < 8; i++) {
            if (blocoDeNotasModel.get(id).getNota().equals(null)) {
                n[i] = "null";
                isOk = false;
            } else {
                isOk = true;
                n[i] = blocoDeNotasModel.get(id).getNota();
            }

            if (blocoDeNotasModel.get(id).getTitulo().equals(null)) {
                n[i] = "null";
            } else {
                n[i] = blocoDeNotasModel.get(id).getTitulo();
            }

            if (blocoDeNotasModel.get(id).getN_emoji().equals(null)) {
                n[i] = "null";
            } else {
                n[i] = blocoDeNotasModel.get(id).getN_emoji();
            }

            if (blocoDeNotasModel.get(id).getTag().equals(null)) {
                n[i] = "null";
            } else {
                n[i] = blocoDeNotasModel.get(id).getTag();
            }

            if (blocoDeNotasModel.get(id).getCor().equals(null)) {
                n[i] = "null";
            } else {
                n[i] = blocoDeNotasModel.get(id).getCor();
            }

            if (blocoDeNotasModel.get(id).getData_de_criacao().equals(null)) {
                n[i] = "null";
            } else {
                n[i] = blocoDeNotasModel.get(id).getData_de_criacao();
            }

            if (blocoDeNotasModel.get(id).getData_de_atualizacao().equals(null)) {
                n[i] = "null";
            } else {
                n[i] = blocoDeNotasModel.get(id).getData_de_atualizacao();
            }

            if (blocoDeNotasModel.get(id).getDataAlarme().equals(null)) {
                n[i] = "null";
            } else {
                n[i] = blocoDeNotasModel.get(id).getDataAlarme();
            }
        }
        return n;
    }

    public void atribuirNotasParaNotasDescript() {
        String n[];
        for (int i = (blocoDeNotasModel.size() - 1); i > -1; i--) {
            n = converterNulls(i);
            NotaDescriptionSave novaNotaDescriptSave = new NotaDescriptionSave(
                    "" + n[0],
                    "" + n[1],
                    "" + n[2],
                    "" + n[3],
                    "" + n[4],
                    "" + n[5],
                    "" + n[6],
                    "" + n[7]
            );
            blocoDeNotasDescriptionSave.add(novaNotaDescriptSave);
        }
    }

    public ArrayList<CardView> retorneAllNotas() {
        //lerNotasArquivadas();

        lerNotasDoBanco();
        atribuirNotasParaNotasDescript();

        if (blocoDeNotasDescriptionSave.size() != 0) {
            Toast.makeText(ctx, "Temos " + blocoDeNotasDescriptionSave.size() + " nota(s).", Toast.LENGTH_LONG).show();

            for (int i = 0; i < blocoDeNotasDescriptionSave.size(); i++) {

                switch (analizarNota(blocoDeNotasDescriptionSave.get(i))) {

                    case 0:
                        //Tratando do CardView
                        CardView cardView0 = new CardView(ctx);
                        cardView0.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 280));
                        cardView0.setRadius(10);
                        cardView0.setCardElevation(10);
                        cardView0.setPadding(20, 60, 20, 20);

                        //Add Collor Background
                        cardView0.setBackgroundResource(selectColorAndBorter(Integer.parseInt(blocoDeNotasDescriptionSave.get(i).getCor())));

                        //Tratando do Button
                        Button button = new Button(ctx);
                        button.setTextSize(10);
                        //button.setTextAlignment(button.TEXT_ALIGNMENT_CENTER);
                        button.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        button.setBackgroundColor(Color.TRANSPARENT);
                        button.setPadding(20, 29, 20, 29);
                        button.setAllCaps(false);
                        button.setTypeface(Typeface.SERIF);
                        button.setTextSize(12);

                        //button(new Typeface());

                        //Add cada coisa a seu respectivo lugar

                        button.setText(blocoDeNotasDescriptionSave.get(i).getTexto());
                        cardView0.addView(button);


                        blocoDeNotas.add(cardView0);

                        break;
                    case 1:
                        //Tratando do CardView
                        CardView cardView3 = new CardView(ctx);
                        cardView3.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 280));
                        cardView3.setRadius(10);
                        cardView3.setCardElevation(10);

                        //Add Collor Background
                        cardView3.setBackgroundResource(selectColorAndBorter(Integer.parseInt(blocoDeNotasDescriptionSave.get(i).getCor())));

                        //Tratando do Button
                        Button button3 = new Button(ctx);
                        button3.setTextSize(10);
                        //button.setTextAlignment(button.TEXT_ALIGNMENT_CENTER);
                        button3.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        button3.setBackgroundColor(Color.TRANSPARENT);
                        button3.setPadding(20, 29, 20, 29);
                        button3.setAllCaps(false);
                        button3.setTypeface(Typeface.SERIF);
                        button3.setTextSize(12);
                        //button(new Typeface());

                        //Add cada coisa a seu respectivo lugar

                        button3.setText(blocoDeNotasDescriptionSave.get(i).getTexto());
                        cardView3.addView(button3);

                        blocoDeNotas.add(cardView3);

                        break;
                    case 2:
                        //Tratando do CardView
                        CardView cardView4 = new CardView(ctx);
                        cardView4.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 280));
                        cardView4.setRadius(10);
                        cardView4.setCardElevation(10);

                        //Add Collor Background
                        cardView4.setBackgroundResource(selectColorAndBorter(Integer.parseInt(blocoDeNotasDescriptionSave.get(i).getCor())));

                        //Tratando do Button
                        Button button4 = new Button(ctx);
                        button4.setTextSize(10);
                        //button.setTextAlignment(button.TEXT_ALIGNMENT_CENTER);
                        button4.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        button4.setBackgroundColor(Color.TRANSPARENT);
                        button4.setPadding(20, 0, 20, 0);
                        //button(new Typeface());

                        //Add cada coisa a seu respectivo lugar

                        button4.setText(blocoDeNotasDescriptionSave.get(i).getTexto());
                        cardView4.addView(button4);

                        blocoDeNotas.add(cardView4);

                        break;
                    case 3:
                        //Tratando do CardView
                        CardView cardView5 = new CardView(ctx);
                        cardView5.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 280));
                        cardView5.setRadius(10);
                        cardView5.setCardElevation(10);

                        //Add Collor Background
                        cardView5.setBackgroundResource(selectColorAndBorter(Integer.parseInt(blocoDeNotasDescriptionSave.get(i).getCor())));

                        //Tratando do Button
                        Button button5 = new Button(ctx);
                        button5.setTextSize(10);
                        //button.setTextAlignment(button.TEXT_ALIGNMENT_CENTER);
                        button5.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        button5.setBackgroundColor(Color.TRANSPARENT);
                        button5.setPadding(20, 0, 20, 0);
                        //button(new Typeface());

                        //Add cada coisa a seu respectivo lugar

                        button5.setText(blocoDeNotasDescriptionSave.get(i).getTexto());
                        cardView5.addView(button5);

                        blocoDeNotas.add(cardView5);
                        break;
                    case 4:
                        //Tratando do Frame
                        CardView cardView1 = new CardView(ctx);
                        cardView1.setLayoutParams(new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 280));
                        cardView1.setRadius(10);
                        cardView1.setCardElevation(10);

                        //Add Collor Background
                        cardView1.setBackgroundResource(selectColorAndBorter(Integer.parseInt(blocoDeNotasDescriptionSave.get(i).getCor())));

                        //criando LinearLayout
                        LinearLayout ll2 = new LinearLayout(ctx);
                        ll2.setOrientation(LinearLayout.VERTICAL);

                        //Tratando o TextView
                        TextView tv2 = new TextView(ctx);
                        tv2.setText(blocoDeNotasDescriptionSave.get(i).getTexto());
                        //tv2.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
                        tv2.setTextSize(10);
                        tv2.setGravity(TextView.TEXT_ALIGNMENT_CENTER);
                        tv2.setPadding(30, 0, 30, 0);

                        //Tratando da ImageView
                        ImageView iv2 = new ImageView(ctx);
                        iv2.setImageResource(R.mipmap.ic_launcher);
                        iv2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));
                        iv2.setPadding(0, 10, 0, 0);

                        //Add cada coisa a seu respectivo lugar
                        ll2.addView(iv2);
                        ll2.addView(tv2);
                        cardView1.addView(ll2);

                        blocoDeNotas.add(cardView1);

                        break;
                    case 5:
                        //Tratando do CardView
                        CardView cardView6 = new CardView(ctx);
                        cardView6.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 280));
                        cardView6.setRadius(10);
                        cardView6.setCardElevation(10);

                        //Add Collor Background
                        cardView6.setBackgroundResource(selectColorAndBorter(Integer.parseInt(blocoDeNotasDescriptionSave.get(i).getCor())));

                        //Tratando do Button
                        Button button6 = new Button(ctx);
                        button6.setTextSize(10);
                        //button.setTextAlignment(button.TEXT_ALIGNMENT_CENTER);
                        button6.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        button6.setBackgroundColor(Color.TRANSPARENT);
                        button6.setPadding(20, 0, 20, 0);
                        //button(new Typeface());

                        //Add cada coisa a seu respectivo lugar

                        button6.setText(blocoDeNotasDescriptionSave.get(i).getTexto());
                        cardView6.addView(button6);

                        blocoDeNotas.add(cardView6);

                        break;
                    case 6:
                        //Tratando do CardView
                        CardView cardView15 = new CardView(ctx);
                        cardView15.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 280));
                        cardView15.setRadius(10);
                        cardView15.setCardElevation(10);

                        //Add Collor Background
                        cardView15.setBackgroundResource(selectColorAndBorter(Integer.parseInt(blocoDeNotasDescriptionSave.get(i).getCor())));

                        //Tratando do Button
                        Button button15 = new Button(ctx);
                        button15.setTextSize(10);
                        //button.setTextAlignment(button.TEXT_ALIGNMENT_CENTER);
                        button15.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        button15.setBackgroundColor(Color.TRANSPARENT);
                        button15.setPadding(20, 0, 20, 0);
                        //button(new Typeface());

                        //Add cada coisa a seu respectivo lugar

                        button15.setText(blocoDeNotasDescriptionSave.get(i).getTexto());
                        cardView15.addView(button15);

                        blocoDeNotas.add(cardView15);

                        break;
                    case 7:
                        //Tratando do CardView
                        CardView cardView7 = new CardView(ctx);
                        cardView7.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 280));
                        cardView7.setRadius(10);
                        cardView7.setCardElevation(10);

                        //Add Collor Background
                        cardView7.setBackgroundResource(selectColorAndBorter(Integer.parseInt(blocoDeNotasDescriptionSave.get(i).getCor())));

                        //Tratando do Button
                        Button button7 = new Button(ctx);
                        button7.setTextSize(10);
                        //button.setTextAlignment(button.TEXT_ALIGNMENT_CENTER);
                        button7.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        button7.setBackgroundColor(Color.TRANSPARENT);
                        button7.setPadding(20, 0, 20, 0);
                        //button(new Typeface());

                        //Add cada coisa a seu respectivo lugar

                        button7.setText(blocoDeNotasDescriptionSave.get(i).getTexto());
                        cardView7.addView(button7);

                        blocoDeNotas.add(cardView7);

                        break;
                    case 8:
                        //Tratando do CardView
                        CardView cardView = new CardView(ctx);
                        cardView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 280));
                        cardView.setRadius(10);
                        cardView.setCardElevation(10);

                        //Add Collor Background
                        cardView.setBackgroundResource(selectColorAndBorter(Integer.parseInt(blocoDeNotasDescriptionSave.get(i).getCor())));

                        //Tratando o LinearLayout
                        LinearLayout ll11 = new LinearLayout(ctx);
                        ll11.setOrientation(LinearLayout.VERTICAL);

                        //Tratando o TextView3
                        TextView tv10 = new TextView(ctx);
                        tv10.setText(blocoDeNotasDescriptionSave.get(i).getTexto());
                        tv10.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
                        tv10.setGravity(TextView.TEXT_ALIGNMENT_CENTER);
                        tv10.setPadding(30, 10, 30, 16);
                        tv10.setTextSize(12);
                        tv10.setTypeface(Typeface.SERIF);

                        //Tratando o TextView4
                        TextView tv11 = new TextView(ctx);
                        tv11.setText(blocoDeNotasDescriptionSave.get(i).getTitulo());
                        tv11.setTextSize(14);
                        tv11.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
                        tv11.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
                        tv11.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        tv11.setPadding(10, 8, 10, 8);
                        tv11.setTypeface(Typeface.DEFAULT_BOLD);


                        LinearLayout ll10b = new LinearLayout(ctx);
                        ll10b.setOrientation(LinearLayout.VERTICAL);
                        ll10b.setGravity(LinearLayout.TEXT_ALIGNMENT_GRAVITY);
                        ll10b.setBackgroundResource(selectColorAndBorter(Integer.parseInt(blocoDeNotasDescriptionSave.get(i).getCor())));


                        //Add cada coisa a seu respectivo lugar
                        ll10b.addView(tv11);
                        ll11.addView(ll10b);
                        ll11.addView(tv10);
                        cardView.addView(ll11);

                        blocoDeNotas.add(cardView);

                        break;
                    case 9:
                        //Tratando do CardView
                        CardView cardView9 = new CardView(ctx);
                        cardView9.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 280));
                        cardView9.setRadius(10);
                        cardView9.setCardElevation(10);

                        //Add Collor Background
                        cardView9.setBackgroundResource(selectColorAndBorter(Integer.parseInt(blocoDeNotasDescriptionSave.get(i).getCor())));

                        //Tratando o LinearLayout
                        LinearLayout ll9 = new LinearLayout(ctx);
                        ll9.setOrientation(LinearLayout.VERTICAL);


                        //Tratando o TextView3
                        TextView tv9 = new TextView(ctx);
                        tv9.setText(blocoDeNotasDescriptionSave.get(i).getTexto());
                        tv9.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
                        tv9.setGravity(TextView.TEXT_ALIGNMENT_CENTER);
                        tv9.setPadding(30, 10, 30, 16);
                        tv9.setTextSize(12);
                        tv9.setTypeface(Typeface.SERIF);

                        //Tratando o TextView4
                        TextView tv8 = new TextView(ctx);
                        tv8.setText(blocoDeNotasDescriptionSave.get(i).getTitulo());
                        tv8.setTextSize(14);
                        tv8.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
                        tv8.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
                        tv8.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        tv8.setPadding(10, 8, 10, 8);
                        tv8.setTypeface(Typeface.DEFAULT_BOLD);


                        LinearLayout ll9b = new LinearLayout(ctx);
                        ll9b.setOrientation(LinearLayout.VERTICAL);
                        ll9b.setGravity(LinearLayout.TEXT_ALIGNMENT_GRAVITY);
                        ll9b.setBackgroundResource(selectColorAndBorter(Integer.parseInt(blocoDeNotasDescriptionSave.get(i).getCor())));


                        //Add cada coisa a seu respectivo lugar
                        ll9b.addView(tv8);
                        ll9.addView(ll9b);
                        ll9.addView(tv9);
                        cardView9.addView(ll9);

                        blocoDeNotas.add(cardView9);

                        break;
                    case 10:
                        //Tratando do CardView
                        CardView cardView8 = new CardView(ctx);
                        cardView8.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 280));
                        cardView8.setRadius(10);
                        cardView8.setCardElevation(10);

                        //Add Collor Background
                        cardView8.setBackgroundResource(selectColorAndBorter(Integer.parseInt(blocoDeNotasDescriptionSave.get(i).getCor())));

                        //Tratando do Button
                        Button button9 = new Button(ctx);
                        button9.setTextSize(10);
                        //button.setTextAlignment(button.TEXT_ALIGNMENT_CENTER);
                        button9.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        button9.setBackgroundColor(Color.TRANSPARENT);
                        button9.setPadding(20, 0, 20, 0);
                        //button(new Typeface());

                        //Add cada coisa a seu respectivo lugar

                        button9.setText(blocoDeNotasDescriptionSave.get(i).getTexto());
                        cardView8.addView(button9);

                        blocoDeNotas.add(cardView8);

                        break;
                    case 11:
                        //Tratando do CardView
                        CardView cardView10 = new CardView(ctx);
                        cardView10.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 280));
                        cardView10.setRadius(10);
                        cardView10.setCardElevation(10);

                        //Add Collor Background
                        cardView10.setBackgroundResource(selectColorAndBorter(Integer.parseInt(blocoDeNotasDescriptionSave.get(i).getCor())));

                        //Tratando do Button
                        Button button10 = new Button(ctx);
                        button10.setTextSize(10);
                        //button10.setTextAlignment(button.TEXT_ALIGNMENT_CENTER);
                        button10.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        button10.setBackgroundColor(Color.TRANSPARENT);
                        button10.setPadding(20, 0, 20, 0);
                        //button10(new Typeface());

                        //Add cada coisa a seu respectivo lugar

                        button10.setText(blocoDeNotasDescriptionSave.get(i).getTexto());
                        cardView10.addView(button10);

                        blocoDeNotas.add(cardView10);

                        break;
                    case 12:
                        //Tratando do CardView
                        CardView cardView11 = new CardView(ctx);
                        cardView11.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 280));
                        cardView11.setRadius(10);
                        cardView11.setCardElevation(10);

                        //Add Collor Background
                        cardView11.setBackgroundResource(selectColorAndBorter(Integer.parseInt(blocoDeNotasDescriptionSave.get(i).getCor())));

                        //Tratando do Button
                        Button button11 = new Button(ctx);
                        button11.setTextSize(10);
                        //button.setTextAlignment(button.TEXT_ALIGNMENT_CENTER);
                        button11.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        button11.setBackgroundColor(Color.TRANSPARENT);
                        button11.setPadding(20, 0, 20, 0);
                        //button(new Typeface());

                        //Add cada coisa a seu respectivo lugar

                        button11.setText(blocoDeNotasDescriptionSave.get(i).getTexto());
                        cardView11.addView(button11);

                        blocoDeNotas.add(cardView11);

                        break;
                    case 13:
                        //Tratando do CardView
                        CardView cardView12 = new CardView(ctx);
                        cardView12.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 280));
                        cardView12.setRadius(10);
                        cardView12.setCardElevation(10);

                        //Add Collor Background
                        cardView12.setBackgroundResource(selectColorAndBorter(Integer.parseInt(blocoDeNotasDescriptionSave.get(i).getCor())));

                        //Tratando do Button
                        Button button12 = new Button(ctx);
                        button12.setTextSize(10);
                        //button12.setTextAlignment(button.TEXT_ALIGNMENT_CENTER);
                        button12.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        button12.setBackgroundColor(Color.TRANSPARENT);
                        button12.setPadding(20, 0, 20, 0);
                        //button12(new Typeface());

                        //Add cada coisa a seu respectivo lugar

                        button12.setText(blocoDeNotasDescriptionSave.get(i).getTexto());
                        cardView12.addView(button12);

                        blocoDeNotas.add(cardView12);

                        break;
                    case 14:
                        //Tratando do CardView
                        CardView cardView13 = new CardView(ctx);
                        cardView13.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 280));
                        cardView13.setRadius(10);
                        cardView13.setCardElevation(10);

                        //Add Collor Background
                        cardView13.setBackgroundResource(selectColorAndBorter(Integer.parseInt(blocoDeNotasDescriptionSave.get(i).getCor())));

                        //Tratando do Button
                        Button button13 = new Button(ctx);
                        button13.setTextSize(10);
                        //button.setTextAlignment(button.TEXT_ALIGNMENT_CENTER);
                        button13.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        button13.setBackgroundColor(Color.TRANSPARENT);
                        button13.setPadding(20, 0, 20, 0);
                        //button(new Typeface());

                        //Add cada coisa a seu respectivo lugar

                        button13.setText(blocoDeNotasDescriptionSave.get(i).getTexto());
                        cardView13.addView(button13);

                        blocoDeNotas.add(cardView13);

                        break;
                    case 15:
                        //Tratando do CardView
                        CardView cardView16 = new CardView(ctx);
                        cardView16.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 280));
                        cardView16.setRadius(10);
                        cardView16.setCardElevation(10);

                        //Add Collor Background
                        cardView16.setBackgroundResource(selectColorAndBorter(Integer.parseInt(blocoDeNotasDescriptionSave.get(i).getCor())));

                        //Tratando do Button
                        Button button16 = new Button(ctx);
                        button16.setTextSize(10);
                        //button.setTextAlignment(button.TEXT_ALIGNMENT_CENTER);
                        button16.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        button16.setBackgroundColor(Color.TRANSPARENT);
                        button16.setPadding(20, 0, 20, 0);
                        //button(new Typeface());

                        //Add cada coisa a seu respectivo lugar

                        button16.setText(blocoDeNotasDescriptionSave.get(i).getTexto());
                        cardView16.addView(button16);

                        blocoDeNotas.add(cardView16);

                        break;
                }
            }

        } else {
            Toast.makeText(ctx, "Vc não tem nenhuma nota escrita.", Toast.LENGTH_SHORT).show();
            Toast.makeText(ctx, "Dica: Escreva sempre que necessário.", Toast.LENGTH_LONG).show();
        }


        return blocoDeNotas;
    }

    public int analizarNota(NotaDescriptionSave nds) {
        if ((!nds.isHaveTitle()) && (!nds.isHaveNumEmoji()) &&
                (!nds.isHaveTag()) && (!nds.isHaveAlarme())) {
            return 0;
        } else if (!nds.isHaveTitle() && !nds.isHaveNumEmoji() &&
                !nds.isHaveTag() && nds.isHaveAlarme()) {
            return 1;
        } else if (!nds.isHaveTitle() && !nds.isHaveNumEmoji() &&
                nds.isHaveTag() && !nds.isHaveAlarme()) {
            return 2;
        } else if (!nds.isHaveTitle() && !nds.isHaveNumEmoji() &&
                nds.isHaveTag() && nds.isHaveAlarme()) {
            return 3;
        } else if (!nds.isHaveTitle() && nds.isHaveNumEmoji() &&
                !nds.isHaveTag() && !nds.isHaveAlarme()) {
            return 4;
        } else if (!nds.isHaveTitle() && nds.isHaveNumEmoji() &&
                !nds.isHaveTag() && nds.isHaveAlarme()) {
            return 5;
        } else if (!nds.isHaveTitle() && nds.isHaveNumEmoji() &&
                nds.isHaveTag() && !nds.isHaveAlarme()) {
            return 6;
        } else if (!nds.isHaveTitle() && nds.isHaveNumEmoji() &&
                nds.isHaveTag() && nds.isHaveAlarme()) {
            return 7;
        } else if (nds.isHaveTitle() && !nds.isHaveNumEmoji() &&
                !nds.isHaveTag() && !nds.isHaveAlarme()) {
            return 8;
        } else if (nds.isHaveTitle() && !nds.isHaveNumEmoji() &&
                !nds.isHaveTag() && nds.isHaveAlarme()) {
            return 9;
        } else if (nds.isHaveTitle() && !nds.isHaveNumEmoji() &&
                nds.isHaveTag() && !nds.isHaveAlarme()) {
            return 10;
        } else if (nds.isHaveTitle() && !nds.isHaveNumEmoji() &&
                nds.isHaveTag() && nds.isHaveAlarme()) {
            return 11;
        } else if (nds.isHaveTitle() && nds.isHaveNumEmoji() &&
                !nds.isHaveTag() && !nds.isHaveAlarme()) {
            return 12;
        } else if (nds.isHaveTitle() && nds.isHaveNumEmoji() &&
                !nds.isHaveTag() && nds.isHaveAlarme()) {
            return 13;
        } else if (nds.isHaveTitle() && nds.isHaveNumEmoji() &&
                nds.isHaveTag() && !nds.isHaveAlarme()) {
            return 14;
        } else if (nds.isHaveTitle() && nds.isHaveNumEmoji() &&
                nds.isHaveTag() && nds.isHaveAlarme()) {
            return 15;
        } else {
            return 0;
        }
    }

    public NotaDescriptionSave receberDados(String texto, String titulo, String numEmoji, String tag, String cor, String dataDeCriacao, String dataDeAtualizacao, String alarme) {
        NotaDescriptionSave nota = new NotaDescriptionSave(texto, titulo, numEmoji, tag, cor, dataDeCriacao, dataDeAtualizacao, alarme);
        return nota;
    }

    public void criarNota(String texto, String titulo, String localDaImg, String tag, String cor, String dataDeCriacao, String dataDeAtualizacao, String alarme) {
        blocoDeNotasDescriptionSave = new ArrayList<NotaDescriptionSave>();
        //lerNotasArquivadas();
        NotaDescriptionSave novaNota = receberDados(texto, titulo, localDaImg, tag, cor, dataDeCriacao, dataDeAtualizacao, alarme);
        blocoDeNotasDescriptionSave.add(novaNota);
        //arquivarNotas();
        Toast.makeText(ctx, "Foi add mais uma nota. Ficando assim com:  " + blocoDeNotasDescriptionSave.size() + " notas até agora.", Toast.LENGTH_SHORT).show();
    }

    public void editarNota(int id, String texto, String titulo, String localDaImg, String tag, String cor, String dataDeCriacao, String dataDeAtualizacao, String alarme) {
        //lerNotasArquivadas();
        blocoDeNotasDescriptionSave.set(id, receberDados(texto, titulo, localDaImg, tag, cor, dataDeCriacao, dataDeAtualizacao, alarme));
        //arquivarNotas();
    }

    public void excluirNota(int id) {
        blocoDeNotasDescriptionSave.remove(id);
        arquivarNotas();
    }

    public void arquivarNotas() {

        if (new File(ctx.getFilesDir() + "/" + (FILE_NAME_BACKUP_DO_BLOCO_DE_NOTAS + numUser + FILES_EXTENTION)).exists()) {
            new File(ctx.getFilesDir() + "/" + (FILE_NAME_BACKUP_DO_BLOCO_DE_NOTAS + numUser + FILES_EXTENTION)).delete();
        }
        String fullText = "";
        for (int i = 0; i < blocoDeNotasDescriptionSave.size(); i++) {
            fullText = fullText + getParametrosOrdenados(i);

            FileOutputStream fos = null;
            try {
                fos = ctx.openFileOutput((FILE_NAME_BACKUP_DO_BLOCO_DE_NOTAS + numUsers + FILES_EXTENTION), ctx.MODE_PRIVATE);
                fos.write(fullText.getBytes());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        Toast.makeText(ctx, "Um backup do bloco de notas foi salvo em: " + ctx.getFilesDir() + "/" + (FILE_NAME_BACKUP_DO_BLOCO_DE_NOTAS + numUsers + FILES_EXTENTION), Toast.LENGTH_SHORT).show();
    }

    public void lerNotasArquivadas() {
        if (new File(ctx.getFilesDir() + "/" + (FILE_NAME_BACKUP_DO_BLOCO_DE_NOTAS + numUser + FILES_EXTENTION)).exists()) {
            // (###) -> separa uma nota de uma outra
            // (---) -> separa cada atributo de uma nota
            // (null) -> indica que o atributo não existe

            FileInputStream fis = null;
            try {
                fis = ctx.openFileInput(FILE_NAME_BACKUP_DO_BLOCO_DE_NOTAS + numUsers + FILES_EXTENTION);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String text;

                while ((text = br.readLine()) != null) {
                    sb.append(text).append("\n");
                }
                backup = sb.toString();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            String todasNotasEmbaralhadas[] = backup.split("%");
            ArrayList<String[]> todasNotas = new ArrayList<String[]>();

            for (int i = 0; i < todasNotasEmbaralhadas.length; i++) {
                if (todasNotasEmbaralhadas[i].split("_").length == 1) {
                    //Não faça nada.
                } else {
                    String aux[] = todasNotasEmbaralhadas[i].split("_");

                    if (aux.length == 9) {
                        String aux2[] = new String[8];
                        //Toast.makeText(ctx, "Bug no split detectado e tratado.", Toast.LENGTH_SHORT).show();

                        for (int j = 1; j < aux.length; j++) {
                            aux2[j - 1] = aux[j];
                        }
                        todasNotas.add(aux2);
                    }
                }
            }

            for (int i = 0; i < todasNotas.size(); i++) {
                //Toast.makeText(ctx, "Conteudo da primeira NotaModel: " + todasNotas.get(i)[0], Toast.LENGTH_SHORT).show();
                //Toast.makeText(ctx, todasNotas.get(i)[1], Toast.LENGTH_SHORT).show();
                //Toast.makeText(ctx, todasNotas.get(i)[2], Toast.LENGTH_SHORT).show();
                //Toast.makeText(ctx, todasNotas.get(i)[3], Toast.LENGTH_SHORT).show();
                //Toast.makeText(ctx, todasNotas.get(i)[4], Toast.LENGTH_SHORT).show();
                //Toast.makeText(ctx, todasNotas.get(i)[5], Toast.LENGTH_SHORT).show();
                NotaDescriptionSave novaNota = receberDados("" + todasNotas.get(i)[0], "" + todasNotas.get(i)[1], todasNotas.get(i)[2], todasNotas.get(i)[3], todasNotas.get(i)[4], "" + todasNotas.get(i)[5], "" + todasNotas.get(i)[6], "" + todasNotas.get(i)[7]);
                blocoDeNotasDescriptionSave.add(novaNota);
            }

        } else {
            Toast.makeText(ctx, "Você ainda não tem nenhuma nota.", Toast.LENGTH_SHORT).show();
            Toast.makeText(ctx, "Sempre que necessário, crie uma.", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteFilesBackup(View view) {
        if (new File(ctx.getFilesDir() + "/" + (FILE_NAME_BACKUP_DO_BLOCO_DE_NOTAS + numUser + FILES_EXTENTION)).exists()) {
            new File(ctx.getFilesDir() + "/" + (FILE_NAME_BACKUP_DO_BLOCO_DE_NOTAS + numUser + FILES_EXTENTION)).delete();
            Toast.makeText(ctx, "Arquivos de backup deletados.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ctx, "Não há arquivos de backup a serem deletados.", Toast.LENGTH_SHORT).show();
        }
    }


    public String getParametrosOrdenados(int id) {
        // (%) -> separa uma nota de uma outra
        // (_) -> separa cada atributo de uma nota
        // (null) -> indica que o atributo não existe

        //String fullText = "###"; -> se for a primeira criação de nota para esse save; Mas, se já se tem outras notas...

        String fullText = "";

        if (!getTextToSaveNota(id).equals("null")) {

            fullText += getTextToSaveNota(id)
                    + getTextToSaveTitulo(id)
                    + getTextToSaveNumEmoji(id)
                    + getTextToSaveTag(id)
                    + getTextToSaveCor(id)
                    + getTextToSaveDataDeCriacao(id)
                    + getTextToSaveDataDeAtualizacao(id)
                    + getTextToSaveAlarme(id);
        } else {
            Toast.makeText(ctx, "Você ainda não escreveu a nota.", Toast.LENGTH_SHORT).show();
        }

        return fullText;
    }

    public String getTextToSaveNota(int id) {
        if (!(blocoDeNotasDescriptionSave.get(id).getTexto().equals("") || blocoDeNotasDescriptionSave.get(id).getTexto().equals("null"))) {
            return ("%_" + blocoDeNotasDescriptionSave.get(id).getTexto() + "_");
        } else {
            return "%_null_";
        }
    }

    public String getTextToSaveTitulo(int id) {
        if (!(blocoDeNotasDescriptionSave.get(id).getTitulo().equals("") || blocoDeNotasDescriptionSave.get(id).getTitulo().equals("null"))) {
            return blocoDeNotasDescriptionSave.get(id).getTitulo() + "_";
        } else {
            return "null_";
        }
    }

    public String getTextToSaveNumEmoji(int id) {
        if (!(blocoDeNotasDescriptionSave.get(id).getNumEmoji().equals("") || blocoDeNotasDescriptionSave.get(id).getNumEmoji().equals("null"))) {
            return blocoDeNotasDescriptionSave.get(id).getNumEmoji() + "_";
        } else {
            return "null_";
        }
    }

    public String getTextToSaveTag(int id) {
        if (!(blocoDeNotasDescriptionSave.get(id).getTag().equals("") || blocoDeNotasDescriptionSave.get(id).getTag().equals("null"))) {
            return blocoDeNotasDescriptionSave.get(id).getTag() + "_";
        } else {
            return "null_";
        }
    }

    public String getTextToSaveCor(int id) {
        if (!(blocoDeNotasDescriptionSave.get(id).getCor().equals("") || blocoDeNotasDescriptionSave.get(id).getCor().equals("null"))) {
            return blocoDeNotasDescriptionSave.get(id).getCor() + "_";
        } else {
            return "null_";
        }
    }

    public String getTextToSaveDataDeCriacao(int id) {
        if (!(blocoDeNotasDescriptionSave.get(id).getDataDeCriacao().equals("") || blocoDeNotasDescriptionSave.get(id).getDataDeCriacao().equals("null"))) {
            return blocoDeNotasDescriptionSave.get(id).getDataDeCriacao() + "_";
        } else {
            return "null_";
        }
    }

    public String getTextToSaveDataDeAtualizacao(int id) {
        if (!(blocoDeNotasDescriptionSave.get(id).getDataDeAtualizacao().equals("") || blocoDeNotasDescriptionSave.get(id).getDataDeAtualizacao().equals("null"))) {
            return blocoDeNotasDescriptionSave.get(id).getDataDeAtualizacao() + "_";
        } else {
            return "null_";
        }
    }

    public String getTextToSaveAlarme(int id) {
        if (!(blocoDeNotasDescriptionSave.get(id).getAlarme().equals("") || blocoDeNotasDescriptionSave.get(id).getAlarme().equals("null"))) {
            return blocoDeNotasDescriptionSave.get(id).getAlarme();
        } else {
            return "null";
        }
    }
}
