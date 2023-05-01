package com.example.tccfrontmobileusuario;

import androidx.annotation.NonNull;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_usuario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.sobreApp:
                Intent intent = new Intent(HomepageUsuario.this, SobreApp.class);
                startActivity(intent);
                finish();
            case R.id.cadastro:
                Intent intent2 = new Intent(HomepageUsuario.this, Cadastro.class);
                startActivity(intent2);
                finish();
            case R.id.logout:
                Toast.makeText(HomepageUsuario.this, "Implementar logout", Toast.LENGTH_SHORT).show();



        }


        return super.onOptionsItemSelected(item);
    }





}