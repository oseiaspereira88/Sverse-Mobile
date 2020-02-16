package com.example.oseias.sverse.OtherAdapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.oseias.sverse.SQLite.dao.ContainerDAO;
import com.example.oseias.sverse.SQLite.model.CicloItem;
import com.example.oseias.sverse.SQLite.model.Container;
import com.exemple.oseias.sverse.R;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by oseias on 18/01/2020.
 */

public class ItensPomodoroAdapter extends BaseAdapter {
    private ImageView imgYes, imgBGItem, imgItem;
    private ConstraintLayout cardItem;
    private ArrayList<CicloItem> itens;
    private ContainerDAO containerDAO;
    private ArrayList<Container> containers;
    private boolean isLongeClick;
    private Context ctx;

    public ItensPomodoroAdapter(Context ctx, ArrayList<CicloItem> itens) {
        this.ctx = ctx;
        this.itens = itens;
        isLongeClick = false;
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public String weekDay(Calendar cal) {
        return new DateFormatSymbols().getWeekdays()[cal.get(Calendar.DAY_OF_WEEK)];
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        view = LayoutInflater.from(ctx).inflate(R.layout.pomodoro_item_model, null);
        findAllViews(view);
        initAllViews(view, i);
        return view;
    }

    private void findAllViews(View view) {
        imgYes = view.findViewById(R.id.imgYes);
        imgBGItem = view.findViewById(R.id.imgBGItem);
        imgItem = view.findViewById(R.id.imgItem);
        cardItem = view.findViewById(R.id.cardItem);
    }

    private void initAllViews(View view, int i) {

        if(i==itens.size()-1){
            cardItem.setPadding(0, 0, 0, 42);
        }

        containerDAO = new ContainerDAO(ctx);
        containers = containerDAO.listarContainers();
        CicloItem item = itens.get(i);
        Container container = new Container(
                "Indefinido",
                "Indefinido",
                "Indefinido",
                0,
                0,
                0,
                0,
                "Indefinido",
                "Indefinido",
                0,
                "Indefinido",
                "Indefinido");

        for(Container c : containers){
            if(c.get_id() == item.getIdContainer()){
                container = c;
            }
        }

        //imgItem.setImageResource(container.getImageContainer());
        imgItem.setImageResource(R.mipmap.ic_play_pomodoro);

        ArrayList<String> dias = listarDias();

        //Seta bgItem como Green e dá yes no estudo
        if(item.isConcluido()){
            imgBGItem.setImageResource(R.mipmap.ic_item_green);
            imgYes.setVisibility(View.VISIBLE);
        } else{
            imgYes.setVisibility(View.INVISIBLE);
        }

        //verifica o momento do item e seta a cor de acordo com o dia atual
        //(se já passou e não foi estudado, se já foi estudado, ou se ainda não foi estudado)
        verificarEstadoDoItem(item, dias);

        //setando OnClick
        //Que da reopen na MainActivity predefinindo as configs da ferramenta de Pomodoro no MuralFragment
        imgItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLongeClick) {
                    isLongeClick = false;
                } else {
                    YoYo.with(Techniques.Landing)
                            .duration(700)
                            .repeat(0)
                            .playOn(v);

                    //Setar item na ferramenta de Pomodoro.

                }
            }
        });
    }

    private void verificarEstadoDoItem(CicloItem item, ArrayList<String> dias) {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        String weekDay = weekDay(calendar);
        int diaAtual = dias.indexOf(weekDay)+1;

        if(item.getDiaDaSemana() < diaAtual && !item.isConcluido()){
            imgBGItem.setImageResource(R.mipmap.ic_item_red);
        } else if (item.getDiaDaSemana() > diaAtual && !item.isConcluido()){
            imgBGItem.setImageResource(R.mipmap.ic_item_blue2);
        } else if (item.getDiaDaSemana() == diaAtual && !item.isConcluido()){
            if(item.getHora() < date.getHours() && !item.isConcluido()){
                imgBGItem.setImageResource(R.mipmap.ic_item_red);
            } else if (item.getHora() > date.getHours() && !item.isConcluido()){
                imgBGItem.setImageResource(R.mipmap.ic_item_blue2);
            } else if (item.getHora() == date.getHours()){
                if(item.getMinuto() < date.getMinutes() && !item.isConcluido()){
                    imgBGItem.setImageResource(R.mipmap.ic_item_red);
                } else if (item.getMinuto() > date.getMinutes() && !item.isConcluido()){
                    imgBGItem.setImageResource(R.mipmap.ic_item_red);
                } else if(item.getMinuto() == date.getMinutes()){
                    imgBGItem.setImageResource(R.mipmap.ic_item_blue2);
                }
            }
        }
    }

    private ArrayList<String> listarDias() {
        ArrayList<String> dias = new ArrayList<>();
        dias.add("domingo");
        dias.add("segunda");
        dias.add("terça");
        dias.add("quarta");
        dias.add("quinta");
        dias.add("sexta");
        dias.add("sábado");
        return dias;
    }

    public void actionButton(View view){
        YoYo.with(Techniques.Pulse)
                .duration(700)
                .repeat(0)
                .playOn(view);
    }
}
