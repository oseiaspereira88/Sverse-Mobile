package com.example.oseias.sverse.OthersActivitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.oseias.sverse.MainActivity;
import com.versaplications.prodesenvelopment.sverse.R;
import com.example.oseias.sverse.SQLite.dao.AtividadeDAO;
import com.example.oseias.sverse.SQLite.model.Atividade;

public class CriadorDeAtividades extends AppCompatActivity {
    Bundle b;
    EditText edt1;
    EditText edt2;
    AtividadeDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criador_de_atividades);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edt1 = findViewById(R.id.editNome);
        edt2 = findViewById(R.id.edtTextAssunto);
        dao = new AtividadeDAO(this);

        if(getIntent().hasExtra("id")){
            b = getIntent().getBundleExtra("id");
            Atividade atividade = dao.buscarAtividadePorId(b.getInt("id"));
            edt1.setText(atividade.getAtividade());
            edt2.setText(atividade.getAssunto());
        }
    }

    public void addAct(View view){
        if (b != null) {
            Atividade atividade = new Atividade(0, edt1.getText().toString(), edt2.getText().toString(), "", "", "", "", "", "", 0);
            atividade.set_id(b.getInt("id"));
            dao.salvarAtividade(atividade);
            Intent it = new Intent(this, MainActivity.class);
            startActivity(it);
            finish();
        }else {
            Atividade atividade = new Atividade(0, edt1.getText().toString(), edt2.getText().toString(), "", "", "", "", "", "", 0);
            dao.salvarAtividade(atividade);
            Intent it = new Intent(this, MainActivity.class);
            startActivity(it);
            finish();
        }
    }

    public void cancelarCriacao(View view) {
        Toast.makeText(this, "A nota foi cancelada.", Toast.LENGTH_SHORT).show();
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }

}
