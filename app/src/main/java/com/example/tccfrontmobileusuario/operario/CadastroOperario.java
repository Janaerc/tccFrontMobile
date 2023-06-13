package com.example.tccfrontmobileusuario.operario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tccfrontmobileusuario.Cadastro;
import com.example.tccfrontmobileusuario.HomepageUsuario;
import com.example.tccfrontmobileusuario.Logout;
import com.example.tccfrontmobileusuario.MainActivity;
import com.example.tccfrontmobileusuario.PrimeiroAcesso;
import com.example.tccfrontmobileusuario.R;
import com.example.tccfrontmobileusuario.SobreApp;

import backend.RetrofitConfig;
import model.UsuarioDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroOperario extends AppCompatActivity {

    UsuarioDTO usuarioDTO;
    EditText nomeUsuario, telefoneUsuario, cpfUsuario, senhaUsuario, emailUsuario, senhaUsuario2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_operario);
        usuarioDTO = (UsuarioDTO) getIntent().getSerializableExtra("usuario");
        nomeUsuario = findViewById(R.id.editTextName);
        cpfUsuario = findViewById(R.id.editTextCpf);
        telefoneUsuario = findViewById(R.id.editTextTelefone);
        emailUsuario = findViewById(R.id.editTextEmailAddress);
        senhaUsuario = findViewById(R.id.editTextPassword);
        senhaUsuario2 = findViewById(R.id.editTextPassword2);

        nomeUsuario.setText(usuarioDTO.getNome());
        cpfUsuario.setText(usuarioDTO.getCpf());
        telefoneUsuario.setText(usuarioDTO.getTelefone());
        emailUsuario.setText(usuarioDTO.getEmail());


    }

    public void atualizarCadastro(View view){

        UsuarioDTO usuario = new UsuarioDTO();

        usuario.setId(usuarioDTO.getId());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setTelefone(usuarioDTO.getTelefone());
        usuario.setEmail(usuarioDTO.getEmail());
        String senha = senhaUsuario.getText().toString();
        String senha2 = senhaUsuario2.getText().toString();

        // VALIDAÇÃO DOS CAMPOS
        if (usuario.getNome().isEmpty()){
            Toast.makeText(this, "Preencha um Nome Válido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (usuario.getTelefone().isEmpty()){
            Toast.makeText(this, "Preencha um Telefone Válido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (usuario.getEmail().isEmpty()){
            Toast.makeText(this, "Preencha um E-mail Válido", Toast.LENGTH_SHORT).show();
            return;
        }
        //CASO: NÃO ATUALIZAR A SENHA

        if (senha.isEmpty()|| senha.isEmpty()) {

            Call<UsuarioDTO> call1 = new RetrofitConfig().getUsuarioService().atualizaUsuario(usuario.getId(),usuario);
            call1.enqueue(new Callback<UsuarioDTO>() {
                @Override
                public void onResponse(Call<UsuarioDTO> call, Response<UsuarioDTO> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(CadastroOperario.this, "Cadastro Atualizado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CadastroOperario.this, HomepageUsuario.class);
                        intent.putExtra("usuario", usuarioDTO);
                        startActivity(intent);

                    } else {
                        Toast.makeText(CadastroOperario.this, "Erro ao Atualizar Cadastro ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CadastroOperario.this, Cadastro.class);
                        intent.putExtra("usuario", usuarioDTO);
                        startActivity(intent);

                    }
                }

                @Override
                public void onFailure(Call<UsuarioDTO> call, Throwable t) {
                    Toast.makeText(CadastroOperario.this, "Cadastro não atualizado ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CadastroOperario.this, Cadastro.class);
                    intent.putExtra("usuario", usuarioDTO);
                    startActivity(intent);
                }
            });
        }
        else {
            //CASO: ATUALIZAR A SENHA
            if(senha.equals(senha2)) {
                usuario.setSenha(usuarioDTO.getSenha());
                Call<UsuarioDTO> call1 = new RetrofitConfig().getUsuarioService().atualizaUsuario(usuario.getId(),usuario);
                call1.enqueue(new Callback<UsuarioDTO>() {
                    @Override
                    public void onResponse(Call<UsuarioDTO> call, Response<UsuarioDTO> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(CadastroOperario.this, "Cadastro Atualizado2", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CadastroOperario.this, HomepageUsuario.class);
                            intent.putExtra("usuario", usuarioDTO);
                            startActivity(intent);

                        } else {
                            Toast.makeText(CadastroOperario.this, "Erro ao Atualizar Cadastro2 ", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CadastroOperario.this, Cadastro.class);
                            intent.putExtra("usuario", usuarioDTO);
                            startActivity(intent);

                        }
                    }

                    @Override
                    public void onFailure(Call<UsuarioDTO> call, Throwable t) {
                        Toast.makeText(CadastroOperario.this, "Cadastro não atualizado2 ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CadastroOperario.this, Cadastro.class);
                        intent.putExtra("usuario", usuarioDTO);
                        startActivity(intent);
                    }
                });



            }
            else {
                Toast.makeText(this, "As senhas não são iguais", Toast.LENGTH_SHORT).show();
                return;

            }
        }
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
        switch (item.getItemId()){
            case R.id.sobreApp:
                Intent intent = new Intent(CadastroOperario.this, SobreApp.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.cadastro_operario:
                Intent intent2 = new Intent(CadastroOperario.this, CadastroOperario.class);
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

    public void closeActivity (View view) {
        Intent intent = new Intent(CadastroOperario.this, HomepageOperario.class);
        startActivity(intent);
        finish();

    }










}