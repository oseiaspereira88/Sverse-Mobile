package com.example.oseias.sverse.OthersFragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.versaplications.prodesenvelopment.sverse.R;

public class EtapasFragment extends Fragment {
//    private RecyclerView rv;
//    private ConstraintLayout clMore;
//    private AllEtapaAdapter adapter;
//    private ArrayList<Etapa> etapa;
//    private CardView cardTitulo;
//    public ConstraintLayout areaExcluir;
//    private TextView textTitulo;
//    private ImageView imgAction;
//    private ImageView imgDetalhe;
//    private boolean isOpenMore = false;
//
//    private String aux;
//    int defaultColor = R.color.colorAccent;
//    int aceptColor = R.color.green;
//    int alertColor = R.color.darkred;
//    boolean isDrag = false;
//    boolean isDroped = true;
//    int position;
//    int defaultSize = 0;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_etapas, null); //->container, false
        //initializeViews(container, rootView);
        return rootView;
    }


    /*
    @RequiresApi(api = Build.VERSION_CODES.M)
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

        areaExcluir.setOnDragListener(new AllEtapaAdapter.MyOnDragListener());

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

        FloatingActionMenu fab = getActivity().findViewById(R.id.fab);

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
                adapter = (AllEtapaAdapter) rv.getAdapter();

                if (etapa.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    //Toast.makeText(getActivity(), "Voce chegou ao final da sua lista.", Toast.LENGTH_SHORT).show();
                }

                if(dy>0){

                    fab.hideMenuButton(true);
                }else{
                    fab.showMenuButton(true);
                }
            }
        });

        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        adapter = new AllEtapaAdapter(getActivity(), generateItens());
        adapter.setRecyclerViewOnClickListenerHack(this);
        rv.setAdapter(adapter);
    }

    public ArrayList<Etapa> generateItens() {
        etapa = new ArrayList<>();
        etapa.add(new Etapa(1, "ALL: Primeiro Etapa", "Nosso primeira Etapa será assim e assado.", 1, 100, "Em Andamento"));
        etapa.add(new Etapa(2, "ALL: Primeiro Etapa", "Nosso primeira Etapa será assim e assado.", 2, 100, "Em Andamento"));
        etapa.add(new Etapa(3, "ALL: Primeiro Etapa", "Nosso primeira Etapa será assim e assado.", 3, 100, "Em Andamento"));
        etapa.add(new Etapa(4, "ALL: Primeiro Etapa", "Nosso primeira Etapa será assim e assado.", 4, 100, "Em Andamento"));
        etapa.add(new Etapa(7, "ALL: Primeiro Etapa", "Nosso primeira Etapa será assim e assado.", 5, 100, "Em Andamento"));
        etapa.add(new Etapa(6, "ALL: Primeiro Etapa", "Nosso primeira Etapa será assim e assado.", 6, 100, "Concluido"));
        return etapa;
    }


    @Override
    public void onClickListener(View view, int position) {
        adapter = (AllEtapaAdapter) rv.getAdapter();
        //adapter.removeItemList(position);
        //abrir Etapa ou meta;
        Toast.makeText(getActivity(), "Vc clicou num item da lista", Toast.LENGTH_SHORT).show();
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

    */
}