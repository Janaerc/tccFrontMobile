package com.example.tccfrontmobileusuario.operario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.tccfrontmobileusuario.R;
import com.example.tccfrontmobileusuario.SobreApp;

public class CadastroOperario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_operario);
    }

    public void menuOperario(View view) {
        openOptionsMenu();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_operario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.sobreApp:
                Intent intent = new Intent(CadastroOperario.this, SobreApp.class);
                startActivity(intent);
                finish();
            case R.id.cadastro_operario:
                Intent intent2 = new Intent(CadastroOperario.this, CadastroOperario.class);
                startActivity(intent2);
                finish();
            case R.id.logout:
                Toast.makeText(CadastroOperario.this, "Implementar logout", Toast.LENGTH_SHORT).show();



        }


        return super.onOptionsItemSelected(item);
    }












}