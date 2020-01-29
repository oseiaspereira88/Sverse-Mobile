package com.example.oseias.sverse;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.oseias.sverse.Adapters.MyFragmentPagerAdapter;
import com.example.oseias.sverse.OthersActivitys.CicloActivity;
import com.exemple.oseias.sverse.R;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusBarBorderRadius(this);
        findAllViews();
        initViews();
    }

    public void findAllViews(){
        tabLayout = (TabLayout) findViewById(R.id.mainTabLayout);
        viewPager = (ViewPager) findViewById(R.id.mainPager);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), 6);
        viewPager.setAdapter(myFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void initViews(){
        tabLayout.getTabAt(0).setIcon(R.drawable.iconhome4);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconmochila);
        tabLayout.getTabAt(2).setIcon(R.drawable.iconnota);
        tabLayout.getTabAt(3).setIcon(R.drawable.icongroup2);
        tabLayout.getTabAt(4).setIcon(R.drawable.iconnotifi4);
        tabLayout.getTabAt(5).setIcon(R.drawable.iconmenu);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarBorderRadius(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.border_top_radius);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    // Código botão Voltar
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            checkExit();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private void checkExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja realmente sair?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void animateButton(View view) {
        YoYo.with(Techniques.RotateIn)
                .duration(700)
                .repeat(0)
                .playOn(view);
    }

    public void abrirCicloDeEstudo(View view) {
        animateButton(view);
        Intent it = new Intent(this, CicloActivity.class);
        startActivity(it);
    }


}
