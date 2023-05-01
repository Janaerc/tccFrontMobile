package com.example.tccfrontmobileusuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import model.UsuarioDTO;

public class HomepageUsuario extends AppCompatActivity {

    UsuarioDTO usuarioDTO;
    Button botaoNovoChamado;
    TextView saudacao, sigla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_usuario);

        usuarioDTO = (UsuarioDTO) getIntent().getSerializableExtra("usuario");
        saudacao = findViewById(R.id.saudacao_user_name);
        sigla = findViewById(R.id.container_user_text);
        botaoNovoChamado = findViewById(R.id.buttonNovoChamado);

        String nomeCompleto = usuarioDTO.getNome();
        int indiceEspaco = nomeCompleto.indexOf(" ");
        char primeiraLetraNome = nomeCompleto.charAt(0);
        char primeiraLetraSobrenome = nomeCompleto.charAt(indiceEspaco + 1);
        String iniciais = "" + primeiraLetraNome + primeiraLetraSobrenome;

        sigla.setText(iniciais);
        saudacao.setText(usuarioDTO.getNome());


    }

    public void novoChamado(View view){
        Intent intent = new Intent(HomepageUsuario.this, NovoChamado.class);
        intent.putExtra("usuario", usuarioDTO);
        startActivity(intent);
        finish();
    }
}