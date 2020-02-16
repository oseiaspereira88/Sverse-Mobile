package com.example.oseias.sverse.OthersActivitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.exemple.oseias.sverse.R;

public class CicloItemCreator extends AppCompatActivity {
    private Spinner sPretencao, sNPomodoros, sTempoPomodoro, sTempoIntervalo, sDiaDaSemana;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_item_creator2);
        //findAllViews();
        //initVars();
    }

    private void findAllViews() {
        sPretencao = (Spinner) findViewById(R.id.sPretencao);
        sNPomodoros = (Spinner) findViewById(R.id.sNPomodoros);
        sTempoPomodoro = (Spinner) findViewById(R.id.sTempoPomodoro);
        sTempoIntervalo = (Spinner) findViewById(R.id.sTempoIntervalo);
        sDiaDaSemana = (Spinner) findViewById(R.id.sDiaDaSemana);
    }

    private void initVars() {
        ArrayAdapter sAdapter1 = ArrayAdapter.createFromResource(this, R.array.pretencao_list, android.R.layout.simple_spinner_item);
        ArrayAdapter sAdapter2 = ArrayAdapter.createFromResource(this, R.array.num_list, android.R.layout.simple_spinner_item);
        ArrayAdapter sAdapter3 = ArrayAdapter.createFromResource(this, R.array.min_list, android.R.layout.simple_spinner_item);
        ArrayAdapter sAdapter4 = ArrayAdapter.createFromResource(this, R.array.min_list2, android.R.layout.simple_spinner_item);
        ArrayAdapter sAdapter5 = ArrayAdapter.createFromResource(this, R.array.dias_semana_list, android.R.layout.simple_spinner_item);
        sPretencao.setAdapter(sAdapter1);
        sNPomodoros.setAdapter(sAdapter2);
        sTempoPomodoro.setAdapter(sAdapter3);
        sTempoIntervalo.setAdapter(sAdapter4);
        sDiaDaSemana.setAdapter(sAdapter5);
    }
}
