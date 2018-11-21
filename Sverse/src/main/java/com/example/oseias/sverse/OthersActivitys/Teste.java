package com.example.oseias.sverse.OthersActivitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.oseias.sverse.OtherAdapters.AtividadeAdapter;
import com.example.oseias.sverse.OtherAdapters.ContainerGridAdapter;
import com.example.oseias.sverse.OtherAdapters.GrupAdapter;
import com.example.oseias.sverse.OtherAdapters.MateriaAdapter;
import com.example.oseias.sverse.OtherAdapters.PersonalAdapter;
import com.versaplications.prodesenvelopment.sverse.R;
import com.example.oseias.sverse.SQLite.model.Container;

import java.util.ArrayList;

public class Teste extends AppCompatActivity {
    ContainerGridAdapter adapterContainers;
    MateriaAdapter adapterMaterias;
    AtividadeAdapter adapterAtividades;
    GrupAdapter adapterEstudoEmGrupo;
    PersonalAdapter adapterPessoal;

    GridView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gv= (GridView) findViewById(R.id.gv);

        /*adapterContainers = new ContainerGridAdapter(this,this,getDataContainersData());
        gv.setAdapter(adapterContainers);

        YoYo.with(Techniques.Landing)
                .duration(2400)
                .repeat(0)
                .playOn(gv);*/
    }
    private ArrayList getDataContainersData()
    {
        ArrayList<Container> containers = new ArrayList<>();
        //containers.add(new Container(R.drawable.bg1,"Info1V", "0"));
        //containers.add(new Container(R.drawable.bg2,"Info2V", "0"));
        //containers.add(new Container(R.drawable.bg3,"Info3V", "0"));
        //containers.add(new Container(R.drawable.bg15,"Info4V", "0"));
        //containers.add(new Container(R.drawable.bg5,"Clube de Gestão Organizacional", "0"));
        //containers.add(new Container(R.drawable.bg6,"Clube de Matematica", "0"));
        //containers.add(new Container(R.drawable.bg7,"Automação Industrial", "0"));
        //containers.add(new Container(R.drawable.bg8,"Clube de Robôtica", "0"));
        //containers.add(new Container(R.drawable.bg9,"Coral de Gislene", "0"));
        //containers.add(new Container(R.drawable.bg10,"Clude de Programação Web", "0"));
        //containers.add(new Container(R.drawable.bg11,"Preparatório para o ENEM", "0"));
        //containers.add(new Container(R.drawable.bg16_min,"Projeto TCC", "0"));
        //containers.add(new Container(R.drawable.bg13,"Estudo Bíblico", "0"));
        //containers.add(new Container(R.drawable.bg14,"Estudos Pessoais", "0"));
        //containers.add(new Container(R.drawable.bg1,"Info1V", "0"));
        //containers.add(new Container(R.drawable.bg2,"Info2V", "0"));
        //containers.add(new Container(R.drawable.bg3,"Info3V", "0"));
        //containers.add(new Container(R.drawable.bg15_min,"Info4V", "0"));

        return containers;
    }

    private ArrayList getContainersData()
    {
        ArrayList<Container> containers = new ArrayList<>();
        //containers.add(new Container(R.drawable.bg1,"Matemática", "4"));
        //containers.add(new Container(R.drawable.bg2,"Português", "7"));
        //containers.add(new Container(R.drawable.bg3,"G.O", "2"));
        //containers.add(new Container(R.drawable.bg15,"P.I.D", "3"));
        return containers;
    }

    public void listarContainer(int containerId){
        //adapterMaterias = new MateriaAdapter(this, this, getContainersData());
        gv.setAdapter(adapterMaterias);
        YoYo.with(Techniques.Landing)
                .duration(2400)
                .repeat(0)
                .playOn(gv);
    }

    private ArrayList getAtividadesData()
    {
        ArrayList<Container> containers = new ArrayList<>();
        //containers.add(new Container(R.drawable.bg1,"Matemática", "4"));
        //containers.add(new Container(R.drawable.bg2,"Português", "7"));
        //containers.add(new Container(R.drawable.bg3,"G.O", "2"));
        //containers.add(new Container(R.drawable.bg15,"P.I.D", "3"));
        return containers;
    }

    public void listarAtividades(int containerId){
        //adapterAtividades = new AtividadeAdapter(this, this, getAtividadesData());
        //gv.setAdapter(adapterAtividades);
    }

    private ArrayList getGruposData()
    {
        ArrayList<Container> containers = new ArrayList<>();
        //containers.add(new Container(R.drawable.bg1,"Matemática", "4"));
        //containers.add(new Container(R.drawable.bg2,"Português", "7"));
        //containers.add(new Container(R.drawable.bg3,"G.O", "2"));
        //containers.add(new Container(R.drawable.bg15,"P.I.D", "3"));
        return containers;
    }

    public void listarTemas(int containerId){
        //adapterEstudoEmGrupo = new GrupAdapter(this, this, getGruposData());
        //gv.setAdapter(adapterEstudoEmGrupo);
    }

    private ArrayList getPersonalData()
    {
        ArrayList<Container> containers = new ArrayList<>();
        //containers.add(new Container(R.drawable.bg1,"Matemática", "4"));
        //containers.add(new Container(R.drawable.bg2,"Português", "7"));
        //containers.add(new Container(R.drawable.bg3,"G.O", "2"));
        //containers.add(new Container(R.drawable.bg15,"P.I.D", "3"));
        return containers;
    }

    public void listarPessoal(int containerId){
        //adapterPessoal = new PersonalAdapter(this, this, getPersonalData());
        //gv.setAdapter(adapterPessoal);
    }

}
