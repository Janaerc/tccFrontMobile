package com.example.tccfrontmobileusuario.operario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tccfrontmobileusuario.R;

import java.util.ArrayList;
import java.util.List;

import adapter.ComentarioListAdapter;
import backend.RetrofitConfig;
import helper.RecyclerItemClickListener;
import model.ChamadoDTO;
import model.ComentarioOperarioDTO;
import model.UsuarioDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManterOrdemDeServico extends AppCompatActivity {

    private UsuarioDTO usuarioDTO;
    private ChamadoDTO chamadoDTO;

    private EditText campus, predio, localizacao, problema;

    private List<ComentarioOperarioDTO> comentarioDTO = new ArrayList<>();

    private ComentarioListAdapter comentarioListAdapter;

    private RecyclerView recyclerViewComentarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operario_manter_ordem_de_servico);
        usuarioDTO = (UsuarioDTO) getIntent().getSerializableExtra("usuario");
        chamadoDTO = (ChamadoDTO) getIntent().getSerializableExtra("chamado");
        campus = findViewById(R.id.ediTextCampus);
        predio = findViewById(R.id.ediTextPredio);
        localizacao = findViewById(R.id.descricao_localizacao_editText);
        problema = findViewById(R.id.descricao_problema_editText);
        recyclerViewComentarios = findViewById(R.id.recyclerViewComentarios);

        campus.setText(chamadoDTO.getPredioId().getCampusId().getNome());
        predio.setText(chamadoDTO.getPredioId().getNome());
        localizacao.setText(chamadoDTO.getDescricaoLocal());
        problema.setText(chamadoDTO.getDescricaoProblema());

        recyclerViewComentarios.setAdapter(comentarioListAdapter);


    }



    public void comentarios() {
        Call<List< ComentarioOperarioDTO>> call = new RetrofitConfig().getComentarioService().listarComentarios(chamadoDTO.getOrdemServicoId().getId());

        call.enqueue(new Callback<List<ComentarioOperarioDTO>>() {
            @Override
            public void onResponse(Call<List<ComentarioOperarioDTO>> call, Response<List<ComentarioOperarioDTO>> response) {
                if (response.isSuccessful()){
                    comentarioDTO = response.body();
                    comentarioListAdapter = new ComentarioListAdapter(comentarioDTO);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerViewComentarios.setLayoutManager(layoutManager);
                    recyclerViewComentarios.setHasFixedSize(true);
                    recyclerViewComentarios.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.HORIZONTAL));
                    recyclerViewComentarios.setAdapter(comentarioListAdapter);

                } else {
                    Toast.makeText(ManterOrdemDeServico.this, "Erro ao carregar comentarios", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<ComentarioOperarioDTO>> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        comentarios();
    }

    @Override
    protected void onResume(){
        super.onResume();
        comentarios();
    }

    public void atualizarStatus(View view){
        Intent it = new Intent(ManterOrdemDeServico.this, OperarioAtualizarStatus.class);
        it.putExtra("usuario", usuarioDTO);
        it.putExtra("chamado", chamadoDTO);
        startActivity(it);

    }
}