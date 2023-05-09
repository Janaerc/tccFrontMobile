package com.example.tccfrontmobileusuario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import model.UsuarioDTO;

public class SobreAppLogadoUsuario extends AppCompatActivity {

    UsuarioDTO usuarioDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre_app);
        usuarioDTO = (UsuarioDTO) getIntent().getSerializableExtra("usuario");

    }

    // MENU DE OPÇÕES - USUARIO LOGADO
    public void menuUsuario(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this::onOptionsItemSelected);
        popup.inflate(R.menu.menu_usuario);
        popup.show();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


            switch (item.getItemId()){
                case R.id.sobreAppUsuarioLogado:
                    Intent intent = new Intent(SobreAppLogadoUsuario.this, SobreAppLogadoUsuario.class);
                    intent.putExtra("usuario", usuarioDTO);
                    startActivity(intent);
                    finish();
                    return true;
                case R.id.cadastro:
                    Intent intent2 = new Intent(SobreAppLogadoUsuario.this, Cadastro.class);
                    intent2.putExtra("usuario", usuarioDTO);
                    startActivity(intent2);
                    finish();
                    return true;
                case R.id.logout:
                    Logout logout = new Logout(this);
                    logout.logout();
                    return true;

            }



        return super.onOptionsItemSelected(item);
    }

//********************************************************************************************************
    public void closeActivity(View view) {
        Intent intent = new Intent(SobreAppLogadoUsuario.this, HomepageUsuario.class);
        intent.putExtra("usuario", usuarioDTO);
        startActivity(intent);
        finish();

    }


}