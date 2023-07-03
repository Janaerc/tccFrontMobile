package com.example.tccfrontmobileusuario.operario;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tccfrontmobileusuario.R;

import backend.RetrofitConfig;
import model.ChamadoDTO;
import model.ComentarioOperarioDTO;
import model.OrdemServicoDTO;
import model.UsuarioDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OperarioAtualizarStatus extends AppCompatActivity {

    private UsuarioDTO usuarioDTO;
    private ChamadoDTO chamadoDTO;

    EditText descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operario_atualizar_status);
        usuarioDTO = (UsuarioDTO) getIntent().getSerializableExtra("usuario");
        chamadoDTO = (ChamadoDTO) getIntent().getSerializableExtra("chamado");
        descricao = findViewById(R.id.descricao_atualizacao_status_editText);
    }

    public void resolver(View view){
        AlertDialog.Builder msgBox = new AlertDialog.Builder(this);
        msgBox.setMessage("Tem certeza que deseja resolver esta ordem de serviço?");
        msgBox.setPositiveButton("sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //fazer o call que irá atualizar o status id = 2 de todos os chamados
                //em que a ordemServicoID = chamado.getOrdemServicoID.getId

                Call<ChamadoDTO> call = new RetrofitConfig().getChamadoService().resolverChamado(chamadoDTO.getOrdemServicoId().getId());

                call.enqueue(new Callback<ChamadoDTO>() {
                    @Override
                    public void onResponse(Call<ChamadoDTO> call, Response<ChamadoDTO> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(OperarioAtualizarStatus.this, "Ordem de serviço finalizada com sucesso", Toast.LENGTH_SHORT ).show();
                            Intent it = new Intent(OperarioAtualizarStatus.this, HomepageOperario.class);
                            it.putExtra("usuario", usuarioDTO);
                            startActivity(it);
                        } else {
                            Toast.makeText(OperarioAtualizarStatus.this, "Não foi possível atualizar a ordem de serviço", Toast.LENGTH_SHORT ).show();

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


    public void redirecionar(View view) {
        Intent it = new Intent(OperarioAtualizarStatus.this, OperarioRedirecionar.class);
        it.putExtra("usuario", usuarioDTO);
        it.putExtra("chamado", chamadoDTO);
        startActivity(it);
    }

    public void salvarStatus(View view) {
        AlertDialog.Builder msgBox = new AlertDialog.Builder(this);
        msgBox.setMessage("Tem certeza que deseja salvar esta atualização de status?");
        msgBox.setPositiveButton("sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ComentarioOperarioDTO comentarioOperarioDTO = new ComentarioOperarioDTO();
                comentarioOperarioDTO.setUsuarioId(usuarioDTO);
                comentarioOperarioDTO.setDescricao(descricao.getText().toString());
                OrdemServicoDTO ordemServicoDTO;
                ordemServicoDTO = chamadoDTO.getOrdemServicoId();
                ordemServicoDTO.setDataAbertura(null);
                comentarioOperarioDTO.setOrdemServicoId(ordemServicoDTO);
                comentarioOperarioDTO.setDataHora(null);


                Call<OrdemServicoDTO> call = new RetrofitConfig().getComentarioService().inserirComentario(comentarioOperarioDTO);
                call.enqueue(new Callback<OrdemServicoDTO>() {
                    @Override
                    public void onResponse(Call<OrdemServicoDTO> call, Response<OrdemServicoDTO> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(OperarioAtualizarStatus.this, "Status atualizado com sucesso", Toast.LENGTH_SHORT).show();
                            Intent it = new Intent(OperarioAtualizarStatus.this, HomepageOperario.class);
                            it.putExtra("usuario", usuarioDTO);
                            startActivity(it);
                        }
                    }

                    @Override
                    public void onFailure(Call<OrdemServicoDTO> call, Throwable t) {
                        Toast.makeText(OperarioAtualizarStatus.this, "Erro ao atualizar status", Toast.LENGTH_SHORT).show();

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
        Intent intent = new Intent(OperarioAtualizarStatus.this, HomepageOperario.class);
        intent.putExtra("usuario", usuarioDTO);
        startActivity(intent);
    }
}