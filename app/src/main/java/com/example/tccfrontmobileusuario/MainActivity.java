package com.example.tccfrontmobileusuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import backend.RetrofitConfig;
import model.LoginDTO;
import model.UsuarioDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText ETcpf, ETsenha;
    UsuarioDTO usuarioDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ETcpf = findViewById(R.id.editTextCPFLogin);
        ETsenha = findViewById(R.id.editTextTextPassword);
    }

    public void logar (View view){
        LoginDTO login = new LoginDTO();
        login.setCpf(ETcpf.getText().toString());
        login.setSenha(ETcpf.getText().toString());

        Call<UsuarioDTO> call1 = new RetrofitConfig().getUsuarioService().login(login);
        call1.enqueue(new Callback<UsuarioDTO>() {
            @Override
            public void onResponse(Call<UsuarioDTO> call, Response<UsuarioDTO> response) {
                if (response.isSuccessful()) {
                    UsuarioDTO usuarioDTO = response.body();
                    Intent intent = new Intent(MainActivity.this, HomepageUsuario.class);
                    intent.putExtra("usuario", usuarioDTO);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(MainActivity.this, "CPF ou senha inválidos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UsuarioDTO> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(MainActivity.this, "Erro ao fazer login", Toast.LENGTH_SHORT).show();

            }
        });

    }


    public void primeiroAcesso(View view){
        Intent it = new Intent( this, PrimeiroAcesso.class);
        startActivity(it);



    }

    public void esqueceuSenha(View view){
        Intent it = new Intent( this, EsqueceuSenha.class);
        startActivity(it);



    }






}