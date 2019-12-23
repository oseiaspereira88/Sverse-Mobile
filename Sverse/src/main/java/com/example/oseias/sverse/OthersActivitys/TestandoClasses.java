package com.example.oseias.sverse.OthersActivitys;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oseias.sverse.OthersClass.MyCountDownTime;
import com.exemple.oseias.sverse.R;

import java.util.Calendar;
import java.util.List;

public class TestandoClasses extends AppCompatActivity {
    TextView tvTime;
    EditText editMin;
    Button bStart, bPause, bStop;
    int ms;
    Context ctx = this;
    MyCountDownTime time;
    long msRestantes;
    boolean isServiceRun;
    Intent itImplicita;
    Intent itExplicita;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronometer_test);
        inicializarVars();
    }

    public static Intent convertImplicitIntent(Intent implicitIntent, Context context) {
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfoList = pm.queryIntentServices(implicitIntent, 0);

        if (resolveInfoList == null || resolveInfoList.size() != 1) {
            return null;
        }
        ResolveInfo serviceInfo = resolveInfoList.get(0);
        ComponentName component = new ComponentName(serviceInfo.serviceInfo.packageName, serviceInfo.serviceInfo.name);
        Intent explicitIntent = new Intent(implicitIntent);
        explicitIntent.setComponent(component);
        return explicitIntent;
    }

    private void inicializarVars() {
        tvTime = (TextView) findViewById(R.id.tvTime);
        editMin = (EditText) findViewById(R.id.editMin);
        bStart = (Button) findViewById(R.id.bStart);
        bPause = (Button) findViewById(R.id.bPause);
        bStop = (Button) findViewById(R.id.bRestart);
        msRestantes = 0;
        isServiceRun = false;

        itImplicita = new Intent();
        itImplicita.setAction("com.example.oseias.sverse.OthersClass.ContBackgroundService");
        itExplicita = convertImplicitIntent(itImplicita, getApplicationContext());
        recuperarTimeInBG();

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
                if(!isServiceRun){
                    startService();
                    isServiceRun=true;
                }
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
                if(isServiceRun){
                    stopService();
                    isServiceRun=false;
                }
            }
        });
    }

    private void recuperarTimeInBG() {
        tvTime.setText(getCurrentTime(true, itImplicita.getLongExtra("getMillis", 1))
                + ":" + getCurrentTime(false, itImplicita.getLongExtra("getMillis", 1)));
    }

    private String getCurrentTime(boolean isMin, long millisUntilFinished) {
        int constCalendar = isMin ? Calendar.MINUTE : Calendar.SECOND;
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millisUntilFinished);
        return c.get(constCalendar) < 10 ? "0"+c.get(constCalendar) : ""+c.get(constCalendar);
    }

    public void startService(){
        //int segundosRestantes = (int) (time.getMillisUntilFinished()/1000);
        itExplicita.putExtra("maxWorkTimeInSeconds", Integer.parseInt(String.valueOf(editMin.getText())) * 60);
        itExplicita.putExtra("getMillis", ms);
        if(itExplicita!=null){
            startService(itExplicita);
        }
    }
    public void stopService(){
        if(itExplicita!=null){
            stopService(itExplicita);
        }
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
