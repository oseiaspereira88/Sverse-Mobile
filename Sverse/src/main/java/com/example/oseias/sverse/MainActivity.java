package com.example.oseias.sverse;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.oseias.sverse.MainFragments.FragmentConfiguracoes;
import com.example.oseias.sverse.OthersActivitys.ChronometerTest;
import com.example.oseias.sverse.OthersActivitys.Configuracoes;
import com.example.oseias.sverse.OthersActivitys.CriadorDeAtividades;
import com.example.oseias.sverse.OthersActivitys.EstudoEmCiclo;
import com.example.oseias.sverse.OthersClass.ArquivamentoIndexFragment.IndexFragement;
import com.example.oseias.sverse.OthersClass.GestorDeNotas;
import com.example.oseias.sverse.MainFragments.FragmentAjuda;
import com.example.oseias.sverse.MainFragments.FragmentFerramentas;
import com.example.oseias.sverse.MainFragments.FragmentPerfil;
import com.example.oseias.sverse.MainFragments.MainFragment;
import com.example.oseias.sverse.MainFragments.FragmentSobre;
import com.example.oseias.sverse.OthersActivitys.Login;
import com.example.oseias.sverse.SQLite.dao.ConfiguracaoDAO;
import com.example.oseias.sverse.SQLite.dao.UsuarioDAO;
import com.example.oseias.sverse.SQLite.model.Configuracao;
import com.exemple.oseias.sverse.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    IndexFragement indexFragement;
    FloatingActionButton fab;
    //private int idCurrentFragment;
    ConfiguracaoDAO configsDAO;
    UsuarioDAO usuarioDAO;
    NavigationView navigationView;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_principal);
        setSupportActionBar(toolbar);

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appBar1);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new MainFragment()).commit();
        navigationView.getMenu().getItem(0).setChecked(true);

        configsDAO = new ConfiguracaoDAO(this);
    }

    // Código botão Voltar
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Handle the back button
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            checkExit();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private void checkExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja realmente sair?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        //Ação tomada caso o usuário escolha sim.
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_abrir_configuracoes) {
            Toast.makeText(this, "Abrindo Configurações...", Toast.LENGTH_SHORT).show();
            Intent it = new Intent(this, Configuracoes.class);
            startActivity(it);
        } else if (id == R.id.action_reiniciar_activity) {
            GestorDeNotas gn = new GestorDeNotas(this);
            gn.deleteFilesBackup(new View(this));
            Toast.makeText(this, "Reiniciando Configurações e o App...", Toast.LENGTH_SHORT).show();
            recreate();
        } else if (id == R.id.action_sair_do_app) {
            Toast.makeText(this, "Fechando...", Toast.LENGTH_SHORT).show();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_principal) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new MainFragment()).commit();
        } else if (id == R.id.nav_explorar) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new FragmentPerfil()).commit();
        } else if (id == R.id.nav_ferramentas) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new FragmentFerramentas()).commit();
        } else if (id == R.id.nav_configuracoes) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new FragmentConfiguracoes()).commit();
        } else if (id == R.id.nav_ajuda) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new FragmentAjuda()).commit();
        } else if (id == R.id.nav_sobre) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new FragmentSobre()).commit();
        } else if (id == R.id.nav_logoff) {
            logoff();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void abrirCicloDeEstudo(View view) {
        YoYo.with(Techniques.RotateIn)
                .duration(700)
                .repeat(0)
                .playOn(view);
        Toast.makeText(this, "Abrindo Ciclo...", Toast.LENGTH_SHORT).show();
        Intent it = new Intent(this, EstudoEmCiclo.class);
        startActivity(it);
    }

    public void abrirCriadorDeAtividades() {
        Toast.makeText(this, "Abrindo Criador de Atividades...", Toast.LENGTH_SHORT).show();
        Intent it = new Intent(this, CriadorDeAtividades.class);
        startActivity(it);
    }

    public void abrirTestContador(View view) {
        Toast.makeText(this, "Abrindo Criador de Atividades...", Toast.LENGTH_SHORT).show();
        Intent it = new Intent(this, ChronometerTest.class);
        startActivity(it);
    }

    public void actionButton(View view) {
        YoYo.with(Techniques.RotateIn)
                .duration(700)
                .repeat(0)
                .playOn(view);
    }

    public void abrirPerfil(View view) {
        YoYo.with(Techniques.Wave)
                .duration(700)
                .repeat(0)
                .playOn(view);

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new FragmentPerfil()).commit();
        navigationView.getMenu().getItem(0).setChecked(true);
        drawer.closeDrawers();
    }

    public void logoff() {
        //setando as configurações
        configsDAO.atualizarConfig(new Configuracao(Configuracao.IS_LOGIN_PERSISTENT, 0));
        configsDAO.atualizarConfig(new Configuracao(Configuracao.ID_LOGIN, 0));

        Intent it = new Intent(this, Login.class);
        startActivity(it);
        finish();
    }


}
