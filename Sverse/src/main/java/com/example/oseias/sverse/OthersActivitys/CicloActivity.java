package com.example.oseias.sverse.OthersActivitys;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.oseias.sverse.Adapters.CicloFragmentPagerAdapter;
import com.example.oseias.sverse.OthersClass.ArquivamentoIndexFragment.IndexFragement;
import com.example.oseias.sverse.SQLite.dao.CicloDAO;
import com.example.oseias.sverse.SQLite.model.Ciclo;
import com.exemple.oseias.sverse.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

public class CicloActivity extends AppCompatActivity {
    private FloatingActionMenu fab;
    private FloatingActionButton fab1, fab2;
    private TabLayout tabLayout;
    private ViewPager pager;
    private CicloFragmentPagerAdapter pagerAdapter;
    private IndexFragement indexFragement;
    private TextView tvInfo;
    private CicloDAO cicloDAO;
    private ArrayList<Ciclo> ciclos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo);
        findFabs();
        initVars();
    }

    public void initVars() {
        cicloDAO = new CicloDAO(this);
        ciclos = cicloDAO.returnAllCiclos();

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        pager = (ViewPager) findViewById(R.id.cicloPager);

        //Setando os titulos e instanciando o pagerAdapter
        ArrayList<String> titulos = new ArrayList<>();
        titulos.add("TODOS OS ESTUDOS");
        if(!ciclos.isEmpty()){
            for (Ciclo ciclo : ciclos){
                titulos.add(ciclo.getTitulo());
            }
        }
        pagerAdapter = new CicloFragmentPagerAdapter(getSupportFragmentManager(),titulos, tabLayout);
        pager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(pager);
        tabLayout.setTabTextColors(Color.DKGRAY,Color.WHITE);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimaryDark2));
        tvInfo = findViewById(R.id.tvCicloInfo);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                fab1.setVisibility(View.INVISIBLE);
                fab2.setVisibility(View.INVISIBLE);

                if(position == 0){
                    if(!ciclos.isEmpty()){
                        tvInfo.setVisibility(View.INVISIBLE);
                    }
                    fab1.setVisibility(View.INVISIBLE);
                    fab2.setVisibility(View.GONE);
                } else {
                    //se não há itens relacionados ao ciclo referente a posição
                    if(true){
                        tvInfo.setText("Ainda não há  itens nesse ciclo de estudos!");
                        tvInfo.setVisibility(View.VISIBLE);
                    }
                    //Se não
                    tvInfo.setVisibility(View.INVISIBLE);
                    fab1.setVisibility(View.GONE);
                    fab2.setVisibility(View.INVISIBLE);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void findFabs(){
        fab = (FloatingActionMenu) findViewById(R.id.fab);
        fab.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                //Toast.makeText(AreaDeTrabalhoEmGrupo.this, "Is menu Opened: " + (opened? "true" : "false"), Toast.LENGTH_SHORT).show();
            }
        });

        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Criar Etapa
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //novo ciclo
            }
        });
    }

    public void actionButton(View view){
        YoYo.with(Techniques.Pulse)
                .duration(700)
                .repeat(0)
                .playOn(view);
    }
}
