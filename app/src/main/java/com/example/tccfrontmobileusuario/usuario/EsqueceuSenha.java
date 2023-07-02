package com.example.tccfrontmobileusuario.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tccfrontmobileusuario.R;

import backend.RetrofitConfig;
import model.UsuarioDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EsqueceuSenha extends AppCompatActivity {

    EditText editCpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueceu_senha);

        editCpf = findViewById(R.id.editTextCpfEsqueceuSenha);

    }
    public void recuperarSenha(View view) {
        int cpf = Integer.parseInt(editCpf.getText().toString());

        Call<UsuarioDTO> call = new RetrofitConfig().getUsuarioService().recuperarSenha(cpf);

        call.enqueue(new Callback<UsuarioDTO>() {
            @Override
            public void onResponse(Call<UsuarioDTO> call, Response<UsuarioDTO> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(EsqueceuSenha.this, "Senha enviada por e-mail", Toast.LENGTH_SHORT ).show();
                    Intent it = new Intent(EsqueceuSenha.this, MainActivity.class);
                    startActivity(it);
                } else {
                    Toast.makeText(EsqueceuSenha.this, "Não foi possível recuperar a senha", Toast.LENGTH_SHORT ).show();

                }
            }

            @Override
            public void onFailure(Call<UsuarioDTO> call, Throwable t) {
                Toast.makeText(EsqueceuSenha.this, "Erro ao recuperar senha", Toast.LENGTH_SHORT ).show();

            }
        });
    }
}