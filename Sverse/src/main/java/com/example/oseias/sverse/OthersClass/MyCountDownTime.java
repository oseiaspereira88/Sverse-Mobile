package com.example.oseias.sverse.OthersClass;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MyCountDownTime extends CountDownTimer {
    Context ctx;
    TextView tv;
    private long timeInFuture;
    private long intervalo;

    public MyCountDownTime(Context ctx, TextView tv, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        intervalo = countDownInterval;
        this.ctx = ctx;
        this.tv = tv;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        timeInFuture = millisUntilFinished;
        tv.setText(getCurrentTime(true, millisUntilFinished) + ":" + getCurrentTime(false, millisUntilFinished));
    }

    private String getCurrentTime(boolean isMin, long millisUntilFinished) {
        String aux;
        int constCalendar = isMin ? Calendar.MINUTE : Calendar.SECOND;
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millisUntilFinished);

        aux = c.get(constCalendar) < 10 ? "0"+c.get(constCalendar) : ""+c.get(constCalendar);
        return aux;
    }

    @Override
    public void onFinish() {
        timeInFuture-=1000;
        tv.setText(getCurrentTime(true, timeInFuture) + ":" + getCurrentTime(false, timeInFuture));
        Toast.makeText(ctx, "A contagem terminou! É hora da pausa.", Toast.LENGTH_SHORT).show();
    }

    public long getMillisUntilFinished(){
        return timeInFuture;
    }
}
