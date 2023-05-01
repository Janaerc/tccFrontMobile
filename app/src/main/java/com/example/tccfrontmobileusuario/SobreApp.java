package com.example.tccfrontmobileusuario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class SobreApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre_app);
    }
//*********************************************************************************************************************************************
// fazer o código if (se usuario logado ir para o menu de usuario, se operario logado abrir menu de operario, se usuario deslocago abrir menu de usuario deslogado)
    //*******************************************************************************************************************************************
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_operario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.sobreApp:
                Intent intent = new Intent(SobreApp.this, SobreApp.class);
                startActivity(intent);
                finish();
            case R.id.cadastro_operario:
                Intent intent2 = new Intent(SobreApp.this, CadastroOperario.class);
                startActivity(intent2);
                finish();
            case R.id.logout:
                Toast.makeText(SobreApp.this, "Implementar logout", Toast.LENGTH_SHORT).show();



        }


        return super.onOptionsItemSelected(item);
    }

















}