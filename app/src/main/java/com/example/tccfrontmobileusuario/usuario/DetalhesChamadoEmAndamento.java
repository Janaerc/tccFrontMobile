package com.example.tccfrontmobileusuario.usuario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.tccfrontmobileusuario.R;

import model.ChamadoDTO;
import model.UsuarioDTO;

public class DetalhesChamadoEmAndamento extends AppCompatActivity {

    UsuarioDTO usuarioDTO;

    ChamadoDTO chamadoDTO;

    EditText editCampus, editPredio, editLocalizacao, editProblema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_detalhes_chamado_em_andamento);

        usuarioDTO = (UsuarioDTO) getIntent().getSerializableExtra("usuario");
        chamadoDTO = (ChamadoDTO) getIntent().getSerializableExtra("chamado");
        editCampus = findViewById(R.id.editTexCampus);
        editPredio = findViewById(R.id.editTextPredio);
        editLocalizacao = findViewById(R.id.descricao_localizacao_editText);
        editProblema = findViewById(R.id.descricao_problema_editText);

        editCampus.setText(chamadoDTO.getPredioId().getCampusId().getNome());
        editPredio.setText(chamadoDTO.getPredioId().getNome());
        editLocalizacao.setText(chamadoDTO.getDescricaoLocal());
        editProblema.setText(chamadoDTO.getDescricaoProblema());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_usuario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.sobreAppUsuarioLogado:
                Intent intent = new Intent(DetalhesChamadoEmAndamento.this, SobreApp.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.cadastro:
                Intent intent2 = new Intent(DetalhesChamadoEmAndamento.this, Cadastro.class);
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

    public void voltarHome(View view) {
        Intent intent = new Intent(DetalhesChamadoEmAndamento.this, HomepageUsuario.class);
        intent.putExtra("usuario", usuarioDTO);
        startActivity(intent);
    }












}