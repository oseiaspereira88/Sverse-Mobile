package com.example.oseias.sverse.OthersActivitys;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.oseias.sverse.Adapters.MyFragmentPagerTrabalhoEmGrupoAdapter;
import com.example.oseias.sverse.OthersClass.ArquivamentoIndexFragment.IndexFragement;
import com.example.oseias.sverse.SQLite.dao.ConfiguracaoDAO;
import com.example.oseias.sverse.SQLite.model.Configuracao;
import com.versaplications.prodesenvelopment.sverse.R;

public class AreaDeTrabalhoEmGrupo extends AppCompatActivity {
    private AppBarLayout appBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyFragmentPagerTrabalhoEmGrupoAdapter myFragmentPagerTrabalhoEmGrupoAdapter;
    private IndexFragement indexFragement;
    private int lastIndexFragment;
    private int defaultIndexPrimaryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_de_trabalho_em_grupo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeViews();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Criar Item", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void initializeViews() {
        appBar = (AppBarLayout) findViewById(R.id.appBarArea);
        tabLayout = new TabLayout(this);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        appBar.addView(tabLayout);
        viewPager = (ViewPager) findViewById(R.id.pager);
        myFragmentPagerTrabalhoEmGrupoAdapter = new MyFragmentPagerTrabalhoEmGrupoAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.titles_tabs_etapas));
        viewPager.setAdapter(myFragmentPagerTrabalhoEmGrupoAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.LTGRAY, Color.WHITE);

        //lerPredefinicoes();
        //viewPager.setCurrentItem(lastIndexFragment);
        //salvarCofiguracoes();
    }

    public void onDestroyView() {
        appBar.removeView(tabLayout);
        if(lastIndexFragment != defaultIndexPrimaryFragment){
            lastIndexFragment = defaultIndexPrimaryFragment;
        }
    }

    public void lerPredefinicoes(){
        ConfiguracaoDAO configDAO = new ConfiguracaoDAO(this);
        lastIndexFragment = configDAO.buscarConfig(Configuracao.LAST_INDEX_FRAGMENT).getvalorConfig();
        defaultIndexPrimaryFragment = 0;
    }

    public void salvarCofiguracoes(){
        ConfiguracaoDAO configsDAO = new ConfiguracaoDAO(this);
        configsDAO.atualizarConfig(new Configuracao(Configuracao.LAST_INDEX_FRAGMENT, 0));
        Toast.makeText(this, "O LastIndexFragment foi salvo...", Toast.LENGTH_SHORT).show();
    }
}
