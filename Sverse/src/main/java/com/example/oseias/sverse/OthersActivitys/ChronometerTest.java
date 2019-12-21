package com.example.oseias.sverse.OthersActivitys;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oseias.sverse.OthersClass.MyCountDownTime;
import com.exemple.oseias.sverse.R;

public class ChronometerTest extends AppCompatActivity {
    TextView tvTime;
    EditText editMin;
    Button bStart, bPause, bStop;
    int ms;
    Context ctx = this;
    MyCountDownTime time;
    long msRestantes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronometer_test);
        inicializarVars();
    }

    private void inicializarVars() {
        tvTime = (TextView) findViewById(R.id.tvTime);
        editMin = (EditText) findViewById(R.id.editMin);
        bStart = (Button) findViewById(R.id.bStart);
        bPause = (Button) findViewById(R.id.bPause);
        bStop = (Button) findViewById(R.id.bStop);
        msRestantes = 0;

        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time!=null) {
                    time.cancel();
                }
                if(msRestantes==0) {
                    ms = Integer.parseInt(editMin.getText().equals("")? "0" : editMin.getText().toString()) * 60 * 1000;
                    time = new MyCountDownTime(ctx, tvTime, ms, 1000);
                    Toast.makeText(ctx, "Pomodoro iniciado.", Toast.LENGTH_SHORT).show();
                } else {
                    time = new MyCountDownTime(ctx, tvTime, msRestantes, 1000);
                    Toast.makeText(ctx, "Continuando a contagem...", Toast.LENGTH_SHORT).show();
                }
                time.start();
            }
        });

        bPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time!=null){
                    msRestantes = time.getMillisUntilFinished();
                    time.cancel();
                }
            }
        });

        bStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time!=null){
                    time.cancel();
                    tvTime.setText("00:00");
                    msRestantes = 0;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(time!=null){
            time.cancel();
        }
    }
}
