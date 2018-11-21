package com.example.oseias.sverse.OthersActivitys;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.oseias.sverse.MainActivity;
import com.example.oseias.sverse.DialogFragments.AlertDialogFragment;
import com.example.oseias.sverse.OthersClass.GestorDeNotas;
import com.example.oseias.sverse.SQLite.dao.ConfiguracaoDAO;
import com.example.oseias.sverse.SQLite.model.Configuracao;
import com.versaplications.prodesenvelopment.sverse.R;
import com.example.oseias.sverse.SQLite.dao.NotaDAO;
import com.example.oseias.sverse.SQLite.model.NotaModel;

import java.util.Calendar;

public class CriadorDeNotas extends AppCompatActivity {
    private boolean haveNota = false;

    //Vars básicas
    private String nota = "", titulo = "", numEmoji = "", tag = "", cor = "", dataDeCriacao = "", dataDeAtualizacao = "", dataDeCompletada = "", alarme = "";

    //Vars do Layout
    private EditText textTitulo, textNota;
    private TextView textData, textHora, textAlarm1, textAlarm2;

    //Vars para o Alarme
    private int ano, mes, dia, hora, minuto;

    //Nosso Gestor de Notas
    private GestorDeNotas gn;

    Bundle b;
    NotaDAO dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criador_de_notas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeViews();
        inicializarAlarmeCreator();
        salvarCofiguracoes();

        if(getIntent().hasExtra("id")){
            b = getIntent().getBundleExtra("id");
            dao = new NotaDAO(this);
            NotaModel nota = dao.buscarNotaPorId(b.getInt("id"));
            textTitulo.setText(nota.getTitulo());
            textNota.setText(nota.getNota());
        }
    }

    public void criarNota(View view) {
        tratarAtributos();
        if (haveNota) {
            if (b != null) {
                inicializarDatas();
                NotaModel model = new NotaModel(titulo, nota, numEmoji, tag, alarme, cor, dataDeCriacao, dataDeAtualizacao, dataDeCompletada, 1);
                model.set_id(b.getInt("id"));
                gn.notaDAO.salvarNota(model);
                limparCampos();
                Toast.makeText(this, "Nota editada com sucesso.", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                if (haveNota) {
                    inicializarDatas();
                    gn.notaDAO.salvarNota(new NotaModel(titulo, nota, numEmoji, tag, alarme, cor, dataDeCriacao, dataDeAtualizacao, dataDeCompletada, 1));
                    limparCampos();
                    Toast.makeText(this, "Nota criada com sucesso.", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        } else {
            Toast.makeText(this, "Primeiro digite sua Nota!", Toast.LENGTH_SHORT).show();
        }
    }

    public void tratarAtributos() {
        if (!textNota.getText().toString().equals("")) {
            haveNota = true;
            nota = textNota.getText().toString();
        } else {
            haveNota = false;
        }

        if (!textTitulo.getText().toString().equals("")) {
            titulo = textTitulo.getText().toString();
        } else {
            titulo = "";
        }

        if (numEmoji.equals("")) {
            numEmoji = "";
        }

        if (tag.equals("")) {
            tag = "";
        }

        if (cor.equals("")) {
            cor = "";
        }

        if (alarme.equals("")) {
            alarme = "";
        }

    }

    public void inicializarDatas() {
        final Calendar cal = Calendar.getInstance();
        int anoDefault = cal.get(Calendar.YEAR);
        int mesDefault = cal.get(Calendar.MONTH);
        int diaDefault = cal.get(Calendar.DAY_OF_MONTH);
        int horaDefault = cal.get(Calendar.HOUR);
        int minutoDefault = cal.get(Calendar.MINUTE);

        dataDeCriacao = dataDeAtualizacao = String.valueOf(diaDefault)
                + "/" + String.valueOf(mesDefault + 1)
                + "/" + String.valueOf(anoDefault)
                + "&" + String.valueOf(horaDefault)
                + ":" + String.valueOf(minutoDefault);
    }

    public void cancelarCriacao(View view) {
        if (b != null) {
            gn.notaDAO.removerNota(b.getInt("id"));
            Toast.makeText(this, "Nota apagada com sucesso.", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            if (haveNota) {
                Toast.makeText(this, "Nota cancelada com sucesso.", Toast.LENGTH_SHORT).show();
                finish();
            } else{
                finish();
            }
        }
    }

    public void initializeViews() {
        textTitulo = (EditText) this.findViewById(R.id.editTitulo1);
        textNota = (EditText) this.findViewById(R.id.editNota1);
        gn = new GestorDeNotas(this);
    }

    public void limparCampos() {
        textTitulo.setText("");
        textNota.setText("");
    }

    public void addAlarme(View view) {

        /*AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Selecione o dia");
        alertDialog.setView(new CalendarView(this));
        alertDialog.show();*/

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        AlertDialogFragment adf = new AlertDialogFragment();
        DialogFragment newFragment = adf;
        newFragment.show(ft, "alertDialog");


        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Selecione o dia");
        alertDialog.setMessage(adf.getLong() + "");
        alertDialog.show();
    }

    public void inicializarAlarmeCreator() {
        inicializarAlarmTexts();

        final Calendar cal = Calendar.getInstance();
        ano = cal.get(Calendar.YEAR);
        mes = cal.get(Calendar.MONTH);
        dia = cal.get(Calendar.DAY_OF_MONTH);
        hora = cal.get(Calendar.HOUR);
        minuto = cal.get(Calendar.MINUTE);

        atualizarData();
        atualizarHora();
    }

    public void inicializarAlarmTexts() {
        textData = (TextView) this.findViewById(R.id.lbl_data);
        textHora = (TextView) this.findViewById(R.id.lbl_hora);
        textAlarm1 = (TextView) this.findViewById(R.id.textAlarm1);
        textAlarm2 = (TextView) this.findViewById(R.id.textAlarm2);
    }

    public void atualizarData() {
        textData.setText(new StringBuilder().append(dia).append("/").append(mes + 1)
                .append("/").append(ano));
    }

    public void atualizarHora() {
        textHora.setText(new StringBuilder().append(hora).append(":").append(minuto));
    }

    public void mensagemData() {
        Toast.makeText(this, new StringBuilder().append("Data: ").append(textData.getText()), Toast.LENGTH_SHORT).show();
        mostrarHora();
    }

    public void mensagemHora() {
        Toast.makeText(this, new StringBuilder().append("Hora: ").append(textHora.getText()), Toast.LENGTH_SHORT).show();
    }

    public void mostrarData(View view) {
        DialogFragment ClasseData = new DataPickerFragment();
        ClasseData.show(getFragmentManager(), "datepicker");
    }

    public void mostrarHora() {
        DialogFragment classHora = new TimePickerFragment();
        classHora.show(getFragmentManager(), "timepicker");
    }

    public void cancelarAlarme() {
        limparAlarme();
        textAlarm1.setText("Alarme Cancelado");
        textAlarm2.setText("Add quando quiser.");
        alarme = "";
    }

    public void limparAlarme() {
        textData.setText(new StringBuilder().append("--").append("/").append("--")
                .append("/").append("--"));
        textHora.setText(new StringBuilder().append("--").append(":").append("--"));
    }

    public void receberAlarme() {
        textAlarm1.setText("Com Alarme");
        textAlarm2.setText("Adicionado com sucesso.");
        alarme = textData.getText() + "&" + textHora.getText();
        ImageView img = (ImageView) this.findViewById(R.id.bDelAlarme);
        img.setVisibility(ImageView.VISIBLE);
        img.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                cancelarAlarme();
                Toast.makeText(CriadorDeNotas.this, "Alarme Cancelado.", Toast.LENGTH_SHORT).show();
                //View.inflate(CriadorDeNotas.this, R.id.bDelAlarme, CriadorDeNotas.this.findViewById(R.id.vGrup));
                //Pegar locais em x e y;
                YoYo.with(Techniques.Pulse)
                        .duration(700)
                        .repeat(0)
                        .playOn(v);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        v.setVisibility(ImageView.INVISIBLE);
                        handler.postDelayed(this, 1000);
                    }
                }, 1000);
            }
        });
    }

    @SuppressLint("ValidFragment")
    public class DataPickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            ano = year;
            mes = month;
            dia = day;
            atualizarData();
            mensagemData();
        }

        @Override
        public void onCancel(DialogInterface dialog) {
            cancelarAlarme();
            super.onCancel(dialog);
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendario = Calendar.getInstance();
            ano = calendario.get(Calendar.YEAR);
            mes = calendario.get(Calendar.MONTH);
            dia = calendario.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, ano, mes, dia);
        }

        @Override
        public int show(FragmentTransaction transaction, String tag) {
            return super.show(transaction, tag);
        }

    }

    @SuppressLint("ValidFragment")
    public class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public void onTimeSet(TimePicker timePicker, int h, int min) {
            hora = h;
            minuto = min;
            atualizarHora();
            mensagemHora();
            receberAlarme();
        }

        @Override
        public void onCancel(DialogInterface dialog) {
            cancelarAlarme();
            super.onCancel(dialog);
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            hora = c.get(Calendar.HOUR);
            minuto = c.get(Calendar.MINUTE);


            return new TimePickerDialog(
                    getActivity(),
                    this,
                    hora,
                    minuto,
                    android.text.format.DateFormat.is24HourFormat(getActivity()));
        }

        @Override
        public int show(FragmentTransaction transaction, String tag) {
            return super.show(transaction, tag);
        }

    }

    public void salvarCofiguracoes(){
        ConfiguracaoDAO configsDAO = new ConfiguracaoDAO(this);
        configsDAO.atualizarConfig(new Configuracao(Configuracao.LAST_INDEX_FRAGMENT, 2));
        Toast.makeText(this, "O LastIndexFragment foi salvo...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish() {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
        super.finish();
    }
}
