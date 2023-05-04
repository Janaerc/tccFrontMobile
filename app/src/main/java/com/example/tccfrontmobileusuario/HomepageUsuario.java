package com.example.tccfrontmobileusuario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import backend.RetrofitConfig;
import bean.Chamado;
import model.ChamadoDTO;
import model.UsuarioDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomepageUsuario extends AppCompatActivity {

    UsuarioDTO usuarioDTO;
    Button botaoNovoChamado;
    TextView saudacao, sigla;

    private List<ChamadoDTO> chamadoDTO;
    private List<Chamado> chamados;

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

        listaChamado();

    }

    public void novoChamado(View view){
        Intent intent = new Intent(HomepageUsuario.this, NovoChamado.class);
        intent.putExtra("usuario", usuarioDTO);
        startActivity(intent);
        finish();
    }

    public void listaChamado(){
        Call<List<ChamadoDTO>> call = new RetrofitConfig().getChamadoService().listaDeChamados(usuarioDTO.getId());
        call.enqueue(new Callback<List<ChamadoDTO>>() {
            @Override
            public void onResponse(Call<List<ChamadoDTO>> call, Response<List<ChamadoDTO>> response) {
                System.out.println("veio resposta");
                chamadoDTO = response.body();
                if(chamadoDTO != null) {
                    for (ChamadoDTO c : chamadoDTO) {
                        Chamado chamado = new Chamado();
                        chamado.setUsuarioId(c.getUsuarioId());
                        chamado.setDescricaoLocal(c.getDescricaoLocal());
                        chamado.setDescricaoProblema(c.getDescricaoProblema());
                        chamado.setUsuarioId(c.getUsuarioId());
                        chamado.setPredioId(c.getPredioId());
                        chamado.setDataHora(c.getDataHora());
                        chamado.setId(c.getId());
                        chamado.setOrdemServicoId(c.getOrdemServicoId());
                        chamado.setStatusId(c.getStatusId());
                        System.out.println(chamado);
                        //chamados.add(chamado);
                    }
                }else {
                        System.out.println(chamadoDTO);

                }
            }

            @Override
            public void onFailure(Call<List<ChamadoDTO>> call, Throwable t) {

            }
        });
    }


    public void menuUsuario(View view) {
        openOptionsMenu();
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