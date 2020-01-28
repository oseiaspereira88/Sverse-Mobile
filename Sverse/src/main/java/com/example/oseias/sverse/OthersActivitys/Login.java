package com.example.oseias.sverse.OthersActivitys;

import android.content.Intent;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.oseias.sverse.Main2Activity;
import com.example.oseias.sverse.MainActivity;
import com.exemple.oseias.sverse.R;
import com.example.oseias.sverse.SQLite.dao.ConfiguracaoDAO;
import com.example.oseias.sverse.SQLite.dao.UsuarioDAO;
import com.example.oseias.sverse.SQLite.model.Configuracao;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    ConstraintLayout cl;
    private EditText editeLogin, editeSenha;
    private UsuarioDAO dao;
    private CheckBox chk_connect;
    private Button bEntrar;
    ConfiguracaoDAO configsDAO;
    ArrayList<Configuracao> configuracoes;

    private boolean offlineMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cl = findViewById(R.id.login_layout);
        configsDAO = new ConfiguracaoDAO(this);
        configuracoes = configsDAO.listarConfigs();
        Toast.makeText(this, "Número de configs:" + configuracoes.size(), Toast.LENGTH_LONG).show();
        inicializarVariaveis();

        dao = new UsuarioDAO(this);

        ///////Animação de intro/////
        ///!!!Falta Implementar!!!///

        if (isPrimaryExibition()) {
            //Run Play an Primary Exibition
            Toast.makeText(this, "Seja bem vindo: Siga um breve tutorial...", Toast.LENGTH_LONG).show();
            //Play an SmartTutor
            //-------------------------//
            setBasicConfig();

        } else if (configuracoes.get(Configuracao.IS_LOGIN_PERSISTENT).getvalorConfig()==1) {
            //Msg de Boas Vindas
            Toast.makeText(this, "Seja bem vindo de volta "
            + dao.buscarUsuarioPorId(configuracoes.get(Configuracao.ID_LOGIN).getvalorConfig()).getNome()
            + ".", Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Sessão iniciada por persistencia de login.", Toast.LENGTH_SHORT).show();
            YoYo.with(Techniques.Landing)
                    .duration(400)
                    .repeat(0)
                    .playOn(cl);

            //Run an Carregamento com o Progress Bar
            ////Falta Implementar

            //Intent it = new Intent(this, MainActivity.class);
            Intent it = new Intent(this, Main2Activity.class);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                    handler.postDelayed(this, 1000);
                }
            }, 1000);
            startActivity(it);
        }
    }

    public boolean isPrimaryExibition(){
        if(configsDAO.listarConfigs().size() != 0){
            return false;
        } else {
            return true;
        }
    }

    public void setBasicConfig(){
        configsDAO.addConfig(new Configuracao(Configuracao.IS_PRIMARY_EXIBITION, 0));
        configsDAO.addConfig(new Configuracao(Configuracao.IS_LOGIN_PERSISTENT, 0));
        configsDAO.addConfig(new Configuracao(Configuracao.ID_LOGIN, 0));
        configsDAO.addConfig(new Configuracao(Configuracao.LAST_INDEX_FRAGMENT, 0));
        configsDAO.addConfig(new Configuracao(Configuracao.LAST_CONTAINERS_LIST_MODE, 0));

    }

    public void inicializarVariaveis(){
        editeLogin = (EditText) findViewById(R.id.editNome);
        editeSenha = (EditText) findViewById(R.id.editSenha);
        chk_connect = (CheckBox) findViewById(R.id.chk_connect);
        chk_connect.setChecked(true);
        bEntrar = (Button) findViewById(R.id.bEntrar);
    }

    public void entrar(View view){
        if (verificarCampoLogin() && verificarCampoSenha()){
            String login = editeLogin.getText().toString();
            String senha = editeSenha.getText().toString();

          if (dao.logar(login, senha)){

              // Abrindo a Main Act
              Intent it = new Intent(this, MainActivity.class);
              final Handler handler = new Handler();
              handler.postDelayed(new Runnable() {
                  @Override
                  public void run() {
                      //finish();
                      handler.postDelayed(this, 1000);
                  }
              }, 1000);
              startActivity(it);

              //setando as configurações
              if(chk_connect.isChecked()){
                  configsDAO.atualizarConfig(new Configuracao(Configuracao.IS_LOGIN_PERSISTENT, 1));
              } else {
                  configsDAO.atualizarConfig(new Configuracao(Configuracao.IS_LOGIN_PERSISTENT, 0));
              }
              configsDAO.atualizarConfig(new Configuracao(Configuracao.ID_LOGIN, dao.retornUserPorLogin(login, senha).get_id()));

              //Saudações
              Toast.makeText(this, "Seja bem vindo "
                      + dao.retornUserPorLogin(login, senha).getNome()
                      + ".", Toast.LENGTH_LONG).show();
              YoYo.with(Techniques.Landing)
                      .duration(400)
                      .repeat(0)
                      .playOn(view);

          } else{
              Toast.makeText(this, "Login Não Efetuado.", Toast.LENGTH_SHORT).show();
              Toast.makeText(this, "Verifique os Campos.", Toast.LENGTH_SHORT).show();
              editeLogin.setError(getString(R.string.lbl_erro_login));
              editeSenha.setError(getString(R.string.lbl_erro_senha));
          }
        }
    }

    public boolean verificarCampoLogin(){
        boolean isOk =  false;
        if (editeLogin.getText().toString().equals("") || editeLogin == null){
            isOk = false;
            editeLogin.setError(getString(R.string.lbl_erro_login));
        } else {
            isOk = true;
        }
        return isOk;
    }

    public boolean verificarCampoSenha(){
        boolean isOk =  false;
        if (editeSenha.getText().toString().equals("") || editeSenha == null){
            isOk = false;
            editeSenha.setError(getString(R.string.lbl_erro_senha));

        } else {
            isOk = true;
        }
        return isOk;
    }

    public void abrirCadastrar(View view){
        Toast.makeText(this, "Abrindo TELA DE CADASTRO...", Toast.LENGTH_SHORT).show();
        Intent it = new Intent(this, Cadastro.class);
        startActivity(it);
    }

    public void logarPeloGmail(View view){
        ImageButton buttonGmail = (ImageButton) findViewById(R.id.bGmail);
        buttonGmail.setImageDrawable(getResources().getDrawable(R.mipmap.ic_run_gmail));
        /////////////Code////////////////
    }

    public void logarPeloSuap(View view){
        ImageButton buttonGmail = (ImageButton) findViewById(R.id.bSuap);
        buttonGmail.setImageDrawable(getResources().getDrawable(R.mipmap.ic_run_suap));
        /////////////Code////////////////
    }

    public void logarOffline(View view){
        ImageButton buttonOffline = (ImageButton) findViewById(R.id.bOffline);
        if(offlineMode){
            offlineMode = false;
            buttonOffline.setImageDrawable(getResources().getDrawable(R.mipmap.ic_offline));
        } else {
            buttonOffline.setImageDrawable(getResources().getDrawable(R.mipmap.ic_run_online));
            offlineMode = true;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.config_login){
            Toast.makeText(this, "Abrindo Configurações de Login.", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
