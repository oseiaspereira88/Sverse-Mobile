package com.example.oseias.sverse.OthersActivitys;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.oseias.sverse.Adapters.MyFragmentPagerAdapter;
import com.example.oseias.sverse.OtherAdapters.AllEtapaAdapter;
import com.example.oseias.sverse.OtherAdapters.CicloAdapter;
import com.example.oseias.sverse.OthersClass.ArquivamentoIndexFragment.IndexFragement;
import com.example.oseias.sverse.SQLite.dao.EstudoDAO;
import com.example.oseias.sverse.SQLite.model.ItemDeEstudo;
import com.exemple.oseias.sverse.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CicloActivity extends AppCompatActivity {
    private FloatingActionMenu fab;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private IndexFragement indexFragement;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo);
        initVars();
    }

    private void initVars() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager = (ViewPager) findViewById(R.id.cicloPager);
        //myFragmentPagerAdapter = new MyFragmentPagerAdapter((), new String[]{"Mural", "Grupos de Estudo", "Bloco de Nota", "Notificações"}, tabLayout);
        //viewPager.setAdapter(myFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.DKGRAY,Color.WHITE);

        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) tabLayout.getLayoutParams();
        params.setScrollFlags
                (AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                        | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                        | AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP);
    }

    public void findFabs(){
        fab = (FloatingActionMenu) findViewById(R.id.fab);
        fab.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                //Toast.makeText(AreaDeTrabalhoEmGrupo.this, "Is menu Opened: " + (opened? "true" : "false"), Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Criar Etapa
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Adicionar Participantes
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
