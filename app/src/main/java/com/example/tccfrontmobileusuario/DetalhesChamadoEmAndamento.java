package com.example.tccfrontmobileusuario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class DetalhesChamadoEmAndamento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_chamado_em_andamento);
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
                Intent intent = new Intent(DetalhesChamadoEmAndamento.this, SobreApp.class);
                startActivity(intent);
                finish();
            case R.id.cadastro:
                Intent intent2 = new Intent(DetalhesChamadoEmAndamento.this, Cadastro.class);
                startActivity(intent2);
                finish();
            case R.id.logout:
                Toast.makeText(DetalhesChamadoEmAndamento.this, "Implementar logout", Toast.LENGTH_SHORT).show();



        }


        return super.onOptionsItemSelected(item);
    }

    public void voltarHome(View view) {
        Intent intent = new Intent(DetalhesChamadoEmAndamento.this, HomepageUsuario.class);
        startActivity(intent);
    }












}