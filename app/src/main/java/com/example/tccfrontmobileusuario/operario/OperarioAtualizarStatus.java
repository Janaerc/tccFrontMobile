package com.example.tccfrontmobileusuario.operario;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
                //fazer o call que irá atualizar o status id de todos os chamados
                //em que a ordemServicoID = chamado.getOrdemServicoID.getId

            }
        });
        msgBox.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        msgBox.show();
    }

    public void salvarStatus(View view) {
        AlertDialog.Builder msgBox = new AlertDialog.Builder(this);
        msgBox.setMessage("Tem certeza que deseja salvar esta atualização de status?");
        msgBox.setPositiveButton("sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ComentarioOperarioDTO comentarioOperarioDTO = new ComentarioOperarioDTO();
                comentarioOperarioDTO.setUsuarioId(usuarioDTO);
                comentarioOperarioDTO.setDescricao(descricao.toString());
                OrdemServicoDTO ordemServicoDTO;
                ordemServicoDTO = chamadoDTO.getOrdemServicoId();
                comentarioOperarioDTO.setOrdemServicoId(ordemServicoDTO);

                Call<OrdemServicoDTO> call = new RetrofitConfig().getComentarioService().inserirComentario(comentarioOperarioDTO);
                call.enqueue(new Callback<OrdemServicoDTO>() {
                    @Override
                    public void onResponse(Call<OrdemServicoDTO> call, Response<OrdemServicoDTO> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(OperarioAtualizarStatus.this, "Status atualizado com sucesso", Toast.LENGTH_SHORT).show();
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
}