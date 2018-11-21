package com.example.oseias.sverse.OtherAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.oseias.sverse.OthersClass.Item;
import com.example.oseias.sverse.OthersClass.UnidadeDeEstudo;
import com.versaplications.prodesenvelopment.sverse.R;
import java.util.ArrayList;

/**
 * Created by oseias on 03/04/2018.
 */

public class CicloAdapter extends BaseAdapter {
    Context ctx;
    ArrayList<UnidadeDeEstudo> listaDeEstudos;
    boolean isLongeClick;
    ArrayList<Item> itens;

    public CicloAdapter(Context ctx, ArrayList<Item> itens) {
        this.ctx = ctx;
        this.itens = itens;
        isLongeClick = false;
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int i) {
        return itens.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        //LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View layout;
        if (view == null) {
            view = LayoutInflater.from(ctx).inflate(R.layout.item_model, null);


            //layout = inflater.inflate(R.layout.intermediaria_tarefa_modelo, null);
            if(i==0){
                //view = LayoutInflater.from(c).inflate(R.layout.primeira_tarefa_modelo, parent, false);
            }else if(i != listaDeEstudos.size()-1){
                //view = LayoutInflater.from(c).inflate(R.layout.intermediaria_tarefa_modelo, parent, false);
            }else {
                //view = LayoutInflater.from(c).inflate(R.layout.ultima_tarefa_modelo, parent, false);
            }

        } else{
            //layout = view;
        }

        TextView tvNome = view.findViewById(R.id.tvNome);
        TextView tvPreco = view.findViewById(R.id.tvPreco);
        tvNome.setText(itens.get(i).getNome());
        tvPreco.setText(String.valueOf(itens.get(i).getPreco()));

        //Atribuições

        /*View finalView = view;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLongeClick) {
                    isLongeClick = false;
                } else {
                    Toast.makeText(c, "Vc Clicou!", Toast.LENGTH_SHORT).show();
                    YoYo.with(Techniques.Landing)
                            .duration(400)
                            .repeat(0)
                            .playOn(finalView);
                    //act.listarContainer(i);
                }
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                isLongeClick = true;
                YoYo.with(Techniques.Wave)
                        .duration(700)
                        .repeat(0)
                        .playOn(finalView);

                Toast.makeText(c, "Você precionou!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });*/

        return view;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
