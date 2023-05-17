package com.example.tccfrontmobileusuario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import backend.RetrofitConfig;
import model.ChamadoDTO;
import model.UsuarioDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalhesChamadoAberto extends AppCompatActivity {

    UsuarioDTO usuarioDTO;

    ChamadoDTO chamadoDTO;

    EditText editCampus, editPredio, editLocalizacao, editProblema;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_detalhes_chamado_aberto);
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

    public void excluirChamado (View view){
        Call<ChamadoDTO> call = new RetrofitConfig().getChamadoService().excluirChamado(chamadoDTO.getId());
        call.enqueue(new Callback<ChamadoDTO>() {
            @Override
            public void onResponse(Call<ChamadoDTO> call, Response<ChamadoDTO> response) {
                if (response.isSuccessful()){
                    Toast.makeText(DetalhesChamadoAberto.this, "Chamado excluído com sucesso", Toast.LENGTH_SHORT).show();
                    Intent it = new Intent(DetalhesChamadoAberto.this, HomepageUsuario.class);
                    it.putExtra("usuario", usuarioDTO);
                    startActivity(it);
                } else {
                    Toast.makeText(DetalhesChamadoAberto.this, "Erro ao excluir chamado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ChamadoDTO> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.sobreApp:
                Intent intent = new Intent(DetalhesChamadoAberto.this, SobreAppLogadoUsuario.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.cadastro:
                Intent intent2 = new Intent(DetalhesChamadoAberto.this, Cadastro.class);
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
            Intent intent = new Intent(DetalhesChamadoAberto.this, HomepageUsuario.class);
            startActivity(intent);
        }







}