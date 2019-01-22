package com.example.oseias.sverse.OtherAdapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.oseias.sverse.Interfaces.AllRecyclerViewOnClickListenerHack;
import com.example.oseias.sverse.SQLite.model.Objetivo;
import com.versaplications.prodesenvelopment.sverse.R;

import java.util.ArrayList;

public class EtapaAdapter extends RecyclerView.Adapter<EtapaAdapter.MyViewHolder> {
    private Activity act;
    private ArrayList<Objetivo> objetivos;
    private LayoutInflater myLayoutInflater;
    private static AllRecyclerViewOnClickListenerHack allRecyclerViewOnClickListenerHack;


    public EtapaAdapter(Activity act, ArrayList<Objetivo> objetivos) {
        this.act = act;
        this.objetivos = objetivos;
        this.myLayoutInflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("LOG", "onCreateViewHolder()");
        View view = myLayoutInflater.inflate(R.layout.item_etapa_model, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.i("LOG", "onBindViewHolder()");
        holder.titulo.setText(objetivos.get(position).getTitulo());
        holder.ordemPosition.setText(objetivos.get(position).getIndexOrdem() + "");
        if(objetivos.get(position).getEstado()=="Em Andamento"){
            holder.checkBox.setChecked(false);
            holder.checkBox.setVisibility(View.VISIBLE);
        } else{
            holder.checkBox.setChecked(true);
            holder.checkBox.setVisibility(View.VISIBLE);
        }

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.checkBox.isChecked()){
                    //Set Etapa como Concluida no BD.
                    Toast.makeText(act, "Vc marcou como Concluida.", Toast.LENGTH_SHORT).show();
                }else{
                    //Set Etapa como Em Andamento no BD.
                    Toast.makeText(act, "Vc marcou como Em Andamento.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return objetivos.size();
    }

    public void removeItemList(int position){
        objetivos.remove(position);
        notifyItemRemoved(position);
    }

    public void setRecyclerViewOnClickListenerHack(AllRecyclerViewOnClickListenerHack r) {
        this.allRecyclerViewOnClickListenerHack = r;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView titulo;
        public TextView ordemPosition;
        public CheckBox checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.textObjetivo);
            ordemPosition = (TextView) itemView.findViewById(R.id.tvPosicao);
            checkBox = (CheckBox) itemView.findViewById(R.id.cbEtapas);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(allRecyclerViewOnClickListenerHack != null){
                        allRecyclerViewOnClickListenerHack.onClickListener(v, getPosition());
                        YoYo.with(Techniques.Landing)
                                .duration(700)
                                .repeat(0)
                                .playOn(itemView);
                        //Intent it = new Intent(act, CriadorDeNotas.class);
                        //Bundle b = new Bundle();
                        //b.putInt("id", etapa.get_id());
                        //it.putExtra("id", b);
                        //act.startActivity(it);
                        //act.finish();
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(allRecyclerViewOnClickListenerHack != null){
                        allRecyclerViewOnClickListenerHack.onLongClickListener(v, getPosition());
                        YoYo.with(Techniques.Wave)
                                .duration(700)
                                .repeat(0)
                                .playOn(itemView);
                    }
                    return true;
                }
            });

            itemView.setOnDragListener(new MyOnDragListener());
        }
    }

    public static class MyOnDragListener implements View.OnDragListener{

        @Override
        public boolean onDrag(View v, DragEvent event) {
            if(allRecyclerViewOnClickListenerHack != null){
                allRecyclerViewOnClickListenerHack.onDragListener(v, event);
            }
            return true;
        }
    }
}
