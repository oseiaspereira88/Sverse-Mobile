package com.example.oseias.sverse.OthersActivitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.exemple.oseias.sverse.R;
import com.example.oseias.sverse.SQLite.dao.UsuarioDAO;
import com.example.oseias.sverse.SQLite.model.Usuario;

public class CadastroActivity extends AppCompatActivity {
    EditText editNome, editEmail, editLogin, editSenha;
    UsuarioDAO usuarioDAO;
    Usuario novoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    public void inicializarVariaveis() {
        editNome = (EditText) findViewById(R.id.editNome);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editLogin = (EditText) findViewById(R.id.editNome);
        editSenha = (EditText) findViewById(R.id.editSenha);
    }

    public boolean validarFormulario(){
        boolean isOk = false;

        if (editNome.getText().toString().equals("") || editNome == null){
            isOk = false;
            editNome.setError("Digite o seu Nome!");
        } else {
            isOk = true;
        }

        if (editEmail.getText().toString().equals("") || editEmail == null){
            isOk = false;
            editEmail.setError("Digite o seu Email!");
        } else {
            isOk = true;
        }

        if (editLogin.getText().toString().equals("") || editLogin == null){
            isOk = false;
            editLogin.setError(getString(R.string.lbl_erro_login));
        } else {
            isOk = true;
        }

        if (editSenha.getText().toString().equals("") || editSenha == null){
            isOk = false;
            editSenha.setError(getString(R.string.lbl_erro_senha));

        } else {
            isOk = true;
        }

        return isOk;
    }

    public void cadastrar(View view) {
        inicializarVariaveis();
        if (validarFormulario()) {
            usuarioDAO = new UsuarioDAO(this);
            novoUsuario = new Usuario(
                    editNome.getText().toString(),
                    editLogin.getText().toString(),
                    editSenha.getText().toString()
            );

            usuarioDAO.salvarNovoUsuario(novoUsuario);

            Toast.makeText(this, "CadastroActivity Efetuado com Sucesso.", Toast.LENGTH_LONG).show();
            finish();
        }
    }


    public void cadastrarPeloGmail(View view) {
        ImageButton buttonGmail = (ImageButton) findViewById(R.id.bGMAIL);
        buttonGmail.setImageDrawable(getResources().getDrawable(R.mipmap.ic_run_gmail));
        /////////////Code////////////////
    }

    public void cadastrarPeloSuap(View view) {
        ImageButton buttonGmail = (ImageButton) findViewById(R.id.bSUAP);
        buttonGmail.setImageDrawable(getResources().getDrawable(R.mipmap.ic_run_suap));
        /////////////Code////////////////
    }

}
