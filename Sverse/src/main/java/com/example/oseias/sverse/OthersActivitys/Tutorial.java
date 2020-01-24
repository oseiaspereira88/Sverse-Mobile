package com.example.oseias.sverse.OthersActivitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.exemple.oseias.sverse.R;

public class Tutorial extends AppCompatActivity {

    // Create the Handler object (on the main thread by default)
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        inicializarVars();
    }

    public void inicializarVars(){
        getWindow().setStatusBarColor(Color.WHITE);

        final Intent it = new Intent(this, Login.class);
        YoYo.with(Techniques.StandUp)
                .duration(1200)
                .repeat(0)
                .playOn(findViewById(R.id.logoIntro));

        final Handler handler = new Handler();
        final Runnable r = new Runnable()
        {
            public void run()
            {
                startActivity(it);
                finish();
            }
        };
        handler.postDelayed(r, 5000);
    }
}
