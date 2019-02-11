package com.example.oseias.sverse.OthersActivitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oseias.sverse.MainActivity;
import com.example.oseias.sverse.SQLite.dao.ConfiguracaoDAO;
import com.example.oseias.sverse.SQLite.dao.ContainerDAO;
import com.example.oseias.sverse.SQLite.model.Configuracao;
import com.example.oseias.sverse.SQLite.model.Container;
import com.versaplications.prodesenvelopment.sverse.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class CriadorDeTurmas extends AppCompatActivity {
    //Vars básicas
    private String dataDeCriacao = "", horaDeCriacao = "";
    private Integer nImgContainerBG, nImgContainer;

    //Vars do Layout
    private TextView textNome, textType, textData, textHora, nNotifications;
    private ImageView imgContainer, imgContainerBG, imgCamera, buttonSelectorBG, buttonDescartar, buttonCreate;
    private EditText editName, editDescricao;
    private Spinner spinnerDificuldade, spinnerImportancia;
    private SpinnerAdapter sAdapter;

    Bundle b;
    ContainerDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criador_de_turmas);
        initializeViews();
        salvarCofiguracoes();

        if (getIntent().hasExtra("id")) {
            b = getIntent().getBundleExtra("id");
            dao.buscarContainerPorId(b.getInt("id"));
            Toast.makeText(this, "valor do dao: " + (dao.buscarContainerPorId(b.getInt("id")).getName()), Toast.LENGTH_LONG).show();
            abrirContainer(dao.buscarContainerPorId(b.getInt("id")));
        }
    }

    public void initializeViews() {
        atualizarDataHora();
        textData = (TextView) this.findViewById(R.id.tvData);
        textHora = (TextView) this.findViewById(R.id.tvHora);
        textData.setText(dataDeCriacao);
        textHora.setText(horaDeCriacao);

        textNome = (TextView) this.findViewById(R.id.perfilNome);
        textType = (TextView) this.findViewById(R.id.tvEmailOrTipo);
        nNotifications = (TextView) this.findViewById(R.id.nNotifications);
        imgContainer = (ImageView) this.findViewById(R.id.imgPerfil);
        imgContainerBG = (ImageView) this.findViewById(R.id.imgBG);
        imgCamera = (ImageView) this.findViewById(R.id.imgCamera);
        buttonSelectorBG = (ImageView) this.findViewById(R.id.bSeletorBG);
        buttonDescartar = (ImageView) this.findViewById(R.id.bDescartar);
        buttonCreate = (ImageView) this.findViewById(R.id.bAcept);
        editName = (EditText) this.findViewById(R.id.editNome);
        editDescricao = (EditText) this.findViewById(R.id.editDescricao);
        spinnerDificuldade = (Spinner) this.findViewById(R.id.spinnerDificuldade);
        spinnerImportancia = (Spinner) this.findViewById(R.id.spinnerImportancia);

        inicializarSpinners();

        dao = new ContainerDAO(this);

        nImgContainerBG = R.drawable.bg13;
        nImgContainer = R.drawable.logo88;

        editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                textNome.setText(editName.getText());
            }
        });
    }

    public void inicializarSpinners(){
        ArrayAdapter adapterDificuldade = ArrayAdapter.createFromResource(this, R.array.dificuldade_list, android.R.layout.simple_spinner_item);
        ArrayAdapter adapterImportancia = ArrayAdapter.createFromResource(this, R.array.importancia_list, android.R.layout.simple_spinner_item);
        spinnerDificuldade.setAdapter(adapterDificuldade);
        spinnerImportancia.setAdapter(adapterImportancia);
    }

    public void abrirContainer(Container container) {
        textNome.setText(container.getName());
        textType.setText(container.getType());
        nNotifications.setText(container.getnNotifications().toString());
        imgContainer.setImageResource(container.getImageContainer());
        imgContainerBG.setImageResource(container.getImageBg());
        imgCamera.setVisibility(View.INVISIBLE);
        editName.setText(container.getName());
        editDescricao.setText(container.getDecricao());
        nImgContainerBG = container.getImageBg();
        nImgContainer = container.getImageContainer();
    }

    public void salvarContainer(View view) {
        if (validarCampos()) {
            if (b != null) {
                atualizarDataHora();
                Container turma = new Container(
                        editName.getText().toString(),
                        "Turma", editDescricao.getText().toString(),
                        1,
                        1,
                        nImgContainerBG,
                        nImgContainer,
                        spinnerDificuldade.getSelectedItem().toString(),
                        spinnerImportancia.getSelectedItem().toString(),
                        0,
                        "X1P0002018",
                        dataDeCriacao);

                turma.set_id(b.getInt("id"));
                dao.salvarContainer(turma);
                Toast.makeText(this, "Turma atualizada com sucesso.", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                atualizarDataHora();
                dao.salvarContainer(new Container(
                        editName.getText().toString(),
                        "Turma", editDescricao.getText().toString(),
                        1,
                        1,
                        nImgContainerBG,
                        nImgContainer,
                        "Difícil",
                        "Muito Importante",
                        0,
                        "X1P0002018",
                        dataDeCriacao));
                Toast.makeText(this, "Turma criada com sucesso.", Toast.LENGTH_SHORT).show();
                finish();

            }
        } else {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
        }
    }

    public void atualizarDataHora() {
        final Calendar cal = Calendar.getInstance();
        int anoDefault = cal.get(Calendar.YEAR);
        int mesDefault = cal.get(Calendar.MONTH);
        int diaDefault = cal.get(Calendar.DAY_OF_MONTH);
        int horaDefault = cal.get(Calendar.HOUR);
        int minutoDefault = cal.get(Calendar.MINUTE);
        dataDeCriacao = String.valueOf(diaDefault)
                + "/" + String.valueOf(mesDefault + 1)
                + "/" + String.valueOf(anoDefault);
        horaDeCriacao = String.valueOf(horaDefault)
                + ":" + String.valueOf(minutoDefault);
    }


    public void descartarContainer(View view) {
        if (b != null) {
            dao.removerContainer(b.getInt("id"));
            Toast.makeText(this, "A turma foi dissolvida.", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            if (validarCampos()) {
                Toast.makeText(this, "A nova turma foi cancelada.", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                finish();
            }
        }
    }

    public boolean validarCampos() {
        if (!editName.equals("") || !editDescricao.equals("")) {
            return true;
        }
        return false;
    }

    public void salvarCofiguracoes() {
        ConfiguracaoDAO configsDAO = new ConfiguracaoDAO(this);
        configsDAO.atualizarConfig(new Configuracao(Configuracao.LAST_INDEX_FRAGMENT, 1));
        Toast.makeText(this, "O LastIndexFragment foi salvo...", Toast.LENGTH_SHORT).show();
    }

    public void selecionarImageContainer(View view){
        view.setVisibility(View.INVISIBLE);

        ArrayList<Integer> imgs = new ArrayList<>();
        imgs.add(R.drawable.logo88);
        imgs.add(R.drawable.logo1);
        imgs.add(R.drawable.logo2);
        imgs.add(R.drawable.logotipo_1);
        imgs.add(R.drawable.logotipo_5);
        imgs.add(R.drawable.logotipo_8);
        imgs.add(R.drawable.logotipo_9);
        imgs.add(R.drawable.logotipo_13);
        imgs.add(R.drawable.logotipo_19);
        imgs.add(R.drawable.perfil_sverse);

        Random random = new Random();

        nImgContainer = imgs.get(random.nextInt(9));
        imgContainer.setImageResource(nImgContainer);
    }

    public void selecionarBGContainer(View view){
        ArrayList<Integer> bgs = new ArrayList<>();
        bgs.add(R.drawable.bg1);
        bgs.add(R.drawable.bg2);
        bgs.add(R.drawable.bg3);
        bgs.add(R.drawable.bg4);
        bgs.add(R.drawable.bg5);
        bgs.add(R.drawable.bg6);
        bgs.add(R.drawable.bg7);
        bgs.add(R.drawable.bg8);
        bgs.add(R.drawable.bg9);
        bgs.add(R.drawable.bg10);

        Random random = new Random();

        nImgContainerBG = bgs.get(random.nextInt(9));
        imgContainerBG.setImageResource(nImgContainerBG);

    }

    @Override
    public void finish() {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
        super.finish();
    }
}
