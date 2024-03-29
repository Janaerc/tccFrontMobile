package com.example.tccfrontmobileusuario.operario;

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

import com.example.tccfrontmobileusuario.R;

import java.util.ArrayList;
import java.util.List;

import backend.RetrofitConfig;
import model.ChamadoDTO;
import model.ComentarioOperarioDTO;
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
                EspecialidadeDTO especialidade = (EspecialidadeDTO) parent.getItemAtPosition(position);
                selectedEspecialidadeId = especialidade.getId();
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
                    System.out.println("resposta sucess");
                    List<EspecialidadeDTO> especialidadeDTO = response.body();
                    for(EspecialidadeDTO c : especialidadeDTO) {
                        System.out.println(c);
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
        osDTO.setDataAbertura(null);

        System.out.println("aqui o id do especialidade");
        System.out.println(selectedEspecialidadeId);

        ComentarioOperarioDTO comentarioOperarioDTO = new ComentarioOperarioDTO();
        comentarioOperarioDTO.setDescricao(comentario.getText().toString());
        comentarioOperarioDTO.setUsuarioId(usuarioDTO);
        comentarioOperarioDTO.setOrdemServicoId(osDTO);
        comentarioOperarioDTO.setDataHora(null);


        AlertDialog.Builder msgBox = new AlertDialog.Builder(this);
        msgBox.setTitle("Redirecionar");
        msgBox.setMessage("Tem certeza que deseja redirecionar a ordem de serviço?");
        msgBox.setPositiveButton("sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Call<OrdemServicoDTO> call1 = new RetrofitConfig().getComentarioService().inserirComentario(comentarioOperarioDTO);
                call1.enqueue(new Callback<OrdemServicoDTO>() {
                    @Override
                    public void onResponse(Call<OrdemServicoDTO> call, Response<OrdemServicoDTO> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(OperarioRedirecionar.this, "Comentario registrado com sucesso", Toast.LENGTH_SHORT ).show();

                        }
                        else {
                            Toast.makeText(OperarioRedirecionar.this, "Não foi possível registrar o comentario", Toast.LENGTH_SHORT ).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<OrdemServicoDTO> call, Throwable t) {
                        Toast.makeText(OperarioRedirecionar.this, "Erro ao adicionar o comentario", Toast.LENGTH_SHORT ).show();
                    }
                });


                Call<OrdemServicoDTO> call = new RetrofitConfig().getOdemServicoService().redirecionarOS(osDTO.getId(), selectedEspecialidadeId);
                call.enqueue(new Callback<OrdemServicoDTO>() {
                    @Override
                    public void onResponse(Call<OrdemServicoDTO> call, Response<OrdemServicoDTO> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(OperarioRedirecionar.this, "Ordem de serviço atualizada com sucesso", Toast.LENGTH_SHORT ).show();
                                                    } else {
                            Toast.makeText(OperarioRedirecionar.this, "Não foi possível atualizar a ordem de serviço", Toast.LENGTH_SHORT ).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<OrdemServicoDTO> call, Throwable t) {
                        Toast.makeText(OperarioRedirecionar.this, "Ordem de serviço atualizada com sucesso", Toast.LENGTH_SHORT ).show();
                        Intent it = new Intent(OperarioRedirecionar.this, HomepageOperario.class);
                        it.putExtra("usuario", usuarioDTO);
                        startActivity(it);
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

    public void voltarHome(View view) {
        Intent intent = new Intent(OperarioRedirecionar.this, HomepageOperario.class);
        intent.putExtra("usuario", usuarioDTO);
        startActivity(intent);
    }
}