package com.example.tccfrontmobileusuario.operario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tccfrontmobileusuario.R;

import model.ChamadoDTO;
import model.UsuarioDTO;

public class ManterOrdemDeServico extends AppCompatActivity {

    UsuarioDTO usuarioDTO;
    ChamadoDTO chamadoDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operario_manter_ordem_de_servico);
        usuarioDTO = (UsuarioDTO) getIntent().getSerializableExtra("usuario");
        chamadoDTO = (ChamadoDTO) getIntent().getSerializableExtra("chamado");

    }
}