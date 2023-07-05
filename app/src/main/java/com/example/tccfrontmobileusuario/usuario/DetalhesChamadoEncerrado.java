package com.example.tccfrontmobileusuario.usuario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tccfrontmobileusuario.R;
import com.example.tccfrontmobileusuario.operario.ManterOrdemDeServico;

import java.util.ArrayList;
import java.util.List;

import adapter.ComentarioListAdapter;
import backend.RetrofitConfig;
import model.ChamadoDTO;
import model.ComentarioOperarioDTO;
import model.UsuarioDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalhesChamadoEncerrado extends AppCompatActivity {

    UsuarioDTO usuarioDTO;

    ChamadoDTO chamadoDTO;

    EditText editCampus, editPredio, editLocalizacao, editProblema;

    private List<ComentarioOperarioDTO> comentarioDTO = new ArrayList<>();

    private ComentarioListAdapter comentarioListAdapter;

    private RecyclerView recyclerViewComentarios;
    TextView numeroChamado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_detalhes_chamado_encerrado);
        usuarioDTO = (UsuarioDTO) getIntent().getSerializableExtra("usuario");
        chamadoDTO = (ChamadoDTO) getIntent().getSerializableExtra("chamado");
        editCampus = findViewById(R.id.editTexCampus);
        editPredio = findViewById(R.id.editTextPredio);
        editLocalizacao = findViewById(R.id.descricao_localizacao_editText);
        editProblema = findViewById(R.id.descricao_problema_editText);
        recyclerViewComentarios = findViewById(R.id.recyclerViewComentarios);
        numeroChamado = findViewById(R.id.numero_chamado_detalhes);

        numeroChamado.setText(chamadoDTO.getId().toString());
        editCampus.setText(chamadoDTO.getPredioId().getCampusId().getNome());
        editPredio.setText(chamadoDTO.getPredioId().getNome());
        editLocalizacao.setText(chamadoDTO.getDescricaoLocal());
        editProblema.setText(chamadoDTO.getDescricaoProblema());
        recyclerViewComentarios.setAdapter(comentarioListAdapter);

    }


    public void comentarios() {
        Call<List<ComentarioOperarioDTO>> call = new RetrofitConfig().getComentarioService().listarComentarios(chamadoDTO.getOrdemServicoId().getId());

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
                    Toast.makeText(DetalhesChamadoEncerrado.this, "Erro ao carregar comentarios", Toast.LENGTH_SHORT).show();

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
    public void voltarHome(View view) {
        Intent it = new Intent(DetalhesChamadoEncerrado.this, HomepageUsuario.class);
        it.putExtra("usuario", usuarioDTO);
        startActivity(it);
    }
}