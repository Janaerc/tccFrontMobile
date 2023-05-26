package com.example.tccfrontmobileusuario.operario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tccfrontmobileusuario.DetalhesChamadoAberto;
import com.example.tccfrontmobileusuario.DetalhesChamadoEmAndamento;
import com.example.tccfrontmobileusuario.HomepageUsuario;
import com.example.tccfrontmobileusuario.Logout;
import com.example.tccfrontmobileusuario.R;
import com.example.tccfrontmobileusuario.SobreApp;

import java.util.ArrayList;
import java.util.List;

import adapter.ChamadoListAdapter;
import adapter.OrdemServicoListAdapter;
import backend.RetrofitConfig;
import bean.Chamado;
import helper.RecyclerItemClickListener;
import model.ChamadoDTO;
import model.OrdemServicoDTO;
import model.UsuarioDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomepageOperario extends AppCompatActivity {

    UsuarioDTO usuarioDTO;

    private ChamadoListAdapter chamadoListAdapterEmAberto, chamadoListAdapterMinhasOrdens;
    private List<ChamadoDTO> chamadoDTOListEmAberto = new ArrayList<>();
    private List<ChamadoDTO> chamadoDTOListMinhasOrdens = new ArrayList<>();
    private RecyclerView recyclerViewEmAberto, recyclerViewMinhasOrdens;
    TextView saudacao, sigla;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operario_homepage);

        usuarioDTO = (UsuarioDTO) getIntent().getSerializableExtra("usuario");
        saudacao = findViewById(R.id.saudacao_operario_name);
        sigla = findViewById(R.id.container_operario_text);
        recyclerViewEmAberto = findViewById(R.id.recyclerViewOrdemDeServicoEmAberto);
        recyclerViewMinhasOrdens = findViewById(R.id.recyclerViewMinhasOrdensDeServico);

        recyclerViewMinhasOrdens.setAdapter(chamadoListAdapterMinhasOrdens);
        recyclerViewEmAberto.setAdapter(chamadoListAdapterEmAberto);

        String nomeCompleto = usuarioDTO.getNome();
        int indiceEspaco = nomeCompleto.indexOf(" ");
        char primeiraLetraNome = nomeCompleto.charAt(0);
        char primeiraLetraSobrenome = nomeCompleto.charAt(indiceEspaco + 1);
        String iniciais = "" + primeiraLetraNome + primeiraLetraSobrenome;

        sigla.setText(iniciais);
        saudacao.setText(usuarioDTO.getNome());

        listaChamadoEmAberto();
        listaMeusChamados();

        //fazer igual o homepageusuario linha 75
        //fazer um para cada recyclerview
        recyclerViewMinhasOrdens.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerViewMinhasOrdens,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                int id = chamadoDTOListMinhasOrdens.get(position).getId();
                                Call<ChamadoDTO> call = new RetrofitConfig().getChamadoService().chamadoPorId(id);
                                call.enqueue(new Callback<ChamadoDTO>() {
                                    @Override
                                    public void onResponse(Call<ChamadoDTO> call, Response<ChamadoDTO> response) {
                                        if (response.isSuccessful()) {
                                            ChamadoDTO selectedChamadoDTO = response.body();
                                            Bundle params = new Bundle();
                                            params.putString("operacao", "view");
                                            params.putSerializable("chamado", selectedChamadoDTO);
                                            Intent it = new Intent(HomepageOperario.this, ManterOrdemDeServico.class);
                                            it.putExtra("chamado", selectedChamadoDTO);
                                            it.putExtra("usuario", usuarioDTO);
                                            startActivity(it);
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


        recyclerViewEmAberto.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerViewEmAberto,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                int id = chamadoDTOListMinhasOrdens.get(position).getId();
                                Call<ChamadoDTO> call = new RetrofitConfig().getChamadoService().chamadoPorId(id);
                                call.enqueue(new Callback<ChamadoDTO>() {
                                    @Override
                                    public void onResponse(Call<ChamadoDTO> call, Response<ChamadoDTO> response) {
                                        if (response.isSuccessful()) {
                                            ChamadoDTO selectedChamadoDTO = response.body();
                                            Bundle params = new Bundle();
                                            params.putString("operacao", "view");
                                            params.putSerializable("chamado", selectedChamadoDTO);
                                            Intent it = new Intent(HomepageOperario.this, DetalhesOrdemServicoAberta.class);
                                            it.putExtra("chamado", selectedChamadoDTO);
                                            it.putExtra("usuario", usuarioDTO);
                                            startActivity(it);
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

    public void updateRecyclerChamadoListEmAberto() {
        Call<List<ChamadoDTO>> call = new RetrofitConfig().getChamadoService().listaChamadosEmAberto(usuarioDTO.getEspecialidadeId().getId());
        call.enqueue(new Callback<List<ChamadoDTO>>() {
            @Override
            public void onResponse(Call<List<ChamadoDTO>> call, Response<List<ChamadoDTO>> response) {
                if (response.isSuccessful()) {
                    chamadoDTOListEmAberto = response.body();
                    System.out.println("dentro do updaterecycler");
                    System.out.println(chamadoDTOListEmAberto);
                    //configura adapter
                    chamadoListAdapterEmAberto = new ChamadoListAdapter(chamadoDTOListEmAberto);

                    //configura recyclerView
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerViewEmAberto.setLayoutManager(layoutManager);
                    recyclerViewEmAberto.setHasFixedSize(true);
                    recyclerViewEmAberto.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.HORIZONTAL));
                    recyclerViewEmAberto.setAdapter(chamadoListAdapterEmAberto);
                } else
                    Toast.makeText(HomepageOperario.this, "Erro ao carregar Chamados", Toast.LENGTH_SHORT).show();
                Log.i("INFO", "erro");
            }

            @Override
            public void onFailure(Call<List<ChamadoDTO>> call, Throwable t) {
                Toast.makeText(HomepageOperario.this, "Erro de API", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void updateRecyclerChamadoListMinhasOrdens() {
        Call<List<ChamadoDTO>> call = new RetrofitConfig().getChamadoService().listaMeusChamados(usuarioDTO.getId());
        call.enqueue(new Callback<List<ChamadoDTO>>() {
            @Override
            public void onResponse(Call<List<ChamadoDTO>> call, Response<List<ChamadoDTO>> response) {
                if (response.isSuccessful()) {
                    chamadoDTOListMinhasOrdens = response.body();
                    System.out.println("dentro do updaterecycler");
                    System.out.println(chamadoDTOListMinhasOrdens);
                    //configura adapter
                    chamadoListAdapterMinhasOrdens = new ChamadoListAdapter(chamadoDTOListMinhasOrdens);

                    //configura recyclerView
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerViewMinhasOrdens.setLayoutManager(layoutManager);
                    recyclerViewMinhasOrdens.setHasFixedSize(true);
                    recyclerViewMinhasOrdens.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.HORIZONTAL));
                    recyclerViewMinhasOrdens.setAdapter(chamadoListAdapterMinhasOrdens);
                } else
                    Toast.makeText(HomepageOperario.this, "Erro ao carregar Chamados", Toast.LENGTH_SHORT).show();
                Log.i("INFO", "erro");
            }

            @Override
            public void onFailure(Call<List<ChamadoDTO>> call, Throwable t) {
                Toast.makeText(HomepageOperario.this, "Erro de API", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void listaChamadoEmAberto() {
        Call<List<ChamadoDTO>> call = new RetrofitConfig().getChamadoService().listaChamadosEmAberto(usuarioDTO.getEspecialidadeId().getId());
        call.enqueue(new Callback<List<ChamadoDTO>>() {
            @Override
            public void onResponse(Call<List<ChamadoDTO>> call, Response<List<ChamadoDTO>> response) {
                if (response.isSuccessful()) {
                    chamadoDTOListEmAberto = response.body();
                } else {
                    Toast.makeText(HomepageOperario.this, "Erro ao carregar Chamados", Toast.LENGTH_SHORT).show();
                    Log.i("INFO", "erro");
                }
            }

            @Override
            public void onFailure(Call<List<ChamadoDTO>> call, Throwable t) {
                Toast.makeText(HomepageOperario.this, "Erro de API", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void listaMeusChamados() {
        Call<List<ChamadoDTO>> call = new RetrofitConfig().getChamadoService().listaMeusChamados(usuarioDTO.getId());
        call.enqueue(new Callback<List<ChamadoDTO>>() {
            @Override
            public void onResponse(Call<List<ChamadoDTO>> call, Response<List<ChamadoDTO>> response) {
                if (response.isSuccessful()) {
                    chamadoDTOListMinhasOrdens = response.body();
                } else {
                    Toast.makeText(HomepageOperario.this, "Erro ao carregar Chamados", Toast.LENGTH_SHORT).show();
                    Log.i("INFO", "erro");
                }
            }

            @Override
            public void onFailure(Call<List<ChamadoDTO>> call, Throwable t) {
                Toast.makeText(HomepageOperario.this, "Erro de API", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateRecyclerChamadoListMinhasOrdens();
        updateRecyclerChamadoListEmAberto();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateRecyclerChamadoListMinhasOrdens();
        updateRecyclerChamadoListEmAberto();
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
        switch (item.getItemId()) {
            case R.id.sobreApp:
                Intent intent = new Intent(HomepageOperario.this, SobreApp.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.cadastro_operario:
                Intent intent2 = new Intent(HomepageOperario.this, CadastroOperario.class);
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
}