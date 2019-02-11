package com.example.oseias.sverse.OthersActivitys;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.oseias.sverse.Interfaces.RecyclerViewOnClickListenerHack;
import com.example.oseias.sverse.OtherAdapters.AllEtapaAdapter;
import com.example.oseias.sverse.OtherAdapters.EtapasConcluidasAdapter;
import com.example.oseias.sverse.OtherAdapters.EtapasEmAndamentoAdapter;
import com.example.oseias.sverse.OthersClass.ArquivamentoIndexFragment.IndexFragement;
import com.example.oseias.sverse.SQLite.dao.ConfiguracaoDAO;
import com.example.oseias.sverse.SQLite.model.Configuracao;
import com.example.oseias.sverse.SQLite.model.Etapa;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.versaplications.prodesenvelopment.sverse.R;

import java.util.ArrayList;

public class AreaDeTrabalhoEmGrupo extends AppCompatActivity implements RecyclerViewOnClickListenerHack {

    //General Atributes
    private FloatingActionMenu fab;
    private ConstraintLayout clMore;
    private RecyclerView rv1, rv2, rv3;
    private AllEtapaAdapter adapter1;
    private EtapasEmAndamentoAdapter adapter2;
    private EtapasConcluidasAdapter adapter3;
    private ArrayList<Etapa> etapas;
    private CardView cardTitulo1, cardTitulo2, cardTitulo3;
    private TextView textTitulo1, textTitulo2, textTitulo3;

    //AllEtapas Atributes
    public ConstraintLayout areaExcluir;
    private ImageView imgAction;
    private ImageView imgDetalhe;

    //EtapasEmAndamento Atributes
    public ConstraintLayout areaAcept;
    private ImageView imgAcept;

    //EtapasConcluidas Atributes
    public ConstraintLayout areaReset;
    private ImageView imgExcluir;


    //Vars Auxs
    private String aux;
    private boolean isOpenMore = false;
    int defaultColor = R.color.colorAccent;
    int aceptColor = R.color.green;
    int alertColor = R.color.darkred;
    boolean isDrag = false;
    boolean isDroped = true;
    int position;
    int defaultSize = 0;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_de_trabalho_em_grupo);

        findFabs();
        findAllViews();
        findEmAndamentoViews();
        findConcluidasViews();
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void findAllViews() {
        clMore = (ConstraintLayout) findViewById(R.id.clMore);
        areaExcluir = (ConstraintLayout) findViewById(R.id.areaExcluir);
        imgAction = (ImageView) findViewById(R.id.imgAction);

        cardTitulo1 = (CardView) findViewById(R.id.cardAll);
        textTitulo1 = (TextView) findViewById(R.id.tvAll);
        textTitulo1.setText("Todas");

        imgDetalhe = (ImageView) findViewById(R.id.imgDetalhe);

        imgDetalhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isOpenMore) {
                    rv1.setVisibility(View.INVISIBLE);
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
                    rv1.setVisibility(View.VISIBLE);
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
                            .playOn(rv1);
                    isOpenMore = false;
                }
            }
        });

        areaExcluir.setOnDragListener(new AllEtapaAdapter.MyOnDragListener());

        rv1 = (RecyclerView) findViewById(R.id.recyclerAll);
        rv1.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager llm = (LinearLayoutManager) rv1.getLayoutManager();
                adapter1 = (AllEtapaAdapter) rv1.getAdapter();

                if (etapas.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    //Toast.makeText(getActivity(), "Voce chegou ao final da sua lista.", Toast.LENGTH_SHORT).show();
                }

                if(dy>0){

                    fab.hideMenuButton(true);
                }else{
                    fab.showMenuButton(true);
                }
            }
        });

        rv1.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv1.setLayoutManager(llm);
        adapter1 = new AllEtapaAdapter(this, generateItens());
        adapter1.setRecyclerViewOnClickListenerHack(this);
        rv1.setAdapter(adapter1);
    }

    public void findEmAndamentoViews() {
        cardTitulo2 = (CardView) findViewById(R.id.cardEmAndamento);
        areaAcept = (ConstraintLayout) findViewById(R.id.areaAcept);
        textTitulo2 = (TextView) findViewById(R.id.tvEmAndamento);
        textTitulo2.setText("Em Andamento");
        imgAcept = (ImageView) findViewById(R.id.imgAcept);

        areaAcept.setOnDragListener(new EtapasEmAndamentoAdapter.MyOnDragListener());

        rv2 = (RecyclerView) findViewById(R.id.recyclerEmAndamento);
        rv2.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager llm = (LinearLayoutManager) rv2.getLayoutManager();
                adapter2 = (EtapasEmAndamentoAdapter) rv2.getAdapter();

                if (etapas.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    //Toast.makeText(getActivity(), "Voce chegou ao final da sua lista.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        rv2.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv2.setLayoutManager(llm);
        adapter2 = new EtapasEmAndamentoAdapter(this, generateItens());
        adapter2.setRecyclerViewOnClickListenerHack(this);
        rv2.setAdapter(adapter2);

    }

    public void findConcluidasViews() {
        areaReset = (ConstraintLayout) findViewById(R.id.areaReset);
        cardTitulo3 = (CardView) findViewById(R.id.cardConcluidos);
        textTitulo3 = (TextView) findViewById(R.id.tvConcluidos);
        textTitulo3.setText("Concluidas");
        imgExcluir = (ImageView) findViewById(R.id.imgReset);

        areaReset.setOnDragListener(new EtapasConcluidasAdapter.MyOnDragListener());

        rv3 = (RecyclerView) findViewById(R.id.recyclerConcluidos);
        rv3.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager llm = (LinearLayoutManager) rv3.getLayoutManager();
                adapter3 = (EtapasConcluidasAdapter) rv3.getAdapter();

                if (etapas.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    //Toast.makeText(getActivity(), "Voce chegou ao final da sua lista.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        rv3.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv3.setLayoutManager(llm);
        adapter3 = new EtapasConcluidasAdapter(this, generateItens());
        adapter3.setRecyclerViewOnClickListenerHack(this);
        rv3.setAdapter(adapter3);

    }

    public ArrayList<Etapa> generateItens() {
        etapas = new ArrayList<>();
        etapas.add(new Etapa(1, "Etapa Exemplo", "Nosso primeira etapa será assim e assado.", 1, 100, "Em Andamento"));
        etapas.add(new Etapa(2, "Etapa Exemplo", "Nosso primeira etapa será assim e assado.", 2, 100, "Em Andamento"));
        etapas.add(new Etapa(3, "Etapa Exemplo", "Nosso primeira etapa será assim e assado.", 3, 100, "Em Andamento"));
        etapas.add(new Etapa(4, "Etapa Exemplo", "Nosso primeira etapa será assim e assado.", 4, 100, "Em Andamento"));
        etapas.add(new Etapa(7, "Etapa Exemplo", "Nosso primeira etapa será assim e assado.", 5, 100, "Em Andamento"));
        etapas.add(new Etapa(6, "Etapa Exemplo", "Nosso primeira etapa será assim e assado.", 6, 100, "Concluido"));
        return etapas;
    }

    @Override
    public void onClickListener1(View view, int position) {
        adapter1 = (AllEtapaAdapter) rv1.getAdapter();
        //adapter.removeItemList(position);
        //abrir Etapa ou meta;
        Toast.makeText(this, "Vc clicou num etapa da Lista de Todas", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickListener2(View view, int position) {
        adapter2 = (EtapasEmAndamentoAdapter) rv2.getAdapter();
        //adapter.removeItemList(position);
        //abrir Etapa ou meta;
        Toast.makeText(this, "Vc clicou num etapa em andamento", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickListener3(View view, int position) {
        adapter3 = (EtapasConcluidasAdapter) rv3.getAdapter();
        //adapter.removeItemList(position);
        //abrir Etapa ou meta;
        Toast.makeText(this, "Vc clicou num etapa concluido", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClickListener1(View view, int position) {
        view.setVisibility(View.INVISIBLE);
        ClipData data = ClipData.newPlainText("simple_text", "text");
        View.DragShadowBuilder sb = new View.DragShadowBuilder(view);
        view.startDrag(data, sb, view, 0);

        cardTitulo1.setBackgroundResource(alertColor);
        imgAction.setVisibility(View.VISIBLE);
        aux = textTitulo1.getText().toString();
        textTitulo1.setText("Solte aqui para Excluir");

        defaultSize = adapter1.getItemCount();
        this.position = position;
        isDrag = true;
        isDroped = false;
    }

    @Override
    public void onLongClickListener2(View view, int position) {
        view.setVisibility(View.INVISIBLE);
        ClipData data = ClipData.newPlainText("simple_text", "text");
        View.DragShadowBuilder sb = new View.DragShadowBuilder(view);
        view.startDrag(data, sb, view, 0);

        cardTitulo2.setBackgroundResource(aceptColor);
        imgAcept.setVisibility(View.VISIBLE);
        aux = textTitulo2.getText().toString();
        textTitulo2.setText("Marque como Concluida");

        defaultSize = adapter2.getItemCount();
        this.position = position;
        isDrag = true;
        isDroped = false;
    }

    @Override
    public void onLongClickListener3(View view, int position) {
        view.setVisibility(View.INVISIBLE);
        ClipData data = ClipData.newPlainText("simple_text", "text");
        View.DragShadowBuilder sb = new View.DragShadowBuilder(view);
        view.startDrag(data, sb, view, 0);

        cardTitulo3.setBackgroundResource(aceptColor);
        imgExcluir.setVisibility(View.VISIBLE);
        aux = textTitulo3.getText().toString();
        textTitulo3.setText("Marque como em Andamento");

        defaultSize = adapter3.getItemCount();
        this.position = position;
        isDrag = true;
        isDroped = false;
    }

    @Override
    public boolean onDragListener1(View view, DragEvent event) {
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
                    cardTitulo1.setBackgroundResource(aceptColor);
                }
                break;
            case DragEvent.ACTION_DRAG_LOCATION:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                ConstraintLayout clExcluir2 = (ConstraintLayout) view;
                if(clExcluir2 == areaExcluir) {
                    cardTitulo1.setBackgroundResource(alertColor);
                }
                break;
            case DragEvent.ACTION_DROP:
                ConstraintLayout clExcluir3 = (ConstraintLayout) view;
                if(clExcluir3 == areaExcluir) {
                    adapter1.removeItemList(position);
                }
                if (adapter1.getItemCount() == defaultSize) {
                    View viewDroped = (View) event.getLocalState();
                    viewDroped.setVisibility(View.VISIBLE);
                }
                cardTitulo1.setBackgroundResource(defaultColor);
                imgAction.setVisibility(View.INVISIBLE);
                textTitulo1.setText(aux);
                isDroped = true;
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                if(!isDroped){
                    View viewDroped = (View) event.getLocalState();
                    viewDroped.setVisibility(View.VISIBLE);
                }
                resetarAllDefaults();
                break;
        }

        return true;
    }

    @Override
    public boolean onDragListener2(View view, DragEvent event) {
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
                    cardTitulo2.setBackgroundResource(defaultColor);
                }
                break;
            case DragEvent.ACTION_DRAG_LOCATION:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                ConstraintLayout cl2 = (ConstraintLayout) view;
                if(cl2 == areaAcept) {
                    cardTitulo2.setBackgroundResource(aceptColor);
                }
                break;
            case DragEvent.ACTION_DROP:
                ConstraintLayout cl3 = (ConstraintLayout) view;
                if(cl3 == areaAcept) {
                    adapter2.removeItemList(position);
                }
                if(adapter2.getItemCount() == defaultSize){
                    View viewDroped = (View) event.getLocalState();
                    viewDroped.setVisibility(View.VISIBLE);
                }
                cardTitulo2.setBackgroundResource(defaultColor);
                imgAcept.setVisibility(View.INVISIBLE);
                textTitulo2.setText(aux);
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                if(!isDroped){
                    View viewDroped = (View) event.getLocalState();
                    viewDroped.setVisibility(View.VISIBLE);
                }
                resetarEmAndamentoDefaults();
                break;
        }

        return true;
    }

    @Override
    public boolean onDragListener3(View view, DragEvent event) {
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
                    cardTitulo3.setBackgroundResource(defaultColor);
                }
                break;
            case DragEvent.ACTION_DRAG_LOCATION:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                ConstraintLayout cl2 = (ConstraintLayout) view;
                if(cl2 == areaAcept) {
                    cardTitulo3.setBackgroundResource(aceptColor);
                }
                break;
            case DragEvent.ACTION_DROP:
                ConstraintLayout cl3 = (ConstraintLayout) view;
                if(cl3 == areaAcept) {
                    adapter3.removeItemList(position);
                }
                if(adapter3.getItemCount() == defaultSize){
                    View viewDroped = (View) event.getLocalState();
                    viewDroped.setVisibility(View.VISIBLE);
                }
                cardTitulo3.setBackgroundResource(defaultColor);
                imgAcept.setVisibility(View.INVISIBLE);
                textTitulo3.setText(aux);
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                if(!isDroped){
                    View viewDroped = (View) event.getLocalState();
                    viewDroped.setVisibility(View.VISIBLE);
                }
                resetarConcluidasDefaults();
                break;
        }

        return true;
    }

    public void resetarAllDefaults() {
        cardTitulo1.setBackgroundResource(defaultColor);
        imgAction.setVisibility(View.INVISIBLE);
        isDrag = false;
        aux = "";
        position = 0;
        defaultSize = 0;
    }

    public void resetarEmAndamentoDefaults(){
        cardTitulo2.setBackgroundResource(defaultColor);
        imgAcept.setVisibility(View.INVISIBLE);
        isDrag = false;
        aux = "";
        position = 0;
        defaultSize = 0;
    }

    public void resetarConcluidasDefaults(){
        cardTitulo3.setBackgroundResource(defaultColor);
        imgExcluir.setVisibility(View.INVISIBLE);
        isDrag = false;
        aux = "";
        position = 0;
        defaultSize = 0;
    }

    public void salvarCofiguracoes(){
        ConfiguracaoDAO configsDAO = new ConfiguracaoDAO(this);
        configsDAO.atualizarConfig(new Configuracao(Configuracao.LAST_INDEX_FRAGMENT, 0));
        //Toast.makeText(this, "O LastIndexFragment foi salvo...", Toast.LENGTH_SHORT).show();
    }
}
