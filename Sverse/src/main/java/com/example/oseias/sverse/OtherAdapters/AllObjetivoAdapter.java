package com.example.oseias.sverse.OtherAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oseias.sverse.Interfaces.AllRecyclerViewOnClickListenerHack;
import com.example.oseias.sverse.SQLite.model.Objetivo;
import com.versaplications.prodesenvelopment.sverse.R;

import java.util.ArrayList;

public class AllObjetivoAdapter extends RecyclerView.Adapter<AllObjetivoAdapter.MyViewHolder> {
    private Context ctx;
    private ArrayList<Objetivo> objetivos;
    private LayoutInflater myLayoutInflater;
    private static AllRecyclerViewOnClickListenerHack allRecyclerViewOnClickListenerHack;


    public AllObjetivoAdapter(Context ctx, ArrayList<Objetivo> objetivos) {
        this.ctx = ctx;
        this.objetivos = objetivos;
        this.myLayoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("LOG", "onCreateViewHolder()");
        View view = myLayoutInflater.inflate(R.layout.item_objetivo_model, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.i("LOG", "onBindViewHolder()");
        holder.titulo.setText(objetivos.get(position).getTitulo());

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
        public RecyclerView recyclerSubItemEtapa;
        public MyViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.textObjetivo);
            recyclerSubItemEtapa = (RecyclerView) itemView.findViewById(R.id.recyclerSubItemEtapas);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(allRecyclerViewOnClickListenerHack != null){
                        allRecyclerViewOnClickListenerHack.onClickListener(v, getPosition());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(allRecyclerViewOnClickListenerHack != null){
                        allRecyclerViewOnClickListenerHack.onLongClickListener(v, getPosition());
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
