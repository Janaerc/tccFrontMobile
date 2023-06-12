package com.example.tccfrontmobileusuario;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tccfrontmobileusuario.operario.DetalhesOrdemServicoAberta;
import com.example.tccfrontmobileusuario.operario.HomepageOperario;

import java.util.ArrayList;
import java.util.List;

import backend.EspecialidadeService;
import backend.RetrofitConfig;
import bean.Campus;
import model.ChamadoDTO;
import model.EspecialidadeDTO;
import model.OrdemServicoDTO;
import model.UsuarioDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OperarioRedirecionar extends AppCompatActivity {

    OrdemServicoDTO osDTO = new OrdemServicoDTO();
    UsuarioDTO usuarioDTO;

    ChamadoDTO chamadoDTO;

    private Spinner especialidadeSpinner;

    private List<EspecialidadeDTO> especialidadeList = new ArrayList<>();

    private ArrayAdapter<EspecialidadeDTO> adapter;

    int especialidadeId, selectedEspecialidadeId;

    EditText comentario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operario_redirecionar);
        usuarioDTO = (UsuarioDTO) getIntent().getSerializableExtra("usuario");
        chamadoDTO = (ChamadoDTO) getIntent().getSerializableExtra("chamado");

        especialidadeSpinner = findViewById(R.id.EquipeSpinner);
        comentario = findViewById(R.id.descricao_redirecionamento_editText);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, especialidadeList);
        adapter.setDropDownViewResource((android.R.layout.simple_spinner_dropdown_item));
        especialidadeSpinner.setAdapter(adapter);

        //deve consultar o banco com as especialidades disponiveis e colocar no spinner --ok
        //deve mudar a especialidade da ordem de serviço --ok
        //limpa a atribuição do operario na OS  --ok
        //comentario deve ficar na lista de comentarios do chamado

        loadEspeciadalide();

        especialidadeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                especialidadeId = position + 1;
                Campus campus = (Campus) parent.getItemAtPosition(position);
                selectedEspecialidadeId = campus.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void loadEspeciadalide(){
        Call<List<EspecialidadeDTO>> call = new RetrofitConfig().getEspecidadeService().listarEspecialidades();

        call.enqueue((new Callback<List<EspecialidadeDTO>>() {
            @Override
            public void onResponse(Call<List<EspecialidadeDTO>> call, Response<List<EspecialidadeDTO>> response) {
                if(response.isSuccessful()){
                    List<EspecialidadeDTO> especialidadeDTO = response.body();
                    for(EspecialidadeDTO c : especialidadeDTO) {
                        especialidadeList.add(c);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<EspecialidadeDTO>> call, Throwable t) {
                Toast.makeText(OperarioRedirecionar.this, "Erro ao buscar os especialidade", Toast.LENGTH_SHORT).show();
            }
        }));
    }

    public void atualizarEspecialidade (View view) {
        EspecialidadeDTO espec = new EspecialidadeDTO();
        espec.setId(selectedEspecialidadeId);
        osDTO = chamadoDTO.getOrdemServicoId();
        osDTO.setEspecialidadeId(espec);
        osDTO.setUsuarioOperarioId(null);

        //criar a classe comentariooperariodto,
        //passar todas as infos, fazer um call para comentariooperarioService q deve ser criada

        AlertDialog.Builder msgBox = new AlertDialog.Builder(this);
        msgBox.setTitle("Associar");
        msgBox.setMessage("Tem certeza que deseja redirecionar a ordem de serviço?");
        msgBox.setPositiveButton("sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Call<OrdemServicoDTO> call = new RetrofitConfig().getOdemServicoService().redirecionar(osDTO);
                call.enqueue(new Callback<OrdemServicoDTO>() {
                    @Override
                    public void onResponse(Call<OrdemServicoDTO> call, Response<OrdemServicoDTO> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(OperarioRedirecionar.this, "Ordem de serviço associado com sucesso", Toast.LENGTH_SHORT ).show();
                            Intent it = new Intent(OperarioRedirecionar.this, HomepageOperario.class);
                            it.putExtra("usuario", usuarioDTO);
                            startActivity(it);
                        } else {
                            Toast.makeText(OperarioRedirecionar.this, "Não foi possível associar a ordem de serviço", Toast.LENGTH_SHORT ).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<OrdemServicoDTO> call, Throwable t) {
                        Toast.makeText(OperarioRedirecionar.this, "Erro ao associar a ordem de serviço", Toast.LENGTH_SHORT ).show();
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

}