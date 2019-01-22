package com.example.oseias.sverse.OthersFragments;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oseias.sverse.Interfaces.EmAndamentoRecyclerViewOnClickListenerHack;
import com.example.oseias.sverse.OtherAdapters.EtapasEmAndamentoAdapter;
import com.example.oseias.sverse.OthersClass.SimpleDividerItemDecoration;
import com.example.oseias.sverse.SQLite.model.Objetivo;
import com.versaplications.prodesenvelopment.sverse.R;

import java.util.ArrayList;

public class FragmentEtapasEmAndamento extends Fragment implements EmAndamentoRecyclerViewOnClickListenerHack {
    private RecyclerView rv;
    EtapasEmAndamentoAdapter adapter;
    private ArrayList<Objetivo> objetivos;
    private CardView cardTitulo;
    public ConstraintLayout areaAcept;
    private TextView textTitulo;
    private ImageView imgExcluir;

    private String aux;
    int defaultColor = R.color.colorAccent;
    int aceptColor = R.color.green;
    int alertColor = R.color.darkred;
    boolean isDrag = false;
    boolean isDroped = true;
    int position;
    int defaultSize = 0;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_etapas_em_andamento, null); //->container, false
        initializeViews(container, rootView);
        return rootView;
    }

    public void initializeViews(ViewGroup container, View rootView) {
        View parent = (View) container.getParent();
        AppBarLayout barLayout = (AppBarLayout) parent.findViewById(R.id.appBarArea);
        TabLayout tabLayout = (TabLayout) barLayout.getChildAt(0);
        cardTitulo = (CardView) rootView.findViewById(R.id.cardEmAndamento);
        areaAcept = (ConstraintLayout) rootView.findViewById(R.id.areaAcept);
        textTitulo = (TextView) rootView.findViewById(R.id.tvEmAndamento);
        textTitulo.setText(tabLayout.getTabAt(tabLayout.getSelectedTabPosition()).getText().toString());
        imgExcluir = (ImageView) rootView.findViewById(R.id.imgAcept);

        areaAcept.setOnDragListener(new EtapasEmAndamentoAdapter.MyOnDragListener());

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                textTitulo.setText(tabLayout.getTabAt(tabLayout.getSelectedTabPosition()).getText().toString());
                resetarDefaults();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        rv = (RecyclerView) rootView.findViewById(R.id.recyclerEmAndamento);
        rv.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager llm = (LinearLayoutManager) rv.getLayoutManager();
                adapter = (EtapasEmAndamentoAdapter) rv.getAdapter();

                if (objetivos.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    //Toast.makeText(getActivity(), "Voce chegou ao final da sua lista.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        rv.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));

        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        adapter = new EtapasEmAndamentoAdapter(getActivity(), generateItens());
        adapter.setRecyclerViewOnClickListenerHack(this);
        rv.setAdapter(adapter);

    }

    public ArrayList<Objetivo> generateItens() {
        objetivos = new ArrayList<>();
        objetivos.add(new Objetivo(1, "Primeiro Objetivo", "Nosso primeira Objetivo será assim e assado.", 1, 100, "Em Andamento"));
        objetivos.add(new Objetivo(2, "Segundo Objetivo", "Nosso primeira Objetivo será assim e assado.", 2, 100, "Em Andamento"));
        objetivos.add(new Objetivo(3, "Terceiro Objetivo", "Nosso primeira Objetivo será assim e assado.", 3, 100, "Em Andamento"));
        objetivos.add(new Objetivo(4, "Quarto Objetivo", "Nosso primeira Objetivo será assim e assado.", 4, 100, "Em Andamento"));
        objetivos.add(new Objetivo(7, "Quinto Objetivo", "Nosso primeira Objetivo será assim e assado.", 5, 100, "Em Andamento"));
        //objetivos.add(new Objetivo(6, "Sexto Objetivo", "Nosso primeira Objetivo será assim e assado.", 6, 100, "Concluido"));
        return objetivos;
    }


    @Override
    public void onClickListener(View view, int position) {
        adapter = (EtapasEmAndamentoAdapter) rv.getAdapter();
        //adapter.removeItemList(position);
        //abrir Objetivo ou meta;
        Toast.makeText(getActivity(), "Vc clicou num item em andamento", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClickListener(View view, int position) {
        view.setVisibility(View.INVISIBLE);
        ClipData data = ClipData.newPlainText("simple_text", "text");
        View.DragShadowBuilder sb = new View.DragShadowBuilder(view);
        view.startDrag(data, sb, view, 0);

        cardTitulo.setBackgroundResource(aceptColor);
        imgExcluir.setVisibility(View.VISIBLE);
        aux = textTitulo.getText().toString();
        textTitulo.setText("Marque como Concluida");

        defaultSize = adapter.getItemCount();
        this.position = position;
        isDrag = true;
        isDroped = false;
    }

    @Override
    public boolean onDragListener(View view, DragEvent event) {
        int action = event.getAction();
        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                if(event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)){
                    return true;
                }
                return false;
            case DragEvent.ACTION_DRAG_ENTERED:
                ConstraintLayout cl1 = (ConstraintLayout) view;
                if(cl1 == areaAcept) {
                    cardTitulo.setBackgroundResource(defaultColor);
                }
                break;
            case DragEvent.ACTION_DRAG_LOCATION:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                ConstraintLayout cl2 = (ConstraintLayout) view;
                if(cl2 == areaAcept) {
                    cardTitulo.setBackgroundResource(aceptColor);
                }
                break;
            case DragEvent.ACTION_DROP:
                ConstraintLayout cl3 = (ConstraintLayout) view;
                if(cl3 == areaAcept) {
                    adapter.removeItemList(position);
                }
                if(adapter.getItemCount() == defaultSize){
                    View viewDroped = (View) event.getLocalState();
                    viewDroped.setVisibility(View.VISIBLE);
                }
                cardTitulo.setBackgroundResource(defaultColor);
                imgExcluir.setVisibility(View.INVISIBLE);
                textTitulo.setText(aux);
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                if(!isDroped){
                    View viewDroped = (View) event.getLocalState();
                    viewDroped.setVisibility(View.VISIBLE);
                }
                resetarDefaults();
                break;
        }

        return true;
    }

    public void resetarDefaults(){
        cardTitulo.setBackgroundResource(defaultColor);
        imgExcluir.setVisibility(View.INVISIBLE);
        isDrag = false;
        aux = "";
        position = 0;
        defaultSize = 0;
    }
}
