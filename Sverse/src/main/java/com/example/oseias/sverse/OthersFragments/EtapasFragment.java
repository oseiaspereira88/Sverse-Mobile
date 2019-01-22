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

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.oseias.sverse.Interfaces.AllRecyclerViewOnClickListenerHack;
import com.example.oseias.sverse.OtherAdapters.EtapaAdapter;
import com.example.oseias.sverse.OthersClass.SimpleDividerItemDecoration;
import com.example.oseias.sverse.SQLite.model.Objetivo;
import com.versaplications.prodesenvelopment.sverse.R;

import java.util.ArrayList;

public class EtapasFragment extends Fragment implements AllRecyclerViewOnClickListenerHack {
    private RecyclerView rv;
    private ConstraintLayout clMore;
    private EtapaAdapter adapter;
    private ArrayList<Objetivo> etapa;
    private CardView cardTitulo;
    public ConstraintLayout areaExcluir;
    private TextView textTitulo;
    private ImageView imgAction;
    private ImageView imgDetalhe;
    private boolean isOpenMore = false;

    private String aux;
    int defaultColor = R.color.colorAccent;
    int aceptColor = R.color.green;
    int alertColor = R.color.darkred;
    boolean isDrag = false;
    boolean isDroped = true;
    int position;
    int defaultSize = 0;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_etapas, null); //->container, false
        initializeViews(container, rootView);
        return rootView;
    }

    public void initializeViews(ViewGroup container, View rootView) {
        View parent = (View) container.getParent();
        AppBarLayout barLayout = (AppBarLayout) parent.findViewById(R.id.appBarArea);
        TabLayout tabLayout = (TabLayout) barLayout.getChildAt(0);
        clMore = (ConstraintLayout) rootView.findViewById(R.id.clMore);
        cardTitulo = (CardView) rootView.findViewById(R.id.cardAll);
        areaExcluir = (ConstraintLayout) rootView.findViewById(R.id.areaExcluir);
        textTitulo = (TextView) rootView.findViewById(R.id.tvAll);
        textTitulo.setText(tabLayout.getTabAt(tabLayout.getSelectedTabPosition()).getText().toString());
        imgAction = (ImageView) rootView.findViewById(R.id.imgAction);
        imgDetalhe = (ImageView) rootView.findViewById(R.id.imgDetalhe);

        imgDetalhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isOpenMore) {
                    rv.setVisibility(View.INVISIBLE);
                    clMore.setVisibility(View.VISIBLE);
                    isOpenMore = true;
                    YoYo.with(Techniques.RotateIn)
                            .duration(300)
                            .repeat(0)
                            .playOn(v);

                    ImageView img = (ImageView) v;
                    img.setImageResource(R.drawable.ic_arrow_back);
                    YoYo.with(Techniques.FadeIn)
                            .duration(700)
                            .repeat(0)
                            .playOn(img);

                    YoYo.with(Techniques.FadeIn)
                            .duration(700)
                            .repeat(0)
                            .playOn(clMore);
                } else{
                    rv.setVisibility(View.VISIBLE);
                    clMore.setVisibility(View.INVISIBLE);
                    isOpenMore = false;
                    YoYo.with(Techniques.FadeIn)
                            .duration(300)
                            .repeat(0)
                            .playOn(v);

                    ImageView img = (ImageView) v;
                    img.setImageResource(R.drawable.ic_vertical_more);
                    YoYo.with(Techniques.RotateIn)
                            .duration(700)
                            .repeat(0)
                            .playOn(v);

                    YoYo.with(Techniques.FadeIn)
                            .duration(700)
                            .repeat(0)
                            .playOn(rv);
                    isOpenMore = false;
                }
            }
        });

        areaExcluir.setOnDragListener(new EtapaAdapter.MyOnDragListener());

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

        rv = (RecyclerView) rootView.findViewById(R.id.recyclerAll);
        rv.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager llm = (LinearLayoutManager) rv.getLayoutManager();
                adapter = (EtapaAdapter) rv.getAdapter();

                if (etapa.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    //Toast.makeText(getActivity(), "Voce chegou ao final da sua lista.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        rv.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));

        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        adapter = new EtapaAdapter(getActivity(), generateItens());
        adapter.setRecyclerViewOnClickListenerHack(this);
        rv.setAdapter(adapter);

    }

    public ArrayList<Objetivo> generateItens() {
        etapa = new ArrayList<>();
        etapa.add(new Objetivo(1, "ALL: Primeiro Objetivo", "Nosso primeira Objetivo será assim e assado.", 1, 100, "Em Andamento"));
        etapa.add(new Objetivo(2, "ALL: Primeiro Objetivo", "Nosso primeira Objetivo será assim e assado.", 2, 100, "Em Andamento"));
        etapa.add(new Objetivo(3, "ALL: Primeiro Objetivo", "Nosso primeira Objetivo será assim e assado.", 3, 100, "Em Andamento"));
        etapa.add(new Objetivo(4, "ALL: Primeiro Objetivo", "Nosso primeira Objetivo será assim e assado.", 4, 100, "Em Andamento"));
        etapa.add(new Objetivo(7, "ALL: Primeiro Objetivo", "Nosso primeira Objetivo será assim e assado.", 5, 100, "Em Andamento"));
        etapa.add(new Objetivo(6, "ALL: Primeiro Objetivo", "Nosso primeira Objetivo será assim e assado.", 6, 100, "Concluido"));
        return etapa;
    }


    @Override
    public void onClickListener(View view, int position) {
        adapter = (EtapaAdapter) rv.getAdapter();
        //adapter.removeItemList(position);
        //abrir Objetivo ou meta;
        Toast.makeText(getActivity(), "Vc clicou num item da lista Geral", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClickListener(View view, int position) {
        view.setVisibility(View.INVISIBLE);
        ClipData data = ClipData.newPlainText("simple_text", "text");
        View.DragShadowBuilder sb = new View.DragShadowBuilder(view);
        view.startDrag(data, sb, view, 0);

        cardTitulo.setBackgroundResource(alertColor);
        imgAction.setVisibility(View.VISIBLE);
        aux = textTitulo.getText().toString();
        textTitulo.setText("Solte aqui para Excluir");

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
                if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    return true;
                }
                return false;
            case DragEvent.ACTION_DRAG_ENTERED:
                ConstraintLayout clExcluir1 = (ConstraintLayout) view;
                if(clExcluir1 == areaExcluir) {
                    cardTitulo.setBackgroundResource(aceptColor);
                }
                break;
            case DragEvent.ACTION_DRAG_LOCATION:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                ConstraintLayout clExcluir2 = (ConstraintLayout) view;
                if(clExcluir2 == areaExcluir) {
                    cardTitulo.setBackgroundResource(alertColor);
                }
                break;
            case DragEvent.ACTION_DROP:
                ConstraintLayout clExcluir3 = (ConstraintLayout) view;
                if(clExcluir3 == areaExcluir) {
                    adapter.removeItemList(position);
                }
                if (adapter.getItemCount() == defaultSize) {
                    View viewDroped = (View) event.getLocalState();
                    viewDroped.setVisibility(View.VISIBLE);
                }
                cardTitulo.setBackgroundResource(defaultColor);
                imgAction.setVisibility(View.INVISIBLE);
                textTitulo.setText(aux);
                isDroped = true;
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

    public void resetarDefaults() {
        cardTitulo.setBackgroundResource(defaultColor);
        imgAction.setVisibility(View.INVISIBLE);
        isDrag = false;
        aux = "";
        position = 0;
        defaultSize = 0;
    }
}