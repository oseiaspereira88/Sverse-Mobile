package com.example.oseias.sverse.OthersFragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.oseias.sverse.Adapters.CicloFragmentPagerAdapter;
import com.example.oseias.sverse.OtherAdapters.CicloAdapter;
import com.example.oseias.sverse.OthersActivitys.CicloItemCreator;
import com.example.oseias.sverse.SQLite.dao.CicloDAO;
import com.example.oseias.sverse.SQLite.dao.EstudoDAO;
import com.example.oseias.sverse.SQLite.model.Ciclo;
import com.example.oseias.sverse.SQLite.model.CicloItem;
import com.exemple.oseias.sverse.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@SuppressLint("ValidFragment")
public class CicloFragment extends Fragment {
    private ArrayList<CicloItem> lista;
    private EstudoDAO estudoDAO;
    private FloatingActionMenu fab;
    private FloatingActionButton fab1, fab2;
    private CicloAdapter cicloAdapter;
    private CicloDAO cicloDAO;
    private ListView lv;
    private int cicloPosition;

    @SuppressLint("ValidFragment")
    public CicloFragment(int cicloPosition) {
        cicloPosition = cicloPosition;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ciclo, null);
        initializeViews(container, rootView);
        return rootView;
    }

    public void initializeViews(ViewGroup container, View rootView) {
        cicloDAO = new CicloDAO(getActivity());
        lista = new ArrayList<>();
        lv = new ListView(getContext());
        lv = rootView.findViewById(R.id.lvEstudos);

        //crieDados(); ou //Buscar dados do banco via CicloItemDAO

        if(!lista.isEmpty()){
            ordenarLista(lista);
            cicloAdapter = new CicloAdapter(getContext(), lista);
            lv.setAdapter(cicloAdapter);
        }
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
        fab = (FloatingActionMenu) getActivity().findViewById(R.id.fab);
        fab.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                //Toast.makeText(AreaDeTrabalhoEmGrupo.this, "Is menu Opened: " + (opened? "true" : "false"), Toast.LENGTH_SHORT).show();
            }
        });

        fab1 = (FloatingActionButton) getActivity().findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) getActivity().findViewById(R.id.fab2);
        ImageView bExcluirCiclo = getActivity().findViewById(R.id.imgExcluir);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Recebendo input do usuario para criar novo ciclo
                openInputDialogCreator();
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Abrindo Act CicloItemCreator
                Intent it = new Intent(getContext(), CicloItemCreator.class);
                Bundle b = new Bundle();
                b.putInt("cicloId", cicloDAO.returnAllCiclos().get(cicloPosition).get_id());
                getActivity().startActivity(it);
            }
        });
        bExcluirCiclo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!cicloDAO.returnAllCiclos().isEmpty() && cicloPosition != 0){
                    confirmDialogDelete(cicloDAO.returnAllCiclos().get(cicloPosition-1).get_id());
                }
            }
        });
    }

    public void crieDados(){
        lista.add(new CicloItem(1, 1, 13, 45, 2, 25, 10, "Observação...", 1, 1, 1));
        lista.add(new CicloItem(2, 2, 14, 00, 2, 15, 8, "Observação...", 2, 1, 1));
        lista.add(new CicloItem(3, 3, 15, 00, 3, 20, 5, "Observação...", 3, 1, 1));
        lista.add(new CicloItem(4, 4, 20, 20, 4, 10, 5, "Observação...", 4, 1, 1));
        lista.add(new CicloItem(5, 5, 17, 00, 1, 30, 0, "Observação...", 5, 1, 1));
        lista.add(new CicloItem(6, 6, 18, 00, 2, 15, 5, "Observação...", 6, 1, 1));
        lista.add(new CicloItem(7, 7, 1, 00, 3, 10, 5, "Observação...", 7, 1, 1));
        lista.add(new CicloItem(8, 1, 7, 30, 4, 15, 5, "Observação...", 2, 1, 1));
        lista.add(new CicloItem(9, 2, 8, 10, 1, 25, 0, "Observação...", 1, 1, 1));
        lista.get(0).setConcluido(true);
        lista.get(7).setConcluido(true);
    }

    // Para ordenar por dia, hora e minuto
    private static void ordenarLista(ArrayList<CicloItem> lista) {
        Collections.sort(lista, new Comparator<CicloItem>() {
            @Override
            public int compare(CicloItem o1, CicloItem o2) {
                return o1.getMinuto().compareTo(o2.getMinuto());
            }

        });
        Collections.sort(lista, new Comparator<CicloItem>() {
            @Override
            public int compare(CicloItem o1, CicloItem o2) {
                return o1.getHora().compareTo(o2.getHora());
            }

        });
        Collections.sort(lista, new Comparator<CicloItem>() {
            @Override
            public int compare(CicloItem o1, CicloItem o2) {
                return o1.getDiaDaSemana().compareTo(o2.getDiaDaSemana());
            }
        });
    }

    public void openInputDialogCreator(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Qual será o titulo?");

        View view = View.inflate(getActivity(), R.layout.input_dialog_ciclo_model, null);
        final EditText input = view.findViewById(R.id.editTitulo);
        builder.setView(view);

        // Set up the buttons
        builder.setPositiveButton("Criar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Criando novo ciclo:
                cicloDAO.salvarCiclo(new Ciclo(input.getText().toString(), 1));
                atualizarPaginador();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void confirmDialogDelete(final Integer id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Tem certeza de que deseja excluir esse ciclo?");
        builder.setMessage("Com essa ação voce deleta juntamente com o ciclo todos os itens relacionados.");

        View view = View.inflate(getActivity(), R.layout.input_dialog_ciclo_model, null);
        final EditText input = view.findViewById(R.id.editTitulo);
        builder.setView(view);

        // Set up the buttons
        builder.setPositiveButton("Excuir mesmo assim!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Excluindo e atualizando o paginador
                cicloDAO.removerCiclo(id);
                atualizarPaginador();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void atualizarPaginador() {
        ViewPager pager = (ViewPager) getActivity().findViewById(R.id.cicloPager);
        ArrayList<Ciclo> ciclos = cicloDAO.returnAllCiclos();
        ArrayList<String> titulos = new ArrayList<>();
        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tabLayout);
        //Setando os titulos e instanciando o pagerAdapter
        titulos.add("TODOS OS ESTUDOS");
        if(!ciclos.isEmpty()){
            for (Ciclo ciclo : ciclos){
                titulos.add(ciclo.getTitulo());
            }
        }
        CicloFragmentPagerAdapter pagerAdapter = new CicloFragmentPagerAdapter(getActivity().getSupportFragmentManager(),titulos, tabLayout);
        pager.setAdapter(pagerAdapter);
    }


}
