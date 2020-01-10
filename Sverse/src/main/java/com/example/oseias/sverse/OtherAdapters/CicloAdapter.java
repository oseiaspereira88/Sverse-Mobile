package com.example.oseias.sverse.OtherAdapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.oseias.sverse.SQLite.dao.ContainerDAO;
import com.example.oseias.sverse.SQLite.model.Container;
import com.example.oseias.sverse.SQLite.model.ItemDeEstudo;
import com.exemple.oseias.sverse.R;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by oseias on 03/04/2018.
 */

public class CicloAdapter extends BaseAdapter {
    Context ctx;
    ArrayList<ItemDeEstudo> itens;
    ContainerDAO containerDAO;
    ArrayList<Container> containers;
    boolean isLongeClick;

    public CicloAdapter(Context ctx, ArrayList<ItemDeEstudo> itens) {
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
        view = LayoutInflater.from(ctx).inflate(R.layout.ciclo_item_model, null);
        ImageView imgSegmento1 = view.findViewById(R.id.imgSegmento1);
        ImageView imgSegmento2 = view.findViewById(R.id.imgSegmento2);
        ImageView imgYes = view.findViewById(R.id.imgYes);
        ImageView imgBGItem = view.findViewById(R.id.imgBGItem);
        final ImageView imgItem = view.findViewById(R.id.imgItem);
        TextView tvDia = (TextView) view.findViewById(R.id.tvDia);
        TextView tvHora = (TextView) view.findViewById(R.id.tvHora);
        TextView tvNome = (TextView) view.findViewById(R.id.tvNome);
        CardView cardItem = (CardView) view.findViewById(R.id.cardItem);

        containerDAO = new ContainerDAO(ctx);
        containers = containerDAO.listarContainers();
        ItemDeEstudo item = itens.get(i);
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

        tvNome.setText(container.getName());
        //imgItem.setImageResource(container.getImageContainer());
        imgItem.setImageResource(R.mipmap.ic_play_pomodoro);

        ArrayList<String> dias = new ArrayList<>();
        dias.add("domingo");
        dias.add("segunda");
        dias.add("terça");
        dias.add("quarta");
        dias.add("quinta");
        dias.add("sexta");
        dias.add("sábado");
        tvDia.setText(dias.get(item.getDia()-1).toString());

        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        String weekDay = weekDay(calendar);

        int diaAtual = dias.indexOf(weekDay)+1;

        if(item.isConcluido()){
            cardItem.setCardBackgroundColor(Color.GREEN);
            imgBGItem.setImageResource(R.mipmap.ic_item_green);
            imgYes.setVisibility(View.VISIBLE);
        } else{
            imgYes.setVisibility(View.INVISIBLE);
        }

        Toast.makeText(ctx, "Para o item " + i + "temos: diaAtual: " + diaAtual, Toast.LENGTH_LONG).show();

        if(item.getDia() < diaAtual && !item.isConcluido()){
            cardItem.setCardBackgroundColor(Color.RED);
            imgBGItem.setImageResource(R.mipmap.ic_item_red);
        } else if (item.getDia() > diaAtual && !item.isConcluido()){
            cardItem.setCardBackgroundColor(Color.BLUE);
            imgBGItem.setImageResource(R.mipmap.ic_item_blue2);
        } else if (item.getDia() == diaAtual && !item.isConcluido()){
            if(item.getHora() < date.getHours() && !item.isConcluido()){
                cardItem.setCardBackgroundColor(Color.RED);
                imgBGItem.setImageResource(R.mipmap.ic_item_red);
            } else if (item.getHora() > date.getHours() && !item.isConcluido()){
                cardItem.setCardBackgroundColor(Color.BLUE);
                imgBGItem.setImageResource(R.mipmap.ic_item_blue2);
            } else if (item.getHora() == date.getHours()){
                if(item.getMinuto() < date.getMinutes() && !item.isConcluido()){
                    cardItem.setCardBackgroundColor(Color.RED);
                    imgBGItem.setImageResource(R.mipmap.ic_item_red);
                } else if (item.getMinuto() > date.getMinutes() && !item.isConcluido()){
                    cardItem.setCardBackgroundColor(Color.BLUE);
                    imgBGItem.setImageResource(R.mipmap.ic_item_red);
                } else if(item.getMinuto() == date.getMinutes()){
                    cardItem.setCardBackgroundColor(Color.YELLOW);
                    imgBGItem.setImageResource(R.mipmap.ic_item_blue2);
                }
            }
        }

        tvHora.setText(item.getHora()<10 ? "0" + item.getHora().toString() : item.getHora().toString());
        tvHora.setText(item.getMinuto()<10 ? tvHora.getText() + ":0" + item.getMinuto().toString() :tvHora.getText() + ":" + item.getMinuto().toString());

        if(i == 0){
            imgSegmento1.setVisibility(View.INVISIBLE);
        } else if (i == itens.size()-1){
            imgSegmento2.setVisibility(View.INVISIBLE);
        }

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                isLongeClick = true;
                YoYo.with(Techniques.Wave)
                        .duration(700)
                        .repeat(0)
                        .playOn(v);

                //Abrir activity CycleItemCreator passando o id do item em questão para edição.
                /*Intent it = new Intent(ctx, CriadorDeNotas.class);
                    Bundle b = new Bundle();
                    b.putInt("id", finalI +1);
                    it.putExtra("id", b);
                    ctx.startActivity(it);*/
                return false;
            }
        });

        int finalI = i;
        imgItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLongeClick) {
                    isLongeClick = false;
                } else {
                    YoYo.with(Techniques.Landing)
                            .duration(700)
                            .repeat(0)
                            .playOn(imgItem);

                    //Abrir a ferramenta de Pomodoro (na MainActivity) com as info do item selected.
                    /*Intent it = new Intent(ctx, CriadorDeNotas.class);
                    Bundle b = new Bundle();
                    b.putInt("id", finalI +1);
                    it.putExtra("id", b);
                    ctx.startActivity(it);*/
                }
            }
        });

        return view;
    }

    public void actionButton(View view){
        YoYo.with(Techniques.Pulse)
                .duration(700)
                .repeat(0)
                .playOn(view);
    }
}
