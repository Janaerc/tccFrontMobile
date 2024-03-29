package com.example.tccfrontmobileusuario.operario;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tccfrontmobileusuario.R;

import java.text.ParseException;

import backend.RetrofitConfig;
import model.ChamadoDTO;
import model.OrdemServicoDTO;
import model.UsuarioDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalhesOrdemServicoAberta extends AppCompatActivity {
    OrdemServicoDTO osDTO = new OrdemServicoDTO();
    TextView numeroOS;
    UsuarioDTO usuarioDTO;
    ChamadoDTO chamadoDTO;

    EditText campus, predio, localizacao, problema;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operario_detalhes_ordem_servico_aberta);
        usuarioDTO = (UsuarioDTO) getIntent().getSerializableExtra("usuario");
        chamadoDTO = (ChamadoDTO) getIntent().getSerializableExtra("chamado");
        campus = findViewById(R.id.editTexCampus);
        predio = findViewById(R.id.editTextPredio);
        localizacao = findViewById(R.id.descricao_localizacao_editText);
        problema = findViewById(R.id.descricao_problema_editText);
        numeroOS = findViewById(R.id.numero_chamado_detalhes);

        numeroOS.setText(chamadoDTO.getOrdemServicoId().getId().toString());
        campus.setText(chamadoDTO.getPredioId().getCampusId().getNome());
        predio.setText(chamadoDTO.getPredioId().getNome());
        localizacao.setText(chamadoDTO.getDescricaoLocal());
        problema.setText(chamadoDTO.getDescricaoProblema());

        System.out.println("aqui esta a o chamadoDTO");
        System.out.println(chamadoDTO);


    }

    public void associar(View view) throws ParseException {
        osDTO = chamadoDTO.getOrdemServicoId();
        osDTO.setUsuarioOperarioId(usuarioDTO);



        AlertDialog.Builder msgBox = new AlertDialog.Builder(this);
        msgBox.setTitle("Associar");
        msgBox.setMessage("Tem certeza que deseja associar-se a ordem de serviço?");
        msgBox.setPositiveButton("Sim", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            Call<OrdemServicoDTO> call = new RetrofitConfig().getOdemServicoService().associarOS(osDTO.getId(), osDTO.getUsuarioOperarioId().getId());
            System.out.println(osDTO.getId());
            System.out.println(osDTO.getUsuarioOperarioId().getId());
            call.enqueue(new Callback<OrdemServicoDTO>() {
                @Override
                public void onResponse(Call<OrdemServicoDTO> call, Response<OrdemServicoDTO> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(DetalhesOrdemServicoAberta.this, "Ordem de serviço associado com sucesso", Toast.LENGTH_SHORT ).show();
                            Intent it = new Intent(DetalhesOrdemServicoAberta.this, HomepageOperario.class);
                            it.putExtra("usuario", usuarioDTO);
                            startActivity(it);
                        } else {
                            Toast.makeText(DetalhesOrdemServicoAberta.this, "Não foi possível associar a ordem de serviço", Toast.LENGTH_SHORT ).show();

                        }
                }

                @Override
                public void onFailure(Call<OrdemServicoDTO> call, Throwable t) {
                    Toast.makeText(DetalhesOrdemServicoAberta.this, "Ordem de serviço associado com sucesso", Toast.LENGTH_SHORT ).show();
                    Intent it = new Intent(DetalhesOrdemServicoAberta.this, HomepageOperario.class);
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
        Intent intent = new Intent(DetalhesOrdemServicoAberta.this, HomepageOperario.class);
        intent.putExtra("usuario", usuarioDTO);
        startActivity(intent);
    }





}