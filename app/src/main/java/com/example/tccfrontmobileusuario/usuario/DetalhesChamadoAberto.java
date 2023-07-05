package com.example.tccfrontmobileusuario.usuario;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tccfrontmobileusuario.R;

import backend.RetrofitConfig;
import model.ChamadoDTO;
import model.UsuarioDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalhesChamadoAberto extends AppCompatActivity {

    UsuarioDTO usuarioDTO;

    ChamadoDTO chamadoDTO;

    TextView numeroChamado;

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
        numeroChamado = findViewById(R.id.numero_chamado_detalhes);

        numeroChamado.setText(chamadoDTO.getId().toString());
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
        AlertDialog.Builder msgBox = new AlertDialog.Builder(this);
        msgBox.setTitle("Excluir");
        msgBox.setMessage("Tem certeza que deseja excluir o chamado?");
        msgBox.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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
        });
        msgBox.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        msgBox.show();


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
            Intent it = new Intent(DetalhesChamadoAberto.this, HomepageUsuario.class);
            it.putExtra("usuario", usuarioDTO);
            startActivity(it);
        }







}