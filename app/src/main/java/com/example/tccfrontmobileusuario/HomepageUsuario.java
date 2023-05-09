package com.example.tccfrontmobileusuario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import adapter.ChamadoListAdapter;
import backend.RetrofitConfig;
import bean.Chamado;
import helper.RecyclerItemClickListener;
import model.ChamadoDTO;
import model.UsuarioDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomepageUsuario extends AppCompatActivity {

    UsuarioDTO usuarioDTO;
    Button botaoNovoChamado;
    TextView saudacao, sigla;

    private ChamadoListAdapter chamadoListAdapter;

    private List<ChamadoDTO> chamadoDTO = new ArrayList<>();
    private List<Chamado> chamados =  new ArrayList<>();;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_homepage);

        usuarioDTO = (UsuarioDTO) getIntent().getSerializableExtra("usuario");
        saudacao = findViewById(R.id.saudacao_user_name);
        sigla = findViewById(R.id.container_user_text);
        botaoNovoChamado = findViewById(R.id.buttonNovoChamado);
        recyclerView = findViewById(R.id.recyclerViewChamadosUsuario);

        String nomeCompleto = usuarioDTO.getNome();
        int indiceEspaco = nomeCompleto.indexOf(" ");
        char primeiraLetraNome = nomeCompleto.charAt(0);
        char primeiraLetraSobrenome = nomeCompleto.charAt(indiceEspaco + 1);
        String iniciais = "" + primeiraLetraNome + primeiraLetraSobrenome;

        sigla.setText(iniciais);
        saudacao.setText(usuarioDTO.getNome());

        listaChamado();

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position){
                        int id = chamados.get(position).getId();

                        Call<ChamadoDTO> call = new RetrofitConfig().getChamadoService().chamadoPorId(id);
                        call.enqueue(new Callback<ChamadoDTO>() {
                            @Override
                            public void onResponse(Call<ChamadoDTO> call, Response<ChamadoDTO> response) {
                                if (response.isSuccessful()){
                                    ChamadoDTO selectedChamadoDTO = response.body();
                                    Bundle params = new Bundle();
                                    params.putString("operacao","view");
                                    params.putSerializable("chamado", selectedChamadoDTO);
                                    if(selectedChamadoDTO.getStatusId().getId() == 1) {
                                        Intent it = new Intent(HomepageUsuario.this, DetalhesChamadoEmAndamento.class);
                                        it.putExtra("update", "update");
                                        it.putExtra("pokemon", selectedChamadoDTO);
                                        it.putExtra("usuario", usuarioDTO);
                                        startActivity(it);
                                    }
                                    if(selectedChamadoDTO.getStatusId().getId() == 2) {
                                        Intent it = new Intent(HomepageUsuario.this, DetalhesChamadoAberto.class);
                                        it.putExtra("update", "update");
                                        it.putExtra("pokemon", selectedChamadoDTO);
                                        it.putExtra("usuario", usuarioDTO);
                                        startActivity(it);
                                    } else {
                                        Toast.makeText(HomepageUsuario.this, "Erro ao recuperar Pokemon", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<ChamadoDTO> call, Throwable t) {

                            }
                        });

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }

                })
        );
    }

    public void updateRecyclerChamadoList(){
        //fazer igual do ListarTodos do pokemon linha 138 até a linha 182
        Call<List<ChamadoDTO>> call1 = new RetrofitConfig().getChamadoService().listaDeChamados(usuarioDTO.getId());

        call1.enqueue(new Callback<List<ChamadoDTO>>() {
            @Override
            public void onResponse(Call<List<ChamadoDTO>> call, Response<List<ChamadoDTO>> response) {
                if (response.isSuccessful()) {
                    chamadoDTO = response.body();

                    //configura adapter
                    chamadoListAdapter = new ChamadoListAdapter(chamadoDTO);

                    //configura recyclerView
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
                    recyclerView.setAdapter(chamadoListAdapter);
                } else
                    Toast.makeText(HomepageUsuario.this, "Erro ao carregar Pokemon", Toast.LENGTH_SHORT).show();
                Log.i("INFO", "erro");
            }

            @Override
            public void onFailure(Call<List<ChamadoDTO>> call, Throwable t) {
                Toast.makeText(HomepageUsuario.this, "Erro de API", Toast.LENGTH_SHORT).show();
            }
        });


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

    @Override
    protected void onStart() {
        super.onStart();
        updateRecyclerChamadoList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateRecyclerChamadoList();
    }


// MENU DE OPÇÕES - usuário logado
    public void menuUsuario(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this::onOptionsItemSelected);
        popup.inflate(R.menu.menu_usuario);
        popup.show();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.sobreAppUsuarioLogado:
                Intent intent = new Intent(HomepageUsuario.this, SobreAppLogadoUsuario.class);
                intent.putExtra("usuario", usuarioDTO);
                startActivity(intent);
                finish();
                return true;
            case R.id.cadastro:
                Intent intent2 = new Intent(HomepageUsuario.this, Cadastro.class);
                intent2.putExtra("usuario", usuarioDTO);
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

//****************************************************************************************



}