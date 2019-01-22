package com.example.oseias.sverse.OtherAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.oseias.sverse.Interfaces.ConcluidosRecyclerViewOnClickListenerHack;
import com.example.oseias.sverse.SQLite.model.Objetivo;
import com.versaplications.prodesenvelopment.sverse.R;

import java.util.ArrayList;

public class EtapasConcluidasAdapter extends RecyclerView.Adapter<EtapasConcluidasAdapter.MyViewHolder> {
    private Context ctx;
    private ArrayList<Objetivo> objetivos;
    private LayoutInflater myLayoutInflater;
    private static ConcluidosRecyclerViewOnClickListenerHack recyclerViewOnClickListenerHack;


    public EtapasConcluidasAdapter(Context ctx, ArrayList<Objetivo> objetivos) {
        this.ctx = ctx;
        this.objetivos = objetivos;
        this.myLayoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    }

    @Override
    public int getItemCount() {
        return objetivos.size();
    }

    public void removeItemList(int position){
        objetivos.remove(position);
        notifyItemRemoved(position);
    }

    public void setRecyclerViewOnClickListenerHack(ConcluidosRecyclerViewOnClickListenerHack r) {
        this.recyclerViewOnClickListenerHack = r;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView titulo;
        public RecyclerView recyclerSubItemEtapa;
        public TextView ordemPosition;

        public MyViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.textObjetivo);
            ordemPosition = (TextView) itemView.findViewById(R.id.tvPosicao);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewOnClickListenerHack != null){
                        recyclerViewOnClickListenerHack.onClickListener(v, getPosition());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(recyclerViewOnClickListenerHack != null){
                        recyclerViewOnClickListenerHack.onLongClickListener(v, getPosition());
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
            if(recyclerViewOnClickListenerHack != null){
                recyclerViewOnClickListenerHack.onDragListener(v, event);
            }
            return true;
        }
    }
}
