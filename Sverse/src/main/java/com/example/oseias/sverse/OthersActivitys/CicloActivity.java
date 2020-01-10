package com.example.oseias.sverse.OthersActivitys;

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
import com.example.oseias.sverse.OtherAdapters.AllEtapaAdapter;
import com.example.oseias.sverse.OtherAdapters.CicloAdapter;
import com.example.oseias.sverse.SQLite.dao.EstudoDAO;
import com.example.oseias.sverse.SQLite.model.ItemDeEstudo;
import com.exemple.oseias.sverse.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CicloActivity extends AppCompatActivity {
    ArrayList<ItemDeEstudo> lista;
    private FloatingActionMenu fab;
    EstudoDAO estudoDAO;
    CicloAdapter cicloAdapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo);
        lista = new ArrayList<>();
        lv = new ListView(this);
        lv = findViewById(R.id.lv9);
        crieDados();
        ordenarLista(lista);
        cicloAdapter = new CicloAdapter(this, lista);
        lv.setAdapter(cicloAdapter);
        findFabs();

        lv.setOnScrollListener(new ListView.OnScrollListener() {
            private int mLastFirstVisibleItem;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                if(mLastFirstVisibleItem<firstVisibleItem)
                {
                    fab.hideMenuButton(true);
                }
                if(mLastFirstVisibleItem>firstVisibleItem)
                {
                    fab.showMenuButton(true);
                }
                mLastFirstVisibleItem=firstVisibleItem;

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

    public void crieDados(){
        lista.add(new ItemDeEstudo(1, 1, 13, 45, 2, 25, 10, 1, 1));
        lista.add(new ItemDeEstudo(2, 2, 14, 00, 2, 15, 8, 2, 1));
        lista.add(new ItemDeEstudo(3, 3, 15, 00, 3, 20, 5, 3, 1));
        lista.add(new ItemDeEstudo(4, 4, 20, 20, 4, 10, 5, 4, 1));
        lista.add(new ItemDeEstudo(5, 5, 17, 00, 1, 30, 0, 5, 1));
        lista.add(new ItemDeEstudo(6, 6, 18, 00, 2, 15, 5, 6, 1));
        lista.add(new ItemDeEstudo(7, 7, 1, 00, 3, 10, 5, 7, 1));
        lista.add(new ItemDeEstudo(8, 1, 7, 30, 4, 15, 5, 2, 1));
        lista.add(new ItemDeEstudo(9, 2, 8, 10, 1, 25, 0, 1, 1));
        lista.get(0).setConcluido(true);
        lista.get(7).setConcluido(true);
    }

    // Para ordenar por dia, hora e minuto
    private static void ordenarLista(ArrayList<ItemDeEstudo> lista) {
        Collections.sort(lista, new Comparator<ItemDeEstudo>() {
            @Override
            public int compare(ItemDeEstudo o1, ItemDeEstudo o2) {
                return o1.getMinuto().compareTo(o2.getMinuto());
            }

        });
        Collections.sort(lista, new Comparator<ItemDeEstudo>() {
            @Override
            public int compare(ItemDeEstudo o1, ItemDeEstudo o2) {
                return o1.getHora().compareTo(o2.getHora());
            }

        });
        Collections.sort(lista, new Comparator<ItemDeEstudo>() {
            @Override
            public int compare(ItemDeEstudo o1, ItemDeEstudo o2) {
                return o1.getDia().compareTo(o2.getDia());
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
