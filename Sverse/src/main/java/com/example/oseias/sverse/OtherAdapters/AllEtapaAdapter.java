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
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.oseias.sverse.Interfaces.RecyclerViewOnClickListenerHack;
import com.example.oseias.sverse.SQLite.model.Etapa;
import com.versaplications.prodesenvelopment.sverse.R;

import java.util.ArrayList;

public class AllEtapaAdapter extends RecyclerView.Adapter<AllEtapaAdapter.MyViewHolder> {
    private Activity act;
    private ArrayList<Etapa> etapas;
    private LayoutInflater myLayoutInflater;
    private static RecyclerViewOnClickListenerHack allRecyclerViewOnClickListenerHack;


    public AllEtapaAdapter(Activity act, ArrayList<Etapa> etapas) {
        this.act = act;
        this.etapas = etapas;
        this.myLayoutInflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("LOG", "onCreateViewHolder()");
        View view = myLayoutInflater.inflate(R.layout.item_etapa_model2, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.i("LOG", "onBindViewHolder()");
        holder.titulo.setText(etapas.get(position).getTitulo());
        holder.ordemPosition.setText(etapas.get(position).getIndexOrdem() + "");
        if(etapas.get(position).getEstado()=="Em Andamento"){
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
        return etapas.size();
    }

    public void removeItemList(int position){
        etapas.remove(position);
        notifyItemRemoved(position);
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r) {
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
                        allRecyclerViewOnClickListenerHack.onClickListener1(v, getPosition());
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
                        allRecyclerViewOnClickListenerHack.onLongClickListener1(v, getPosition());
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
                allRecyclerViewOnClickListenerHack.onDragListener1(v, event);
            }
            return true;
        }
    }
}
