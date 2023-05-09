package com.example.tccfrontmobileusuario.operario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tccfrontmobileusuario.R;

public class DetalhesOrdemServicoAberta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operario_detalhes_ordem_servico_aberta);
    }


    public void voltarHome(View view) {
        Intent intent = new Intent(DetalhesOrdemServicoAberta.this, HomepageOperario.class);
        startActivity(intent);
    }





}