package com.example.oseias.sverse.OthersActivitys;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.LayerDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.oseias.sverse.Adapters.CicloFragmentPagerAdapter;
import com.example.oseias.sverse.SQLite.dao.CicloDAO;
import com.example.oseias.sverse.SQLite.model.Ciclo;
import com.exemple.oseias.sverse.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CicloActivity extends AppCompatActivity {
    private FloatingActionMenu fab;
    private FloatingActionButton fabCicloCreate, fabItemCreate;
    private CicloDAO cicloDAO;
    private ArrayList<Ciclo> ciclos;
    private CicloFragmentPagerAdapter pagerAdapter;
    private ViewPager pager;
    private TabLayout tabLayout;
    private ConstraintLayout bgButtons;
    private ImageView imgBGTitle;
    private ImageView imgBGTabs;
    private TextView tvInfo;
    private ImageView imgExcluirCiclo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo);

        findAllViews();
        setFabsListeners();
        initAllViews();
    }

    public void findAllViews() {
        fab = (FloatingActionMenu) findViewById(R.id.fab);
        fabCicloCreate = (FloatingActionButton) findViewById(R.id.fab1);
        fabItemCreate = (FloatingActionButton) findViewById(R.id.fab2);
        cicloDAO = new CicloDAO(this);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        bgButtons = (ConstraintLayout) findViewById(R.id.bgButtons);
        imgBGTitle = (ImageView) findViewById(R.id.imgBGTitle);
        imgBGTabs = (ImageView) findViewById(R.id.imgBGTabs);
        pager = (ViewPager) findViewById(R.id.cicloPager);
        tvInfo = findViewById(R.id.tvCicloInfo);
        imgExcluirCiclo = findViewById(R.id.imgExcluirCiclo);
    }

    public void setFabsListeners() {
        fab.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {

            }
        });
        fabCicloCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Recebendo input do usuario por dialog e criando novo ciclo ou concelando o dialog
                openInputDialogCreator();
            }
        });
    }

    public void initAllViews() {
        ciclos = cicloDAO.returnAllCiclos();
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        //Orndenando os titulos na lista e instanciando o pagerAdapter e o viewPager
        pagerAdapter = new CicloFragmentPagerAdapter(getSupportFragmentManager(), getOrdenedAllTitles(), tabLayout);
        pager.setAdapter(pagerAdapter);

        //Atualizando TabLayout e setando o pager
        updateTabLayoutColor(getResources().getColor(R.color.colorPrimaryDark2));
        tabLayout.setupWithViewPager(pager);

        //SetandoOnPageChangeListener
        pager.addOnPageChangeListener(getOnPageChangeListener());
    }

    private ViewPager.OnPageChangeListener getOnPageChangeListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    updateTabLayoutColor(getResources().getColor(R.color.colorPrimaryDark2));
                    fabCicloCreate.setVisibility(View.INVISIBLE);
                    fabItemCreate.setVisibility(View.GONE);
                } else {
                    int cicloId = ciclos.get(position-1).get_id();

                    //atualizando as cores do Layout
                    updateTabLayoutColor(ciclos.get(position - 1).getCor());

                    //setando novo listener para o button excluir
                    imgExcluirCiclo.setOnClickListener(getNewListenerDelete(cicloId));

                    //setando novo listener para o fabItemCreate
                    fabItemCreate.setOnClickListener(getNewListenerFabItemCreate(cicloId));

                    //se não há itens relacionados ao ciclo referente a posição
                    if (true) {
                        tvInfo.setText("Ainda não há  itens nesse ciclo de estudos!");
                        tvInfo.setVisibility(View.VISIBLE);
                    } else {
                        tvInfo.setVisibility(View.INVISIBLE);
                    }
                    fabCicloCreate.setVisibility(View.GONE);
                    fabItemCreate.setVisibility(View.INVISIBLE);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        };
    }

    public void openInputDialogCreator() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Qual será o titulo?");

        View view = View.inflate(this, R.layout.input_name_and_color_dialog_model, null);
        final EditText input = view.findViewById(R.id.editTitulo);

        final Map<ImageView, ImageView> map = new HashMap<ImageView, ImageView>();
        map.put((ImageView) view.findViewById(R.id.imgBGItem1), (ImageView) view.findViewById(R.id.yesBGItem1));
        map.put((ImageView) view.findViewById(R.id.imgBGItem2), (ImageView) view.findViewById(R.id.yesBGItem2));
        map.put((ImageView) view.findViewById(R.id.imgBGItem3), (ImageView) view.findViewById(R.id.yesBGItem3));
        map.put((ImageView) view.findViewById(R.id.imgBGItem4), (ImageView) view.findViewById(R.id.yesBGItem4));
        map.put((ImageView) view.findViewById(R.id.imgBGItem5), (ImageView) view.findViewById(R.id.yesBGItem5));
        map.put((ImageView) view.findViewById(R.id.imgBGItem6), (ImageView) view.findViewById(R.id.yesBGItem6));
        map.put((ImageView) view.findViewById(R.id.imgBGItem7), (ImageView) view.findViewById(R.id.yesBGItem7));
        map.put((ImageView) view.findViewById(R.id.imgBGItem8), (ImageView) view.findViewById(R.id.yesBGItem8));
        map.put((ImageView) view.findViewById(R.id.imgBGItem9), (ImageView) view.findViewById(R.id.yesBGItem9));

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imgYes = map.get((ImageView) v);
                ImageView imgBGITem = (ImageView) v;

                if (imgYes.getVisibility() == View.INVISIBLE) {
                    Set<Map.Entry<ImageView, ImageView>> set = map.entrySet();
                    Iterator it = set.iterator();

                    while (it.hasNext()) {
                        Map.Entry<ImageView, ImageView> entry = (Map.Entry) it.next();
                        entry.getValue().setVisibility(View.INVISIBLE);
                        entry.getValue().setClickable(true);
                    }
                    imgYes.setVisibility(View.VISIBLE);
                    imgBGITem.setClickable(false);
                }
            }
        };

        Set<Map.Entry<ImageView, ImageView>> set = map.entrySet();
        Iterator it = set.iterator();

        while (it.hasNext()) {
            Map.Entry<ImageView, ImageView> entry = (Map.Entry) it.next();
            entry.getKey().setOnClickListener(listener);
        }

        builder.setView(view);

        // Set up the buttons
        builder.setPositiveButton("Criar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //setando as cor a partir da detecção de qual imgYes está Visible
                Set<Map.Entry<ImageView, ImageView>> set = map.entrySet();
                Iterator it = set.iterator();

                int cor = Color.GREEN;
                while (it.hasNext()) {
                    Map.Entry<ImageView, ImageView> entry = (Map.Entry) it.next();
                    if (entry.getValue().getVisibility() == View.VISIBLE) {

                        //Tratando o Drawable
                        LayerDrawable layerDrawable = ((LayerDrawable) entry.getKey().getDrawable());
                        final int width = layerDrawable.getIntrinsicWidth();
                        final int height = layerDrawable.getIntrinsicHeight();

                        final Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                        layerDrawable.setBounds(0, 0, width, height);
                        layerDrawable.draw(new Canvas(bitmap));

                        //Coordenada x do centro
                        int x = bitmap.getWidth() / 2;
                        //Coordenada y do centro
                        int y = bitmap.getHeight() / 2;

                        cor = bitmap.getPixel(x, y);
                    }
                }

                //Criando novo ciclo:
                cicloDAO.salvarCiclo(new Ciclo(input.getText().toString(), cor, 1));
                atualizarPaginador();

                //retraindo o fab
                fab.toggle(false);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private View.OnClickListener getNewListenerDelete(int id) {
        final Context ctx = this;

        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add animation
                actionButton(view);

                //cria e mostra confirmDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setTitle("Tem certeza que deseja excluir?");
                builder.setMessage("Com essa ação voce deleta juntamente com o ciclo todos os itens relacionados.")
                        .setPositiveButton("Excuir mesmo assim!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Excluindo e atualizando o paginador
                                cicloDAO.removerCiclo(id);
                                atualizarPaginador();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                // Create the AlertDialog object and show it
                builder.create().show();
            }
        };
    }

    private View.OnClickListener getNewListenerFabItemCreate(final int id){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Abrindo Act CicloItemCreator
                Intent it = new Intent(getBaseContext(), CicloItemCreator.class);
                Bundle b = new Bundle();
                b.putInt("cicloId", id);
                startActivity(it);
            }
        };
    }

    private void atualizarPaginador() {
        ciclos = cicloDAO.returnAllCiclos();
        ArrayList<String> titulos = new ArrayList<>();

        //Setando os titulos e instanciando o pagerAdapter
        titulos.add("TODOS OS ESTUDOS");
        if (!ciclos.isEmpty()) {
            for (Ciclo ciclo : ciclos) {
                titulos.add(ciclo.getTitulo());
            }
        }
        pagerAdapter = new CicloFragmentPagerAdapter(getSupportFragmentManager(), titulos, tabLayout);
        pager.setAdapter(pagerAdapter);
    }


    public void updateTabLayoutColor(int cor) {
        tabLayout.setTabTextColors(Color.DKGRAY, Color.WHITE);
        tabLayout.setSelectedTabIndicatorColor(cor);
        imgBGTabs.setBackgroundColor(cor);
        bgButtons.setBackgroundColor(cor);
        imgBGTitle.setBackgroundColor(cor);
        fab.setMenuButtonColorNormal(cor);
        fabCicloCreate.setColorNormal(cor);
        fabItemCreate.setColorNormal(cor);
        getWindow().setStatusBarColor(cor);
    }

    private ArrayList<String> getOrdenedAllTitles() {
        ArrayList<String> titulos = new ArrayList<>();
        titulos.add("TODOS OS ESTUDOS");
        if (!ciclos.isEmpty()) {
            for (Ciclo ciclo : ciclos) {
                titulos.add(ciclo.getTitulo());
            }
        }
        return titulos;
    }

    public void actionButton(View view) {
        YoYo.with(Techniques.Pulse)
                .duration(700)
                .repeat(0)
                .playOn(view);
    }
}
