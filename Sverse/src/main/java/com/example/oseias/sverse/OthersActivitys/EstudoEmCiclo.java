package com.example.oseias.sverse.OthersActivitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.oseias.sverse.OtherAdapters.ItensAdapter;
import com.example.oseias.sverse.OthersClass.ItemArea;
import com.versaplications.prodesenvelopment.sverse.R;

import java.util.ArrayList;

public class EstudoEmCiclo extends AppCompatActivity {
    ArrayList<ItemArea> lista;
    ItensAdapter itensAdapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste2);
        lista = new ArrayList<>();
        lv = new ListView(this);
        lv = findViewById(R.id.lv9);
        crieDados();
        itensAdapter = new ItensAdapter(this, lista);
        lv.setAdapter(itensAdapter);
    }

    public void crieDados(){
        lista.add(new ItemArea("Feijão Branco", 5.75f));
        lista.add(new ItemArea("Feijão Preto", 8.75f));
        lista.add(new ItemArea("Feijão Macaça", 4.75f));
        lista.add(new ItemArea("Feijão Verde", 7.75f));
        lista.add(new ItemArea("Fava Gigante", 8.75f));
    }

    public void actionButton(View view){
        YoYo.with(Techniques.Pulse)
                .duration(700)
                .repeat(0)
                .playOn(view);
    }
}
